package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TContent {
    private int contentid;
    private int  articleid;
    private String textcontent;
    private String photourl;
    private int orderid;

    public TContent() {
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getTextcontent() {
        return textcontent;
    }

    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    //exchange single user data from table to TContent bean
    public TContent(ResultSet rs) {
        try {
            if (rs != null && rs.next()) {
                this.setContentid(rs.getInt("contentid"));
                this.setArticleid(rs.getInt("articleid"));
                this.setTextcontent(rs.getString("textcontent"));
                this.setPhotourl(rs.getString("photourl"));
                this.setOrderid(rs.getInt("orderid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //exchange multiple user data from table to TContent bean
    public static ArrayList<TContent> toList(ResultSet rs) {
        ArrayList<TContent> list = new ArrayList<TContent>();
        try {
            while (rs != null && rs.next()) {
                TContent content = new TContent();
                content.setContentid(rs.getInt("contentid"));
                content.setArticleid(rs.getInt("articleid"));
                content.setTextcontent(rs.getString("textcontent"));
                content.setPhotourl(rs.getString("photourl"));
                content.setOrderid(rs.getInt("orderid"));
                list.add(content);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
