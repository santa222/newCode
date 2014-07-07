package com.mika.newcode.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class Event {
    @SerializedName("uid")
    protected String uid;
    @SerializedName("joined")
    protected String joined;
    @SerializedName("joined_time")
    protected String joined_time;
    @SerializedName("mid")
    protected String mid;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }


    public String getJoined_time() {
        return joined_time;
    }

    public void setJoined_time(String joined_time) {
        this.joined_time = joined_time;
    }


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }


}