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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<Event> geEvents() {
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
}
