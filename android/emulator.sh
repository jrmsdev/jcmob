#!/bin/sh
exec android-emulator @4.65_720p_Galaxy_Nexus_Edited_API_26 \
	-gpu off \
	-logcat 'ActivityManager:e AndroidRuntime:e' \
	-netfast \
	-accel on \
	-no-boot-anim
