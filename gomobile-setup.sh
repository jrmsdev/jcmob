#!/bin/sh -eux
# ref: https://github.com/mpl/go4droid/blob/master/Dockerfile

#export ANDROID_HOME=/opt/android/sdk

#go get -d -u golang.org/x/mobile/cmd/gomobile
go clean -i ./vendor/golang.org/x/mobile/cmd/gomobile
go install -i ./vendor/golang.org/x/mobile/cmd/gomobile

#go get -d -u golang.org/x/mobile/cmd/gobind
go clean -i ./vendor/golang.org/x/mobile/cmd/gobind
go install -i ./vendor/golang.org/x/mobile/cmd/gobind

gomobile clean || true
echo gomobile init -ndk $ANDROID_HOME/ndk-bundle

exit 0
