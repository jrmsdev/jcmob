#!/bin/sh -eux
# ref: https://github.com/mpl/go4droid/blob/master/Dockerfile

export GOPATH=/opt/golang
export ANDROID_HOME=/opt/android/sdk

go get -d -u golang.org/x/mobile/cmd/gomobile
go clean -i golang.org/x/mobile/cmd/gomobile
go install golang.org/x/mobile/cmd/gomobile

go get -d -u golang.org/x/mobile/cmd/gobind
go clean -i golang.org/x/mobile/cmd/gobind
go install golang.org/x/mobile/cmd/gobind

gomobile clean
gomobile init -ndk $ANDROID_HOME/ndk-bundle

exit 0
