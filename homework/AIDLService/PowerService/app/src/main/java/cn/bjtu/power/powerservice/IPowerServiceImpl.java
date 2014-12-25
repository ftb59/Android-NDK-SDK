package cn.bjtu.power.powerservice;

/**
 * Created by ftb on 14-12-23.
 */

import android.os.SystemClock;
import  cn.bjtu.power.powercommon.PowerParams;
import  cn.bjtu.power.powercommon.PowerResult;
import  cn.bjtu.power.powercommon.IPowerService;

public class IPowerServiceImpl extends  IPowerService.Stub {
    private static final String TAG = "IPowerServiceImpl";

    @Override
    public long powJR(long l, long l2){
        return powerCalculate.powerJR(l, l2);
    }

    @Override
    public long powJI(long l, long l2)  {
        return powerCalculate.powerJI(l,l2);
    }

    @Override
    public long powNR(long l, long l2)  {
        return powerCalculate.powerNR(l, l2);
    }

    @Override
    public long powNI(long l, long l2) {
        return powerCalculate.powerNI(l, l2);
    }

    @Override
    public PowerResult pow(PowerParams powerParams)  {
        long timeInMillis = SystemClock.uptimeMillis();
        long result;
        switch (powerParams.getFunc()) {
            case ITERATIVE_JAVA:
                result = this.powJI(powerParams.getNumber(), powerParams.getPower());
                break;
            case RECURSIVE_JAVA:
                result = this.powJR(powerParams.getNumber(), powerParams.getPower());
                break;
            case ITERATIVE_NATIVE:
                result = this.powNI(powerParams.getNumber(), powerParams.getPower());
                break;
            case RECURSIVE_NATIVE:
                result = this.powNR(powerParams.getNumber(), powerParams.getPower());
                break;
            default:
                return null;
        }
        timeInMillis = SystemClock.uptimeMillis() - timeInMillis;
        return new PowerResult(result, timeInMillis);
    }
}
