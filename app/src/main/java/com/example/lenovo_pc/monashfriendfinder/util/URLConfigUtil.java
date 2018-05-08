package com.example.lenovo_pc.monashfriendfinder.util;

/**
 * Created by lenovo-pc on 2018/4/12.
 */

public class URLConfigUtil {
    private static String  URLMainStudentpr = "/FriendshipDB/webresources/friend.studentpr";
    private static String  URLMainLocation = "/FriendshipDB/webresources/friend.location";
    private static String URLMainFriendShip = "/FriendshipDB/webresources/friend.friendship";



    public static void setURLfriends(String URLfriends) {
        URLConfigUtil.URLfriends = URLfriends;
    }

    //studentpr
    private static  String  URLfriends = "/FriendshipDB/webresources/friend.studentpr/friends";
    private static String  URLfindByStuid = "/FriendshipDB/webresources/friend.studentpr/findByStuid";
    private static String  URLfindByMoemail = "/FriendshipDB/webresources/friend.studentpr/findByMoemail";
    private  static  String URLfindByFunit = "/FriendshipDB/webresources/friend.studentpr/findByFunit";
    private static String  URLfindBySurname = "/FriendshipDB/webresources/friend.studentpr/findBySurname";
    private static String URLfindByCurjob = "/FriendshipDB/webresources/friend.studentpr/findByCurjob";
    private static String URLFromTo = "/FriendshipDB/webresources/friend.studentpr";
    private static String URLfindBySutime = "/FriendshipDB/webresources/friend.studentpr/findBySutime";
    private static String URLfindByStumode = "/FriendshipDB/webresources/friend.studentpr/findByStumode";
    private static String URLid = "/FriendshipDB/webresources/friend.studentpr";
    private static String URLfindByFmovie = "/FriendshipDB/webresources/friend.studentpr/findByFmovie";
    private static String URLfindByPwd = "/FriendshipDB/webresources/friend.studentpr/findByPwd";
    private static String URLfindByDETAIL = "/FriendshipDB/webresources/friend.studentpr/findByDETAIL";
    private static String URLfindByDob = "/FriendshipDB/webresources/friend.studentpr/findByDob";
    private static String URLfindByManyAttributes = "/FriendshipDB/webresources/friend.studentpr/findByManyAttributes";
    private static String URLfindByFirname = "/FriendshipDB/webresources/friend.studentpr/findByFirname";
    private static String URLfindByCourse = "/FriendshipDB/webresources/friend.studentpr/findByCourse";
    private static String URLfindBySuburb = "/FriendshipDB/webresources/friend.studentpr/findBySuburb";
    private static String URLfindByGender = "/FriendshipDB/webresources/friend.studentpr/findByGender";
    private static String URLfindByFsport = "/FriendshipDB/webresources/friend.studentpr/findByFsport";
    private static String URLcount = "/FriendshipDB/webresources/friend.studentpr/count";
    private static String URLfindByNationality = "/FriendshipDB/webresources/friend.studentpr/findByNationality";
    private static String URLfindBySudate = "/FriendshipDB/webresources/friend.studentpr/findBySudate";
    private static String URLfindByUNIT = "/FriendshipDB/webresources/friend.studentpr/findByUNIT";
    private static String URLfindByAddress = "/FriendshipDB/webresources/friend.studentpr/findByAddress";
    private static String URLfindByNalanguage = "/FriendshipDB/webresources/friend.studentpr/findByNalanguage";
    //location
    private static String URLfindBylocaStuidd = "/FriendshipDB/webresources/friend.location/findByStuidd";
    private static String URLfindByLocname = "/FriendshipDB/webresources/friend.location/findByLocname";
    private static String URLfindByLocationid = "/FriendshipDB/webresources/friend.location/findByLocationid";
    private static String URLLocaid = "/FriendshipDB/webresources/friend.location";
    private static String URLfindByDIS = "/FriendshipDB/webresources/friend.location/findByDIS";
    private static String URLfindByDate = "/FriendshipDB/webresources/friend.location/findByDate";
    private static String URLfindByLatitude = "/FriendshipDB/webresources/friend.location/findByLatitude";
    private static String URLfindByTime = "/FriendshipDB/webresources/friend.location/findByTime";
    private static String URLfindByLongitude = "/FriendshipDB/webresources/friend.location/findByLongitude";
    private static String URLfindByLikeLocname = "/FriendshipDB/webresources/friend.location/findByLikeLocname";
    private static String URLfindBySurName = "/FriendshipDB/webresources/friend.location/findBySurName";
    private static String URLfindByVISIT = "/FriendshipDB/webresources/friend.location/findByVISIT";
    private static String URLLocacount = "/FriendshipDB/webresources/friend.location/count";
    private static String  URLocaLFromTo = "/FriendshipDB/webresources/friend.location";
    private static String  URLCheckAccount = "/FriendshipDB/webresources/friend.studentpr/findByCheckAccount";


    private static String  URLupdateprofile = "/FriendshipDB/webresources/friend.studentpr/updateprofile";
    //friendship
    private static String URLfriendmap = "/FriendshipDB/webresources/friend.location/findfriendmap";
    private static String URLfindBySstuid = "/FriendshipDB/webresources/friend.friendship/findBySstuid";
    private static String URLfindByFriendshipSurname = "/FriendshipDB/webresources/friend.friendship/findBySurname";
    private static String URLfindByFriendshipEdate = "/FriendshipDB/webresources/friend.friendship/findByEdate";
    private static String URLfindByStuidd = "/FriendshipDB/webresources/friend.friendship/findByStuidd";
    private static String URLByFriendid = "/FriendshipDB/webresources/friend.friendship/findByFriendid";
    private static String URLfindBySdate = "/FriendshipDB/webresources/friend.friendship/findBySdate";
    private static String URLfindByFriendshipid = "/FriendshipDB/webresources/friend.friendship/findByFriendshipid";
    private static String URLdeletefriend = "/FriendshipDB/webresources/friend.friendship/deletefriend";
    private static String URLadduser = "/FriendshipDB/webresources/friend.studentpr/adduser";
    private  static String URLfriend = "/FriendshipDB/webresources/friend.friendship/Insertfriend";
    private static String URLMap = "/FriendshipDB/webresources/friend.location/findMapInfo";
    private static String  URLfindpiechart = "/FriendshipDB/webresources/friend.studentpr/findPieChart";
    public static String getURLfindpiechart() {
        return URLfindpiechart;
    }

    public static void setURLfindpiechart(String URLfindpiechart) {
        URLConfigUtil.URLfindpiechart = URLfindpiechart;
    }
    public static String getURLupdateprofile() {
        return URLupdateprofile;
    }
    public static String getURLfriends() {
        return URLfriends;
    }
    public static void setURLupdateprofile(String URLupdateprofile) {
        URLConfigUtil.URLupdateprofile = URLupdateprofile;
    }


    public static String getURLfriendmap() {
        return URLfriendmap;
    }

    public static void setURLfriendmap(String URLfriendmap) {
        URLConfigUtil.URLfriendmap = URLfriendmap;
    }


    public static String getURLMainStudentpr() {
        return URLMainStudentpr;
    }

    public static String getURLMainLocation() {
        return URLMainLocation;
    }

    public static String getURLMainFriendShip() {
        return URLMainFriendShip;
    }


    //studentpr:  All methods are used in the studentpr database

    public static String getURLfindByStuid() {
        return URLfindByStuid;
    }


    public static String getURLfindByMoemail() {
        return URLfindByMoemail;
    }

    public static String getURLfindByFunit() {
        return URLfindByFunit;
    }

    public static String getURLfindBySurname() {
        return URLfindBySurname;
    }

    public static String getURLfindByCurjob() {
        return URLfindByCurjob;
    }

    public static String getURLFromTo() {
        return URLFromTo;
    }

    public static String getURLfindBySutime() {
        return URLfindBySutime;
    }

    public static String getURLfindByStumode() {
        return URLfindByStumode;
    }

    public static String getURLid() {
        return URLid;
    }

    public static String getURLfindByFmovie() {
        return URLfindByFmovie;
    }

    public static String getURLfindByPwd() {
        return URLfindByPwd;
    }

    public static String getURLfindByDETAIL() {
        return URLfindByDETAIL;
    }

    public static String getURLfindByDob() {
        return URLfindByDob;
    }

    public static String getURLfindByManyAttributes() {
        return URLfindByManyAttributes;
    }

    public static String getURLfindByFirname() {
        return URLfindByFirname;
    }

    public static String getURLfindByCourse() {
        return URLfindByCourse;
    }

    public static String getURLfindBySuburb() {
        return URLfindBySuburb;
    }

    public static String getURLfindByGender() {
        return URLfindByGender;
    }

    public static String getURLfindByFsport() {
        return URLfindByFsport;
    }

    public static String getURLcount() {
        return URLcount;
    }

    public static String getURLfindByNationality() {
        return URLfindByNationality;
    }

    public static String getURLfindBySudate() {
        return URLfindBySudate;
    }

    public static String getURLfindByUNIT() {
        return URLfindByUNIT;
    }

    public static String getURLfindByAddress() {
        return URLfindByAddress;
    }

    public static String getURLfindByNalanguage() {
        return URLfindByNalanguage;
    }
//location


    public static String getURLfindByLocname() {
        return URLfindByLocname;
    }

    public static String getURLfindByLocationid() {
        return URLfindByLocationid;
    }

    public static String getURLLocaid() {
        return URLLocaid;
    }

    public static String getURLfindByDIS() {
        return URLfindByDIS;
    }

    public static String getURLfindByDate() {
        return URLfindByDate;
    }

    public static String getURLfindByLatitude() {
        return URLfindByLatitude;
    }

    public static String getURLfindByTime() {
        return URLfindByTime;
    }

    public static String getURLfindByLongitude() {
        return URLfindByLongitude;
    }

    public static String getURLfindByLikeLocname() {
        return URLfindByLikeLocname;
    }

    public static String getURLfindBySurName() {
        return URLfindBySurName;
    }

    public static String getURLfindByVISIT() {
        return URLfindByVISIT;
    }

    public static String getURLLocacount() {
        return URLLocacount;
    }

    public static String getURLocaLFromTo() {
        return URLocaLFromTo;
    }

//friendship
    public static String getURLfindBySstuid() {
        return URLfindBySstuid;
    }

    public static String getURLfindByFriendshipSurname() {
        return URLfindByFriendshipSurname;
    }

    public static String getURLfindByFriendshipEdate() {
        return URLfindByFriendshipEdate;
    }

    public static String getURLfindByStuidd() {
        return URLfindByStuidd;
    }

    public static String getURLByFriendid() {
        return URLByFriendid;
    }

    public static String getURLfindBySdate() {
        return URLfindBySdate;
    }

    public static String getURLfindByFriendshipid() {
        return URLfindByFriendshipid;
    }

    public static String getURLfindBylocaStuidd() {
        return URLfindBylocaStuidd;
    }

    public static String getURLCheckAccount() {
        return URLCheckAccount;
    }

    public static String getURLdeletefriend() {
        return URLdeletefriend;
    }

    public static void setURLdeletefriend(String URLdeletefriend) {
        URLConfigUtil.URLdeletefriend = URLdeletefriend;
    }

    public static String getURLadduser() {
        return URLadduser;
    }

    public static void setURLadduser(String URLadduser) {
        URLConfigUtil.URLadduser = URLadduser;
    }

    public static String getURLfriend() {
        return URLfriend;
    }

    public static void setURLfriend(String URLfriend) {
        URLConfigUtil.URLfriend = URLfriend;
    }

    public static String getURLMap() {
        return URLMap;
    }

    public static void setURLMap(String URLMap) {
        URLConfigUtil.URLMap = URLMap;
    }
}
