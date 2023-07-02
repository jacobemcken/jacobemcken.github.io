---
layout: post
title: MongoDB profiling
categories:
- Programming
- Database
tags:
- Database
- Mongo
- Docker
- Profiling
comments: true
---

When running MongoDB in Docker, diagnostics logs are sent to `STDOUT` by
default. Since MongoDB version 4.4 these logs have been in a structured JSON
format.

Use the Docker command to export Docker container logs (including the MongoDB
ones) and `jq` to sanitize all non JSON logs (from potential other processes in
the container):

    docker logs mongo_container_id \
     | jq -R "fromjson? | . " -c \
     > mongo_diagnostics.json.log

**Notice:** The `mongo_container_id` can be found using `docker container ls`.

Convert the sparkly "new" 4.4 JSON format log to the old legacy format using
[`convert-json-logs-to-legacy`-tool][m1], because the [log analyzer doesn't
support JSON][m2]:

    generate_mplot_logs.py --log mongo_diagnostics.json.log \
    > mongo_diagnostics.log

Now analyze the logs using `mtools`:

    mloginfo --queries mongo_diagnostics.log


I don't have a ton of profiling experience myself... looking forward to see
where this will take me.

[m1]: https://github.com/ricojf-mongodb/convert-json-logs-to-legacy
[m2]: https://github.com/rueckstiess/mtools/issues/806
[m3]: https://github.com/rueckstiess/mtools