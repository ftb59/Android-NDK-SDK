package cn.bjtu.power.powerservice;

/**
 * Created by ftb on 14-12-23.
 */
public class powerCalculate {
    public static long powerJR(long nb, long pow)
    {
        long tmp;

        if (pow == 0)
            return (1);
        if (pow % 2 == 0)
        {
            tmp = powerJR(nb, pow / 2);
            return (tmp * tmp);
        }
        else
            return (nb * powerJR( nb, pow - 1));
    }

    public static long powerJI(long nb, long pow)
    {
        long  result = 1;

        while (pow != 0)
        {
            if ((pow & 1) != 0)
                result *= nb;
            pow >>= 1;
            nb *= nb;
        }
        return result;
    }

    public native static long powerNR(long nb, long pow);
    public native static long powerNI(long nb, long pow);


    static {
        System.loadLibrary("NativePower");
    }

}
