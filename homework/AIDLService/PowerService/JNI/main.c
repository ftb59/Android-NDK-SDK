#include "pow.h"

JNIEXPORT jlong JNICALL Java_cn_bjtu_power_powerservice_powerCalculate_powerNI(JNIEnv *env, jclass clazz, jlong nb, jlong pow)
{
  jlong  result = 1;

  while (pow)
    {
      if (pow & 1)
	result *= nb;
      pow >>= 1;
      nb *= nb;
    }
  return result;
}

JNIEXPORT jlong JNICALL Java_cn_bjtu_power_powerservice_powerCalculate_powerNR(JNIEnv *env, jclass clazz, jlong nb, jlong pow)
{
  jlong tmp;
 
  if (pow == 0)
    return (1);
  if (pow % 2 == 0)
    {
      tmp = Java_cn_bjtu_power_powerservice_powerCalculate_powerNR(env, clazz, nb, pow / 2);
      return (tmp * tmp);
    } 
  else
    return (nb * Java_cn_bjtu_power_powerservice_powerCalculate_powerNR(env, clazz, nb, pow - 1));
}
