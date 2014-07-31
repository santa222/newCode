package com.mika.newcode.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class CRModel  implements Parcelable {
    @SerializedName("name")
    protected String name;

    public String getName() {
        if(name==null) name="";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final Creator<CRModel> CREATOR = new Creator<CRModel>() {
        @Override
        public CRModel createFromParcel(Parcel source) {

            return null;
        }

        @Override
        public CRModel[] newArray(int size) {
            return new CRModel[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}