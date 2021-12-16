package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TUser {
    private String userid;
    private String username;
    private String pwd;
    private String mobile;
    private String mail;
    private String createtime;

    public TUser() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    //exchange single user data from table to TUser bean
    public TUser(ResultSet rs) {
        try {
            if (rs != null && rs.next()) {
                this.setUserid(rs.getString("userid"));
                this.setUsername(rs.getString("username"));
                this.setPwd(rs.getString("pwd"));
                this.setMobile(rs.getString("mobile"));
                this.setMail(rs.getString("mail"));
                this.setCreatetime(rs.getString("createtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //exchange multiple user data from table to TUser bean
    public static ArrayList<TUser> toList(ResultSet rs) {
        ArrayList<TUser> list = new ArrayList<TUser>();
        try {
            while (rs != null && rs.next()) {
                TUser user = new TUser();
                user.setUserid(rs.getString("userid"));
                user.setUsername(rs.getString("username"));
                user.setPwd(rs.getString("pwd"));
                user.setMobile(rs.getString("mobile"));
                user.setMail(rs.getString("mail"));
                user.setCreatetime(rs.getString("createtime"));
                list.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
