// IPowerService.aidl
package cn.bjtu.power.powercommon;

import cn.bjtu.power.powercommon.PowerParams;
import cn.bjtu.power.powercommon.PowerResult;
// Declare any non-default types here with import statements

interface IPowerService {
    long powJR(in long nb, in long pow);
    long powJI(in long nb, in long pow);
    long powNR(in long nb, in long pow);
    long powNI(in long nb, in long pow);
    PowerResult pow(in PowerParams params);
}
