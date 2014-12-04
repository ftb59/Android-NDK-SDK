LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := main.cpp

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../libhelloworldservice/

LOCAL_CFLAGS += -DPLATFORM_ANDROID

LOCAL_MODULE := helloworldclient
LOCAL_MODULE_TAGS := optional

LOCAL_PRELINK_MODULE := false

LOCAL_SHARED_LIBRARIES += liblog
LOCAL_SHARED_LIBRARIES += libutils libui libbinder
LOCAL_SHARED_LIBRARIES += libhelloworldservice

LOCAL_CFLAGS += -Idalvik/libnativehelper/include/nativehelper

include $(BUILD_EXECUTABLE)
