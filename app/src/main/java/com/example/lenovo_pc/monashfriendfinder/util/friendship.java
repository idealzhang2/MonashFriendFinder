package com.example.lenovo_pc.monashfriendfinder.util;

/**
 * Created by lenovo-pc on 2018/4/23.
 */

public class friendship {
    private String friendid;
    private String sid;
    private String friendshipid;

    public friendship(String friendid, String sid, String friendshipid, String sdate, String edate) {
        this.friendid = friendid;
        this.sid = sid;
        this.friendshipid = friendshipid;
        this.sdate = sdate;
        this.edate = edate;
    }

    public String getFriendid() {
        return friendid;
    }

    public friendship() {
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFriendshipid() {
        return friendshipid;
    }

    public void setFriendshipid(String friendshipid) {
        this.friendshipid = friendshipid;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    private String sdate;
    private String edate;
}
