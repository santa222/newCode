package com.mika.newcode.models;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class Meeting extends CRModel{
    @SerializedName("mid")
    protected int mid;


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

}