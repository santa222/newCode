package com.mika.newcode.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class User extends CRModel{
    @SerializedName("uid")
    protected int uid;
    @SerializedName("account")
    protected String account;
    @SerializedName("company")
    protected String company;
    @SerializedName("mail")
    protected String mail;
    @SerializedName("mobile_phone")
    protected String mobile_phone;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getCompany() {
        if(company==null) company="";
        return company;
    }

    public void setCompany(String company) {
            this.company = company;
    }

    public String getAccount() {
        if(account==null) account="";
        return account;
    }

    public void setAccount(String account) {
            this.account = account;
    }

    public String getMail() {
        if(mail==null) mail="";
        return mail;
    }

    public void setMail(String email) {
            this.mail = email;
    }

    public String getMobilePhone() {
        if(mobile_phone==null) mobile_phone="";
        return mobile_phone;
    }

    public void setMobilePhone(String mobile_phone) {
         this.mobile_phone = mobile_phone;
    }



}