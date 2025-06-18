---
layout: post
title: Stumble upon in June 2025
image: /assets/img/careless_people.webp
image_alt: "Midjourney prompt: A surreal digital painting of corporate executives laughing in a crumbling glass tower, blindfolded and indifferent, surrounded by a chaotic world of protest signs, burning data, and broken smartphones high contrast, moody lighting, dystopian tech aesthetic, cinematic realism, muted color palette. (Prompt suggested by ChatGPT based on a longer description)"
description: "On occasion, I come across content worth sharing. This time: A book about Facebook, a blog post about Big Tech & AI and a Danish DNS provider DNS.services."
categories:
- Other
tags:
- stumbleupon
- dns
comments: true
---

I have started a journey to disentangle myself from Big Tech — at least partly.
So far, I've avoided boarding the Facebook train altogether (including Instagram, Snapchat and TikTok).
And after finishing the book [*"Careless People"* by *Sarah Wynn-Williams*][6] (recommendable by the way),
which describes her experiences from inside Facebook,
my JOMO choice feels even better.
Apparently, friends and family still invite me to their birthdays using alternative (old-fashioned) means 😅
You know, like phone calls, SMS, and sometimes even face-to-face — weird!

It is not all flex. 💪
I have a LinkedIn profile, and with the excuse of improving my professional network,
I've been visiting the website more frequently lately.
Even though it is obvious how it tries to grab my attention, it still succeeds in pulling me in.
The same is true for YouTube. When watching a video about hardware or a conference talk,
I suddenly find myself watching anything but the reason I opened YouTube in the first place.
It is scary 😱

So, when I stumbled upon Sam Thursfield's ["status update" blog post][1]
about some of the same things,
it felt very comforting to know I wasn't alone with these issues.

However, to take matters into my own hands,
I am researching how to set up a somewhat powerful AI locally at home.
For this, a GPU with plenty of vRAM is strongly recommended.
I plan to acquire a used graphics card of an older Nvidia model such as RTX 3090 with 24 GB.
mostly because ChatGPT and YouTube strongly suggest that... I know 🤣

But, I don't want to install everything directly on the host,
so of course I started playing around with Docker,
since GPUs can be attached to a container upon start.

Having Docker, I might as well look into hosting my own password manager like [Vaultwarden][2]
and an office suite with file sharing via [Nextcloud][3].

Alas, such services would need to be exposed using HTTPS,
and nothing short of an SSL terminating reverse proxy would suffice.
That meant looking into [Nginx Proxy Manager][8] and [Let's Encrypt][9],
and while roaming GitHub,
[I managed to help someone][5] with some of the same problems I ran into.

My current DNS provider, One.com, didn't integrate with *Nginx Proxy Manager*.
Having a DNS service that is giving me headaches every time I need to do accounting (foreign VAT),
when it isn't even anything special, made me look around for an alternative.

I considered several DNS providers and Namecheap, with their FreeDNS offering, was one of them.
But when I found, they omit DNSSEC as part of their free service AND being a US company, I resumed my search.
In the end, I was recommended [DNS.services][4] by an old colleague, and it felt like "coming home". ♥️
For those who remember GratisDNS (which was bought by One.com),
I think *DNS.services* manages to retain the same vibe, although it is more modern.

*DNS.services* doesn't integrate with *Nginx Proxy Manager* either,
but at least I avoid the accounting headache AND I'm saving over 20 € a year for the same service.
If I ever get around to implementing the integration myself,
I gathered some notes on the subject in the discussion:
["how to add own DNS provider for DNS challenge?"][7]

I'll share more details about my "break free from Big-Tech" projects as they happen.

[1]: https://samthursfield.wordpress.com/2025/06/15/status-update-15-06-2025/
[2]: https://www.vaultwarden.ca/
[3]: https://nextcloud.com/
[4]: https://dns.services/
[5]: https://github.com/NginxProxyManager/nginx-proxy-manager/discussions/4375
[6]: https://en.wikipedia.org/wiki/Careless_People
[7]: https://github.com/NginxProxyManager/nginx-proxy-manager/discussions/4565
[8]: https://github.com/NginxProxyManager/nginx-proxy-manager
[9]: https://letsencrypt.org/
