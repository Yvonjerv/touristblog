package model;

import business.dao.ArticleDAO;
import business.impl.ArticleDaoImpl;
import business.impl.ContentDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TArticle {
    private int articleid;
    private String title;
    private String address;
    private String publishtime;
    private String userid;
    private TContent firstContent;

    public TArticle() {
    }

    public TContent getFirstContent() {
        ArticleDAO dao = new ArticleDaoImpl();
        firstContent = dao.getFirstContent(this.articleid);
        return firstContent;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    //exchange single user data from table to TArticle bean
    public TArticle(ResultSet rs) {
        try {
            if (rs != null && rs.next()) {
                this.setArticleid(rs.getInt("articleid"));
                this.setTitle(rs.getString("title"));
                this.setAddress(rs.getString("address"));
                this.setPublishtime(rs.getString("publishtime"));
                this.setUserid(rs.getString("userid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //exchange multiple user data from table to TArticle bean
    public static ArrayList<TArticle> toList(ResultSet rs) {
        ArrayList<TArticle> list = new ArrayList<TArticle>();
        try {
            while (rs != null && rs.next()) {
                TArticle article = new TArticle();
                article.setArticleid(rs.getInt("articleid"));
                article.setTitle(rs.getString("title"));
                article.setAddress(rs.getString("address"));
                article.setPublishtime(rs.getString("publishtime"));
                article.setUserid(rs.getString("userid"));
                list.add(article);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
