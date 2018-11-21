set -eux
if test ! -s ./libs/jcmob.aar; then
  mkdir -p ./libs
  gomobile bind -x -target android -javapkg go -o ./libs/jcmob.aar github.com/jrmsdev/jcmob
fi
