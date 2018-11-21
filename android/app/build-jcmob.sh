set -eu
mkdir -p ./libs
if test -s ./libs/jcmob.aar; then
  echo './libs/jcmob.aar already built!'
else
  gomobile bind -target android -javapkg go -o ./libs/jcmob.aar ./../../../jcmob
fi
