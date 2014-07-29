package com.mika.newcode.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class Event {
    @SerializedName("eid")
    protected int eid;
    @SerializedName("uid")
    protected int uid;
    @SerializedName("joined")
    protected int joined;
    @SerializedName("joined_time")
    protected String joined_time;
    @SerializedName("mid")
    protected int mid;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }


    public int getJoined() {
        return joined;
    }

    public void setJoined(int joined) {
        this.joined = joined;
    }


    public String getJoined_time() {
        return joined_time;
    }

    public void setJoined_time(String joined_time) {
        this.joined_time = joined_time;
    }


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }


}