#include <jni.h>

#ifndef _Included_cn_bjtu_power_powerservice_powerCalculate_PowLib
#define _Included_cn_bjtu_power_powerservice_powerCalculate_PowLib
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jlong JNICALL Java_cn_bjtu_power_powerservice_powerCalculate_powerNI
(JNIEnv *, jclass, jlong, jlong);

JNIEXPORT jlong JNICALL Java_cn_bjtu_power_powerservice_powerCalculate_powerNR
(JNIEnv *, jclass, jlong, jlong);

#ifdef __cplusplus
}
#endif
#endif
