LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES:= main.c
LOCAL_MODULE := hello_world
include $(BUILD_EXECUTABLE)
