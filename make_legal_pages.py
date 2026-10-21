#!/usr/bin/env python3
"""Turn the repo's legal .txt files into simple HTML pages in docs/.

Run from the repository root:  python3 make_legal_pages.py
The text is copied exactly as written; only HTML escaping, paragraph breaks
and numbered headings are added. Nothing is reworded.
"""
import html
import os
import re

PAGES = [
    ("Privacy Policy.txt", "privacy-policy.html", "Privacy Policy"),
    ("Terms of Service.txt", "terms-of-service.html", "Terms of Service"),
    ("Data Deletion Request Instructions.txt", "data-deletion.html", "Data Deletion Request Instructions"),
]

CSS = """
body{font-family:-apple-system,Segoe UI,Roboto,Helvetica,Arial,sans-serif;line-height:1.6;
max-width:760px;margin:2rem auto;padding:0 1rem;color:#1f2328}
h1{font-size:1.8rem}h2{font-size:1.3rem;margin-top:2rem}h3{font-size:1.1rem;margin-top:1.5rem}
nav a{margin-right:1rem}footer{margin-top:3rem;color:#656d76;font-size:.9rem}
"""

NAV = ('<nav><a href="index.html">Home</a><a href="privacy-policy.html">Privacy Policy</a>'
       '<a href="terms-of-service.html">Terms of Service</a>'
       '<a href="data-deletion.html">Data Deletion</a></nav>')

HEADING = re.compile(r"^(\d+(?:\.\d+)*)\.?\s+\S")


def is_heading(line):
    """A short numbered line that is a title, not a sentence."""
    if not HEADING.match(line) or len(line) > 70:
        return False
    if line.endswith((".", "!", "?", ";", ",")):
        return False
    if ":" in line and line.split(":", 1)[1].strip():
        return False
    return True


def body_html(text):
    blocks = [b.strip("\n") for b in re.split(r"\n\s*\n", text.replace("\r\n", "\n")) if b.strip()]
    out = []
    for b in blocks:
        lines = [l.rstrip() for l in b.split("\n")]
        first = lines[0].strip()
        if len(lines) == 1 and is_heading(first):
            level = "h2" if "." not in HEADING.match(first).group(1) else "h3"
            out.append(f"<{level}>{html.escape(first)}</{level}>")
        else:
            out.append("<p>" + "<br>\n".join(html.escape(l.strip()) for l in lines) + "</p>")
    return "\n".join(out)


def page(title, content):
    return (f'<!doctype html>\n<html lang="en">\n<head>\n<meta charset="utf-8">\n'
            f'<meta name="viewport" content="width=device-width, initial-scale=1">\n'
            f'<title>{html.escape(title)} - Cloud Counselage Connect+</title>\n<style>{CSS}</style>\n</head>\n'
            f'<body>\n{NAV}\n<h1>{html.escape(title)}</h1>\n{content}\n'
            f'<footer>Cloud Counselage Connect+</footer>\n</body>\n</html>\n')


def main():
    os.makedirs("docs", exist_ok=True)
    links = []
    for src, dest, title in PAGES:
        if not os.path.exists(src):
            print(f"skipped (not found): {src}")
            continue
        with open(src, encoding="utf-8", errors="replace") as f:
            text = f.read()
        with open(os.path.join("docs", dest), "w", encoding="utf-8") as f:
            f.write(page(title, body_html(text)))
        links.append(f'<li><a href="{dest}">{html.escape(title)}</a></li>')
        print(f"wrote docs/{dest}")
    index = page("Cloud Counselage Connect+", "<ul>\n" + "\n".join(links) + "\n</ul>")
    with open(os.path.join("docs", "index.html"), "w", encoding="utf-8") as f:
        f.write(index)
    print("wrote docs/index.html")


if __name__ == "__main__":
    main()
