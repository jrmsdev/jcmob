#!/bin/sh -xue
APK=${1:-'app/build/outputs/apk/debug/app-debug.apk'}
ADB=${ANDROID_HOME}/platform-tools/adb
exec ${ADB} install -r -d ${APK}
