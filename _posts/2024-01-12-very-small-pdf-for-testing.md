---
layout: post
title: A very small PDF for testing
image: /assets/img/tiny_document_in_grass2.webp
image_alt: "Midjourney prompt: Zoomed in on an extruded 'PDF' document icon standing in the grass with a huge ant next to the icon. There are a few red flowers in the grass and a tree in the background. It is golden morning light."
categories:
- Programming
tags:
- PDF
- Test
comments: true
support: true
excerpt_separator: <!--more-->
---

For testing, I often find myself prioritizing having small data sets because they are easier to comprehend.
Also, the small size helps emphasize what is being tested
when irrelevant data is absent.

Today, I needed a PDF for testing.
Following the same principles, I sought out "the smallest (valid) PDF" and ended up on StackOverflow.

<!--more-->

In most cases, the PDF size probably doesn't matter much,
but I needed a PDF base64 encoded in a JSON file.
A huge PDF would not fit on my screen and force me to scroll
making it harder to get an overview of the JSON file in its entirety.

Back at StackOverflow, specifically at the question:
[What is the smallest possible valid PDF?][1]
I found several suggestions,
but none that would show text
AND work with the default PDF viewer (Evince) on my Linux Fedora laptop.

Having my test PDF contain visible text was important to me.
A blank page can easily be a symptom of something not working,
so using a blank page for testing potentially hides actual errors.

I started searching for the PDF specification,
pondering if I could write one by hand.
I quickly got discouraged
because I didn't want to put too much time and effort into something so "unimportant".
But just as I was about to settle for something slightly larger,
I stumbled over [PrintMyFolders][2] and its owner: Steve.

He provided a [PDF sample][3] from which I was able to easily
create an A4 document with the (almost) centered text "Dummy PDF".
Not only was the test PDF useable by my application,
but it also worked flawlessly in both Firefox, Chrome, and Evince.

Inkscape would create a 5.2 kilo byte file for a similar PDF,
while LibreOffice Writer would create a 7.8 kilo byte file.

630 bytes is of course more than the other StackOverflow examples, but still very small.
The slightly larger size seems like a small price
for the satisfaction of well working test PDF - worth the compromise.

It is even fairly easy to edit the text by using a standard text editor.
Though PDF viewers doesn't seem picky about it
`startxref` is supposed to mark
where `xref` (cross-reference table) is supposed to "start",
and changing the text length pushes where `xref` starts.

The base64 encoded version:
```
JVBERi0xLjQKMSAwIG9iago8PC9UeXBlIC9DYXRhbG9nCi9QYWdlcyAyIDAgUgo+PgplbmRvYmoK
MiAwIG9iago8PC9UeXBlIC9QYWdlcwovS2lkcyBbMyAwIFJdCi9Db3VudCAxCj4+CmVuZG9iagoz
IDAgb2JqCjw8L1R5cGUgL1BhZ2UKL1BhcmVudCAyIDAgUgovTWVkaWFCb3ggWzAgMCA1OTUgODQy
XQovQ29udGVudHMgNSAwIFIKL1Jlc291cmNlcyA8PC9Qcm9jU2V0IFsvUERGIC9UZXh0XQovRm9u
dCA8PC9GMSA0IDAgUj4+Cj4+Cj4+CmVuZG9iago0IDAgb2JqCjw8L1R5cGUgL0ZvbnQKL1N1YnR5
cGUgL1R5cGUxCi9OYW1lIC9GMQovQmFzZUZvbnQgL0hlbHZldGljYQovRW5jb2RpbmcgL01hY1Jv
bWFuRW5jb2RpbmcKPj4KZW5kb2JqCjUgMCBvYmoKPDwvTGVuZ3RoIDUzCj4+CnN0cmVhbQpCVAov
RjEgMjAgVGYKMjIwIDQwMCBUZAooRHVtbXkgUERGKSBUagpFVAplbmRzdHJlYW0KZW5kb2JqCnhy
ZWYKMCA2CjAwMDAwMDAwMDAgNjU1MzUgZgowMDAwMDAwMDA5IDAwMDAwIG4KMDAwMDAwMDA2MyAw
MDAwMCBuCjAwMDAwMDAxMjQgMDAwMDAgbgowMDAwMDAwMjc3IDAwMDAwIG4KMDAwMDAwMDM5MiAw
MDAwMCBuCnRyYWlsZXIKPDwvU2l6ZSA2Ci9Sb290IDEgMCBSCj4+CnN0YXJ0eHJlZgo0OTUKJSVF
T0YK```

... and as an inline <a href="data:application/pdf;base64,JVBERi0xLjQKMSAwIG9iago8PC9UeXBlIC9DYXRhbG9nCi9QYWdlcyAyIDAgUgo+PgplbmRvYmoKMiAwIG9iago8PC9UeXBlIC9QYWdlcwovS2lkcyBbMyAwIFJdCi9Db3VudCAxCj4+CmVuZG9iagozIDAgb2JqCjw8L1R5cGUgL1BhZ2UKL1BhcmVudCAyIDAgUgovTWVkaWFCb3ggWzAgMCA1OTUgODQyXQovQ29udGVudHMgNSAwIFIKL1Jlc291cmNlcyA8PC9Qcm9jU2V0IFsvUERGIC9UZXh0XQovRm9udCA8PC9GMSA0IDAgUj4+Cj4+Cj4+CmVuZG9iago0IDAgb2JqCjw8L1R5cGUgL0ZvbnQKL1N1YnR5cGUgL1R5cGUxCi9OYW1lIC9GMQovQmFzZUZvbnQgL0hlbHZldGljYQovRW5jb2RpbmcgL01hY1JvbWFuRW5jb2RpbmcKPj4KZW5kb2JqCjUgMCBvYmoKPDwvTGVuZ3RoIDUzCj4+CnN0cmVhbQpCVAovRjEgMjAgVGYKMjIwIDQwMCBUZAooRHVtbXkgUERGKSBUagpFVAplbmRzdHJlYW0KZW5kb2JqCnhyZWYKMCA2CjAwMDAwMDAwMDAgNjU1MzUgZgowMDAwMDAwMDA5IDAwMDAwIG4KMDAwMDAwMDA2MyAwMDAwMCBuCjAwMDAwMDAxMjQgMDAwMDAgbgowMDAwMDAwMjc3IDAwMDAwIG4KMDAwMDAwMDM5MiAwMDAwMCBuCnRyYWlsZXIKPDwvU2l6ZSA2Ci9Sb290IDEgMCBSCj4+CnN0YXJ0eHJlZgo0OTUKJSVFT0YK">"data" link to the PDF</a>.


Happy testing!

[1]: https://stackoverflow.com/questions/17279712/what-is-the-smallest-possible-valid-pdf
[2]: https://www.printmyfolders.com/
[3]: https://www.printmyfolders.com/Home/sample-pdf
