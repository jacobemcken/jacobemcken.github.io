## Howto run locally

Making Jekyll build and serve GitHub Pages is "impossible" to get to work
locally on Fedora. Docker to the rescue.

    docker run -v $PWD:/srv/jekyll -v $PWD/vendor/bundle:/usr/local/bundle -p 4000:4000 -it jekyll/jekyll:3 bash
    bundler init
    bundler add github-pages

Mixing up `bundle` and `bundler` had me chasing my own tail for hours, giving me
a backtrace starting with:

    Errno::EACCES: Permission denied @ rb_sysopen - /srv/jekyll/Gemfile

When "serving" Jekyll will automatically watch for file changes and rebuild. The
browser will connect back to the Jekyll server on port `35729`, on which
notifications about updates will be published.

    docker run -v $PWD:/srv/jekyll \
               -v $PWD/vendor/bundle:/usr/local/bundle \
               -p 4000:4000 \
               -p 35729:35729 \
               jekyll/jekyll:3 jekyll serve --livereload


## Address security issues

Update the `Gemfile` with the newest version of `github-pages` from https://rubygems.org/gems/github-pages

Start the Docker container with the command from above, and run the following inside:

    bundler update
