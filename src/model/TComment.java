package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TComment {
    private int commentid;
    private String textcomment;
    private String userid;
    private int articleid;
    private String createtime;

    public TComment() {
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getTextcomment() {
        return textcomment;
    }

    public void setTextcomment(String textcomment) {
        this.textcomment = textcomment;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    //exchange single user data from table to TComment bean
    public TComment(ResultSet rs) {
        try {
            if (rs != null && rs.next()) {
                this.setArticleid(rs.getInt("articleid"));
                this.setCommentid(rs.getInt("commentid"));
                this.setCreatetime(rs.getString("createtime"));
                this.setTextcomment(rs.getString("textcomment"));
                this.setUserid(rs.getString("userid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //exchange multiple user data from table to TComment bean
    public static ArrayList<TComment> toList(ResultSet rs) {
        ArrayList<TComment> list = new ArrayList<TComment>();
        try {
            while (rs != null && rs.next()) {
                TComment tcomment = new TComment();
                tcomment.setArticleid(rs.getInt("articleid"));
                tcomment.setCommentid(rs.getInt("commentid"));
                tcomment.setCreatetime(rs.getString("createtime"));
                tcomment.setTextcomment(rs.getString("textcomment"));
                tcomment.setUserid(rs.getString("userid"));
                list.add(tcomment);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
