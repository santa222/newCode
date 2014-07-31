package com.mika.newcode.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mika_1990 on 14-7-3.
 */
public class User extends CRModel implements Parcelable{
    @SerializedName("uid")
    protected int uid;
    @SerializedName("company")
    protected String company;
    @SerializedName("account")
    protected String account;
    @SerializedName("email")
    protected String email;
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

    public String getEmail() {
        if(email==null) email="";
        return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String getMobilePhone() {
        if(mobile_phone==null) mobile_phone="";
        return mobile_phone;
    }

    public void setMobilePhone(String mobile_phone) {
         this.mobile_phone = mobile_phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeInt(uid);
        dest.writeString(company);
        dest.writeString(account);
        dest.writeString(email);
        dest.writeString(mobile_phone);
        Log.v("222","writeToParcel: ");
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            User aUser=new User();
            aUser.setUid(source.readInt());
            aUser.setCompany(source.readString());
            aUser.setAccount(source.readString());
            aUser.setEmail(source.readString());
            aUser.setMobilePhone(source.readString());
            Log.v("222","user.uid: "+aUser.getUid()+"company: "+aUser.getCompany());
            return aUser;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

/*


    public boolean equals(MobileMenu menu) {
        if (this.getTitle() == null || this.getDescription() == null || this.getAction() == null || this.getActionObject() == null || this.getImage() == null || this.getContent_type() == null)
            return false;

        return this.getId() == menu.getId() &&
                this.getTitle().equals(menu.getTitle()) &&
                this.getDescription().equals(menu.getDescription()) &&
                this.getAction().equals(menu.getAction()) &&
                this.getActionObject().equals(menu.getActionObject()) &&
                this.getImage().equals(menu.getImage()) &&
                this.getContent_type().equals(menu.getContent_type());
    }
*/

}