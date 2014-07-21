package com.mika.newcode.models;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class Meeting{
    @SerializedName("mid")
    protected int mid;
    @SerializedName("name")
    protected String name;


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}