LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := main.c

LOCAL_CFLAGS += -DPLATFORM_ANDROID


LOCAL_MODULE := libNativePower
LOCAL_MODULE_TAGS := optional

LOCAL_PRELINK_MODULE := false

LOCAL_SHARED_LIBRARIES += liblog
LOCAL_SHARED_LIBRARIES += libutils libui
LOCAL_SHARED_LIBRARIES += libbinder

LOCAL_CFLAGS += -Idalvik/libnativehelper/include/nativehelper

#include $(PREBUILT_SHARED_LIBRARY)
include $(BUILD_SHARED_LIBRARY)
