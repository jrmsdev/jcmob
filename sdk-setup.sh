#!/bin/sh -eux

SDK_FILE=sdk-tools-linux-3859397.zip
SDK_FILE_HASH=444e22ce8ca0f67353bda4b85175ed3731cae3ffa695ca18119cbacef1c1bea0
SDK_URL=https://dl.google.com/android/repository/$SDK_FILE
PLATFORMS='android-23 android-24 android-27'
BUILD_TOOLS='23.0.3 24.0.3 27.0.2'

export ANDROID_HOME=/opt/android/sdk

mkdir -vp $ANDROID_HOME
cd $ANDROID_HOME
if ! test -s ./tools/bin/sdkmanager; then
    echo "download: $SDK_URL"
    wget -cq $SDK_URL
    echo "$SDK_FILE_HASH $SDK_FILE" | sha256sum -c
    unzip $SDK_FILE
fi
echo y | ./tools/bin/sdkmanager --update

for plat in $PLATFORMS; do
    ./tools/bin/sdkmanager "platforms;$plat"
done
for btversion in $BUILD_TOOLS; do
    ./tools/bin/sdkmanager "build-tools;$btversion"
done

./tools/bin/sdkmanager 'extras;android;m2repository'
./tools/bin/sdkmanager 'ndk-bundle'
cd - >/dev/null

exit 0
