package com.mika.newcode.models;

import com.google.gson.annotations.SerializedName;/*
import com.parallel6.captivereachconnectsdk.jsonmodel.BaseJSon;
import com.parallel6.captivereachconnectsdk.models.MobileMenu;
import com.parallel6.captivereachconnectsdk.models.User;*/

import java.util.List;

/**
 * Created by carlosbedoya on 10/14/13.
 */
public class AllData {
    @SerializedName("user")
    private List<User> users;
    @SerializedName("event")
    private List<Event> events;
    @SerializedName("meeting")
    private List<Meeting> meetings;
    @SerializedName("gift")
    private List<Gift> gifts;
    @SerializedName("gift_role")
    private List<GiftRole> giftRoles;
    @SerializedName("role")
    private List<Role> roles;
    @SerializedName("user_role")
    private List<UserRole> userRoles;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.users = users;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }


    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts= gifts;
    }

    public List<GiftRole> getGiftRoles() {
        return giftRoles;
    }

    public void setGiftRoles(List<GiftRole> giftRoles) {
        this.giftRoles = giftRoles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> giftRoles) {
        this.userRoles = userRoles;
    }

}
