---
layout: post
title: Giving vision to my local AI setup using llama.cpp
description: "How to enable vision for vision-capable models in llama.cpp, so they can process images and video."
image: /assets/img/brain_with_googles_in_pixel_art.webp
image_alt: "Midjourney prompt: Electronic brain with googles taking off. Style in pixel art."
categories:
- Programming
tags:
- ai
- Docker
- llamacpp
excerpt_separator: <!--more-->
---

I noticed that Qwen 3.8 was described using the following:

> ... a native vision-language model that understands images and videos ...

and wondered why *llama.cpp* wouldn't allow me to add images to the chat context
(while allowing text and PDF files),
and why Open WebUI would give me the following (non-obvious) error:

> image input is not supported - hint: if this is unexpected, you may need to provide the mmproj

Fortunately, the issue was quickly resolved.

<!--more-->

To get to the core of the problem,
I decided to remove Open WebUI from the equation
by using the *llama.cpp* UI chat directly.

Here, the error was presented much prettier in the UI, but not any more helpful:

<img src="/public/media/llamacpp error, requires a vision-capable model.webp"
     alt="Llama.cpp UI showing the error: 'Images require a vision-capable model'."
     loading="lazy" />

Having to start somewhere,
I searched for any mentions of "vision" and "mmproj" in the `README.md` for *llama.cpp*,
without any luck.
Initial internet searches pointed toward compiling *llama.cpp* or using `llama-mtmd-cli`,
neither of which felt like the right direction.

Supposedly, using the `llama cli -hf ...` command to download models
automatically also ensures vision capabilities when supported (not verified).

But since I've opted to host *llama.cpp* on a server
and populate it with models manually,
I needed a different approach.

I found the hint I needed in the GitHub discussion [#22190 - How to use --mmproj][1],
specifically in this sentence:

> If the model contains multiple GGUF (for multimodal or multi-shard),
> files should be put into a subdirectory.

So now I suspected that I would need to download extra files,
but from where... Hugging Face wasn't eager to push them in my face, like with the models.
After navigating back and forth using [unsloth/Qwen3.8-27B-GGUF][2] as my starting point,
I found the tab *Files and versions* at the top,
which included the following two files with the right "smell":
- `mmproj-BF16.gguf`
- `mmproj-F16.gguf`

AI claims these files are the *"multimodal projection layer of a vision-language model"*
and that the `BF16` file is the newer and safer choice to avoid overflow issues.
AI was very certain that I wouldn't be able to distinguish any quality difference between the two files.
I can confirm it was right about one thing... I couldn't tell the difference 😅

The [file for the "projection layer"][3] was downloaded
using the same method as the Qwen model itself,
by right-clicking on the download icon (arrow down on a flat surface)
and giving the URL to `curl`:


```bash
cd /mnt/storage/gguf-models
curl -L -O https://huggingface.co/unsloth/Qwen3.8-27B-GGUF/resolve/main/mmproj-BF16.gguf?download=true
```

Group the quantized model file and the projection layer in a shared directory.
The following example assumes you already have `Qwen3.8-27B-Q4_0.gguf` downloaded
(the model we downloaded in the previous blog post about *llama.cpp*).

```bash
mkdir Qwen3.8-27B-Q4_0
mv Qwen3.8-27B-Q4_0.gguf mmproj-BF16.gguf Qwen3.8-27B-Q4_0
```

Now restart `llama-server` and notice how the *Add files* menu changed from:

<img src="/public/media/llamacpp, add files without vision support.webp"
     alt="Llama.cpp UI showing 'Add files' menu for model without vision-capabilities."
     width="256"
     loading="lazy" />

to:

<img src="/public/media/llamacpp, add files with vision support.webp"
     alt="Llama.cpp UI showing 'Add files' menu for model with vision-capabilities."
     width="256"
     loading="lazy" />

As far as I can tell, *llama.cpp* can dynamically load the multimodal model and offload it to the GPU
without me having to specify any special configuration
— it just works, including with Open WebUI 🚀

<img src="/public/media/openwebui_show_vision_capabilities_in_action.webp"
     alt="Open WebUI showing vision capabilities in action."
     loading="lazy" />

[1]: https://github.com/ggml-org/llama.cpp/discussions/22190
[2]: https://huggingface.co/unsloth/Qwen3.8-27B-GGUF
[3]: https://huggingface.co/unsloth/Qwen3.8-27B-GGUF/blob/main/mmproj-BF16.gguf
