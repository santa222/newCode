package com.mika.newcode.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class GiftRole {
    @SerializedName("gid")
    protected int gid;
    @SerializedName("rid")
    protected int rid;


    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }



}