set -eu
mkdir -p ./libs
if test -s ./libs/jcmob.aar; then
  echo './libs/jcmob.aar already built!'
else
  go install github.com/jrmsdev/go-jcms/lib/jcms
  go install github.com/jrmsdev/jcmob
  gomobile bind -target android -javapkg go -o ./libs/jcmob.aar github.com/jrmsdev/jcmob
fi
