---
layout: post
title: 'Rant: Updating Samsung NVMe firmware uncessarily complicated'
image: /assets/img/nvme_hard_disk_floating_above_motherboard.webp
image_alt: "Midjourney prompt: Poor user experience during debugging a NVMe hard disk being detached while the system runs."
description: "In this day and age, I had assumed updating firmware on a Linux system would be a breeze, but a Samsung NVMe SSD took me back to the \"good old days\"."
categories:
- Programming
tags:
- thisisstupid
- rant
- harddisk
- nvme
- firmware
- Samsung
comments: true
excerpt_separator: <!--more-->
---

The other day, I was taken back to the "good old days", and not in a good way.
I had assumed that a major hardware provider like Samsung nowadays
would ensure a decent user experience during firmware updates,
regardless of the operating system.
But I was in for a disappointment.

My home server running Linux has been crashing occasionally for a while.
But since a reboot would temporarily "fix" it, sometimes for weeks,
I never prioritized finding the underlying problem... until now.
It feels like a recent kernel update now triggers the crash more often,
but I have no data to back this statement up.
<!--more-->

The operating system would crash because the hard disk
(Samsung NVMe SSD 990 PRO) would suddenly get detached,
causing <abbr title="Input/Output">I/O</abbr> errors.

I would find something like the following when attaching a monitor to the server:

```
[118436.628670] nvme nvme0: Device not ready; aborting reset, CSTS=0x1
[118456.644680] nvme nvme0: Device not ready; aborting reset, CSTS=0x1
[118456.650372] Buffer I/O error on dev dm-0, logical block 3670071, lost async page write
[118456.650381] Buffer I/O error on dev dm-1, logical block 348127422, lost async page write
[118456.650385] Buffer I/O error on device dm-1, logical block 348684822
[118456.650388] Buffer I/O error on device dm-1, logical block 347147799
[118456.650391] Buffer I/O error on device dm-1, logical block 37148757
[118456.650398] Buffer I/O error on device dm-0, logical block 3899415
[118456.650401] Buffer I/O error on device dm-1, logical block 37148758
[118456.650402] Buffer I/O error on device dm-0, logical block 14060845
[118456.650403] Buffer I/O error on device dm-0, logical block 14060846
[118456.650404] Buffer I/O error on device dm-1, logical block 348684823
[118456.650411] Buffer I/O error on device dm-1, logical block 348695187
[118456.650419] Buffer I/O error on dev dm-1, logical block 350750461, lost async page write
[118456.650424] Aborting journal on device dm-0-8.
[118456.650431] Aborting journal on device dm-1-8.
[118456.650438] EXT4-fs (dm-1): Delayed block allocation failed for inode 86776714 at logical offset 2375
[118456.650438] EXT4-fs error (device dm-1) in ext4_new_inode:1139: Journal has aborted
[118456.650441] EXT4-fs (dm-1): This should not happen!! Data will be lost
[118456.650443] Buffer I/O error on dev dm-1, logical block 289159200, lost sync page write
[118456.650443] EXT4-fs error (device dm-0) in ext4_orphan_add:1885: Journal has aborted
[118456.650445] JBD2: I/O error when updating journal superblock for dm-0-8.
[118456.650446] JBD2: I/O error when updating journal superblock for dm-1-8.
[118456.650446] EXT4-fs error (device dm-1) in ext4_da_writepages:2724: Journal has aborted
[118456.650448] EXT4-fs error (device dm-0) in __ext4_new_inode:1096: Journal has aborted
[118456.650448] EXT4-fs error (device dm-0) in ext4_reserve_inode_write:5792: IO failure
[118456.650452] EXT4-fs error (device dm-0) in ext4_dirty_inode:5996: inode #11796551 comm kworker/u32:2: mark_inode_dirty error
[118456.650454] EXT4-fs error (device dm-1) in ext4_da_do_write_end:3036: inode #8729395 comm dockerd: mark_inode_dirty error
[118456.650455] EXT4-fs error (device dm-1) in __ext4_new_inode:1096: Journal has aborted
[118456.650457] EXT4-fs error (device dm-0) in ext4_create:2893: Journal has aborted
[118456.650459] EXT4-fs error (device dm-1) in ext4_dirty_inode:5997: IO failure
[118456.650462] EXT4-fs error (device dm-1) in ext4_da_do_write_end:3037: IO failure
[118456.650466] EXT4-fs error (device dm-1) in ext4_journal_check_start:94: comm kworker/u32:4: Detected aborted journal
[118456.650467] EXT4-fs error (device dm-0) in ext4_reserve_inode_write:5792: Journal has aborted
[118456.650473] EXT4-fs error (device dm-0) in ext4_dirty_inode:5996: inode #11796551 comm runc: mark_inode_dirty error
[118456.650478] Buffer I/O error on dev dm-1, logical block 0, lost sync page write
[118456.650489] Buffer I/O error on dev dm-0, logical block 0, lost sync page write
[118456.650493] EXT4-fs (dm-0): I/O error while writing superblock
[118456.650495] EXT4-fs (dm-0): Remounting filesystem read-only
[118456.650502] Buffer I/O error on dev dm-0, logical block 0, lost sync page write
[118456.650504] EXT4-fs (dm-1): I/O error while writing superblock
[118456.650523] EXT4-fs (dm-1): Remounting filesystem read-only
[118456.650530] Buffer I/O error on dev dm-1, logical block 0, lost sync page write
[118456.670725] systemd[1]: systemd-journald.service: Failed to spawn executor: Input/output error
[118456.692247] systemd[1]: Failed to start systemd-journald.service - Journal Service.
[118456.692260] EXT4-fs (nvme0n1p2): shut down requested (2)
[118456.692275] Aborting journal on device nvme0n1p2-8.
[118456.692287] Buffer I/O error on dev nvme0n1p2, logical block 262144, lost sync page write
[118456.669560] JBD2: I/O error when updating journal superblock for nvme0n1p2-8.
[118456.698566] systemd[1]: systemd-journald.service: Failed to spawn executor: Input/output error
[118456.671292] systemd[1]: Failed to start systemd-journald.service - Journal Service.
[118456.671572] systemd[1]: systemd-journald.service: Failed to spawn executor: Input/output error
[118456.672866] systemd[1]: Failed to start systemd-journald.service - Journal Service.
[118456.673176] systemd[1]: systemd-journald.service: Failed to spawn executor: Input/output error
[118456.674577] systemd[1]: Failed to start systemd-journald.service - Journal Service.
[118456.674843] systemd[1]: Failed to start systemd-journald.service - Journal Service.
[118456.700416] systemd[1]: snapd.service: Failed to spawn executor: Input/output error
[118456.753182] systemd[1]: snapd.service: Failed to spawn executor: Input/output error
[118456.503424] systemd[1]: snapd.service: Failed to spawn executor: Input/output error
[118456.757254] systemd[1]: Failed to start snapd.service - Snap Daemon.
[118456.803496] systemd[1]: snapd.service: Failed to spawn executor: Input/output error
[118456.822230] systemd[1]: snapd.service: Failed to spawn executor: Input/output error
[118458.253527] systemd[1]: snapd.failure.service: Failed to spawn executor: Input/output error
[118458.253934] systemd[1]: Failed to start snapd.failure.service - Failure handling of the snapd snap.
[126882.327293] systemd[1]: apt-daily.service: Failed to spawn executor: Input/output error
[126882.327543] systemd[1]: Failed to start apt-daily.service - Daily apt download activities.
```

I suspected either the hard disk controller (PCIe) or the hard disk itself to be the culprit,
and neither would be easy for me to verify and reproduce consistently.
I've simply had very little experience with hardware debugging
over the last decade or so.

I started searching for ways to quickly reproduce the problem
using both old-school internet searches and asking LLMs.
It is never fun to wait for days to verify whether a fix worked.
Just like with programming, it is all about making the feedback loop as short as possible.

I found suggestions for doing heavy write operations using `fio`,
and creating a "Reset storm" among others -  all very interesting.
But I also found people complaining about [hard disks with faulty firmware][1]
causing problems with a smell similar to my own.

The obvious first step was to check whether my SSD's firmware version `5B2QJXD7` was the latest.
But Samsung does not seem to have any public changelog for their firmware - ⛔ fail 1.  
I think it is very important to be able to find information about
when firmware versions were released and what changes they contain.

They are also very eager to redirect me to a page explaining that I should install the Samsung Magician software.
Software that only works for Windows, Mac, and Android - ⛔ fail 2.

Their support chatbot was a bit limited but it did manage to redirect me to a page where I could find [ISOs to create a bootable USB stick][4] - ✅ success 1.  
But the ISO only worked with some freeware software (UNetbootin),
because it does some magic after the files are written to the USB stick - ⛔ fail 3.  
For decades, it has been possible to write ISOs directly to USB sticks (and CDs) and boot from them without postprocessing.
It does require you to create the ISOs correctly, though 😉

To be fair, I was able to update the firmware without using Windows - ✅ success 2.  
But with the excellent piece of [software, for updating firmware][3] on all sorts of hardware,
provided by the Linux community, it should not be this difficult - ⛔ fail 4.  

To wrap up this rant:

> Dear Samsung,
>
> Please provide information about firmware history of your products publicly.
>
> Please also distribute your firmware via `fwupdmgr`,
> which works with ALL major Linux distributions.
> But if that is impossible for whatever reason, then create bootable firmware ISOs
> that do not require me to install any third-party (freeware) software.  
> ― It should simply not be needed in this day and age.
>
> Best regards,
> Jacob

I hope the update have stabilized my server,
it has been running for a couple of days now. 🤞


[1]: https://edbzn.dev/fixing-filesystem-read-only-with-samsung-ssd/
[2]: https://www.samsung.com/ca/support/memory-storage/update-the-firmware-of-your-samsung-ssd/
[3]: https://fwupd.org/
[4]: https://semiconductor.samsung.com/consumer-storage/support/tools/