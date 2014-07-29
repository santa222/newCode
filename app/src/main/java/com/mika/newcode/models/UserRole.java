package com.mika.newcode.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class UserRole {
    @SerializedName("uid")
    protected int uid;
    @SerializedName("rid")
    protected int rid;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }



}