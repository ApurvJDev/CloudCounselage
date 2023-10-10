package com.example.cloudcounselageconnect;

public class BotResponse {
    public static String getBotReply(String query) {

        query = query.toLowerCase().trim();

        if(query.contains("hello")) {
            return "Hello! How can I assist you?";
        } else if (query.contains("how are you")) {
            return "I'm just a bot, but I'm here to help!";
        } else if (query.contains("thank")) {
            return "You're Welcome!";
        } else if (query.contains("duration") || query.contains("weeks") || query.contains("difference")) {
            return "The difference in the internship duration is to provide you flexibility to " +
                    "accommodate your academic requirements, as many colleges do not allow their " +
                    "students to do internship while the academic sessions are in progress. " +
                    "Such students can contribute more hours and finish their internship early.\n" +
                    "\n" +
                    "The problem statement for all the duration for the resp. domain will be same. " +
                    "The excellence awards will categorised based on the duration and projects " +
                    "submitted for 6 weeks will be accessed in relation to the other projects " +
                    "submitted for the same duration";
        } else if (query.contains("domain")) {
            return "There are 16 IT & 7 Management domains for internship:\n" +
                    "Cloud Computing,  Digital Marketing, DevOps, Human Resources, Machine Learning," +
                    " Data Analytics, Artificial Intelligence, Business Research, Web Development, Java, Python,\n" +
                    "Business Development, React JS, UI/UX, Node.js, Operations Management, " +
                    "Android Development, Quality Assurance, Flutter, Cyber Security, Product Management," +
                    "  Project Management, Game Development, Blockchain, Full Stack Development, Marketing Management";
        } else if (query.contains("internship")) {
            return "Please paste the link in your browser to signup " +
                    "for the free online internship https://www.industryacademiacommunity.com/courses/Internships";
        } else if (query.contains("update") || query.contains("telegram")) {
            return "Please join our Telegram Channel for updates: https://t.me/+uXmD1vTLpttjN2Vl";
        }  else if (query.contains("offer letter") || query.contains("certificate")) {
            return "The participants will get following certificates and letters:\n" +
                    "> Appointment letter within 15 days after the interns choose the field of interest\n" +
                    "> Industry Training Certificate (further to completing assessment)\n" +
                    "> Experience Certificate from Cloud Counselage (on successful completion of internship)\n" +
                    "> Appreciation letter/certificate ( For top performers)\n";
        }  else {
            return "I'm not sure how to respond to that.";
        }
    }
}
