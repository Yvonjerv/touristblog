package business.impl;

import basic.BaseDAO;
import basic.BaseDaoImpl;
import business.dao.CommentDAO;
import model.TComment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDAO {
    //we need to process Database by data Access layer
    private final BaseDAO dao = new BaseDaoImpl();

    @Override
    public int addComment(TComment comment) {

        String sql = "insert into t_comment( articleid, textcomment, userid) " +
                "values(?,?,?)";
        Object[] para = {comment.getArticleid(), comment.getTextcomment(), comment.getUserid()};
        int row = dao.insert(sql, para);
        if (row > 0) return row;
        else return -1;
    }

    @Override
    public TComment getCommentById(int commentid) {
        String sql = "select * from t_comment where  commentid=?  ";
        Object[] para = {commentid};
        ResultSet rs = dao.select(sql, para);
        TComment tcomment = new TComment(rs);
        dao.close();
        return tcomment;
    }


    @Override
    public boolean deleteCommentsByArticleid(int articleid) {

        String sql = "delete from t_comment where articleid=? ";
        Object[] para = {articleid};
        return dao.delete(sql, para);
    }


    @Override
    public List<TComment> getAllComments(int articleid, String userid) {
        String sql = "";
        ResultSet rs = null;

        if (articleid == 0 && userid == null) {
            sql = "select * from t_comment order by createtime desc";
            rs = dao.select(sql, null);

        } else if (articleid != 0 && userid == null) {
            sql = "select * from t_Comment where  articleid =? order by  createtime desc";
            Object[] para = {articleid};
            rs = dao.select(sql, para);

        } else if (articleid == 0 && userid != null) {
            sql = "select * from t_comment where  userid =? order by  createtime desc";
            Object[] para = {userid};
            rs = dao.select(sql, para);
        } else {
            sql = "select * from t_comment where  articleid ? and  userid =? order by  createtime desc";
            Object[] para = {articleid, userid};
            rs = dao.select(sql, para);
        }
        ArrayList<TComment> list = TComment.toList(rs);
        dao.close();
        return list;
    }

    @Override
    public boolean deleteComment(int commentid) {
        String sql = "delete from t_comment where commentid=? ";
        Object[] para = {commentid};
        return dao.delete(sql, para);
    }
}
