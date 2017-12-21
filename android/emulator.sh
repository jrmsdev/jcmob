#!/bin/sh
exec ${ANDROID_HOME}/emulator/emulator @4.65_720p_Galaxy_Nexus_Edited_API_26 \
	-gpu off \
	-logcat 'ActivityManager:E,AndroidRuntime:E' \
	-netfast \
	-accel on \
	-no-snapshot \
	-no-boot-anim $@
