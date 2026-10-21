package com.example.cloudcounselageconnect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Matches a user's message to the closest labelled intent by comparing word sets
 * (Dice coefficient). This is a similarity-based lookup over a labelled dataset.
 * It does not learn or adjust any weights.
 */
public class IntentMatcher {

    public static class Intent {
        final String name;
        final String response;
        final List<Set<String>> patterns = new ArrayList<>();

        public Intent(String name, String response, List<String> patternTexts) {
            this.name = name;
            this.response = response;
            for (String p : patternTexts) {
                patterns.add(tokens(p));
            }
        }
    }

    /** Very common words that say nothing about which topic a question is about. */
    private static final Set<String> STOP = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "are", "to", "of", "in", "it", "be",
            "this", "that", "there", "any", "does", "do", "i", "my", "me", "am",
            "what", "how", "can", "will", "which", "who", "tell", "about", "please",
            "you", "your", "for", "get", "give", "with", "on", "at", "and", "or",
            "if", "by", "from", "have", "has", "was", "were", "would", "could",
            "should", "know", "want", "also", "needed", "so", "we", "us"));

    private final List<Intent> intents;
    private final double threshold;
    private final String fallback;

    public IntentMatcher(List<Intent> intents, double threshold, String fallback) {
        this.intents = intents;
        this.threshold = threshold;
        this.fallback = fallback;
    }

    /** Name of the best intent, or "fallback" when nothing scores above the threshold. */
    public String bestIntent(String query) {
        Set<String> q = tokens(query);
        if (q.isEmpty()) return "fallback";
        String best = "fallback";
        double bestScore = 0.0;
        for (Intent intent : intents) {
            for (Set<String> p : intent.patterns) {
                if (p.isEmpty()) continue;
                int common = 0;
                for (String w : q) {
                    if (p.contains(w)) common++;
                }
                double score = 2.0 * common / (q.size() + p.size());
                if (score > bestScore) {
                    bestScore = score;
                    best = intent.name;
                }
            }
        }
        return bestScore >= threshold ? best : "fallback";
    }

    /** Reply of the best intent, or null when nothing scores above the threshold. */
    public String replyOrNull(String query) {
        String name = bestIntent(query);
        for (Intent intent : intents) {
            if (intent.name.equals(name)) return intent.response;
        }
        return null;
    }

    public String reply(String query) {
        String r = replyOrNull(query);
        return r != null ? r : fallback;
    }

    static Set<String> tokens(String text) {
        Set<String> out = new HashSet<>();
        String cleaned = text.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9 ]", " ").trim();
        if (cleaned.isEmpty()) return out;
        for (String w : cleaned.split("\\s+")) {
            if (!STOP.contains(w)) out.add(stem(w));
        }
        return out;
    }

    private static String stem(String w) {
        if (w.endsWith("ies") && w.length() > 4) return w.substring(0, w.length() - 3) + "y";
        if (w.endsWith("s") && w.length() > 3) return w.substring(0, w.length() - 1);
        return w;
    }
}
