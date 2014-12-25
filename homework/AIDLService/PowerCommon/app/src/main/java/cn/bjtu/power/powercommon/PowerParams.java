package cn.bjtu.power.powercommon;

/**
 * Created by ftb on 14-12-23.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class PowerParams implements Parcelable {
    public static enum FunctionCalled
    {
        RECURSIVE_JAVA, ITERATIVE_JAVA, RECURSIVE_NATIVE, ITERATIVE_NATIVE
    }

    public PowerParams(long nb, long pow, FunctionCalled f)
    {
        number = nb;
        power = pow;
        func = f;
    }

    private final long number;
    private final long power;
    private final FunctionCalled func;

    public long getNumber() {
        return number;
    }

    public long getPower() {
        return power;
    }

    public FunctionCalled getFunc() {
        return func;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(this.number);
        parcel.writeLong(this.power);
        parcel.writeInt(this.func.ordinal());
    }

    public static final Creator<PowerParams> CREATOR = new Creator<PowerParams>() {
        public PowerParams createFromParcel(Parcel in) {
            long n = in.readLong();
            long pow = in.readLong();
            FunctionCalled func = FunctionCalled.values()[in.readInt()];
            return new PowerParams(n, pow, func);
        }

        public PowerParams[] newArray(int size) {
            return new PowerParams[size];
        }
    };
}
