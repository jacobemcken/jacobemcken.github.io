## Howto run locally

Making Jekyll build and serve GitHub Pages is "impossible" to get to work
locally on Fedora. Docker to the rescue.

    docker run -v $PWD:/srv/jekyll -v $PWD/vendor/bundle:/usr/local/bundle -p 4000:4000 -it jekyll/jekyll:3 bash
    bundler init
    bundler add github-pages

Mixing up `bundle` and `bundler` had me chasing my own tail for hoursm giving me
a backtrace starting with:

    Errno::EACCES: Permission denied @ rb_sysopen - /srv/jekyll/Gemfile

When "serving" Jekyll will automatically watch for file changes and rebuild.

    docker run -v $PWD:/srv/jekyll -v $PWD/vendor/bundle:/usr/local/bundle -p 4000:4000 jekyll/jekyll:3 jekyll serve -I
