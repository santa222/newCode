package com.mika.newcode.models;

import android.os.Parcel;
import android.os.Parcelable;

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


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(name);
        dest.writeString(account);
        dest.writeString(company);
       // dest.writeParcelable(image, flags);
    }

    private static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return null;
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