package cn.bjtu.power.powercommon;

/**
 * Created by ftb on 14-12-23.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class PowerResult implements Parcelable{

    private final long res;

    private final long time;

    public PowerResult(long r, long t) {
        this.res = r;
        this.time = t;
    }

    public long getResult() {
        return res;
    }

    public long getTimeInMillis() {
        return time;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(this.res);
        parcel.writeLong(this.time);
    }

    public static final Creator<PowerResult> CREATOR = new Creator<PowerResult>() {
        public PowerResult createFromParcel(Parcel in) {
            long r = in.readLong();
            long t = in.readLong();
            return new PowerResult(r,t);
        }

        public PowerResult[] newArray(int size) {
            return new PowerResult[size];
        }
    };
}
