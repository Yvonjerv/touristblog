package business.impl;

import basic.BaseDAO;
import basic.BaseDaoImpl;
import business.dao.ArticleDAO;
import model.TArticle;
import model.TContent;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDAO {
    //we need to process Database by data Access layer
    private final BaseDAO dao = new BaseDaoImpl();

    @Override
    public int addArticle(TArticle article) {

        String sql = " insert into t_article( title, address, userid) " +
                " values(?,?,?)";
        Object[] para = {article.getTitle(), article.getAddress(), article.getUserid()};
        int row = dao.insert(sql, para);
        if (row > 0) return row;
        else return -1;
    }

    @Override
    public boolean modifyArticle(TArticle article) {
        String sql = " update t_article set  title=? ,address=? " +
                "  where  articleId=?";
        Object[] para = {article.getTitle(), article.getAddress(), article.getArticleid()};
        return dao.update(sql, para);
    }

    @Override
    public List<TArticle> getAllArticles(String title, String userid) {
        String sql = " ";
        ResultSet rs = null;

        if (title == null && userid == null) {
            sql = " select * from t_article order by articleid desc";
            rs = dao.select(sql, null);

        } else if (title != null && userid == null) {
            sql = " select * from t_article where  title like %?% order by  articleid desc";
            Object[] para = {title};
            rs = dao.select(sql, para);

        } else if (title == null && userid != null) {
            sql = " select * from t_article where  userid =? order by  articleid desc";
            Object[] para = {userid};
            rs = dao.select(sql, para);

        } else {
            sql = " select * from t_article where  title like %?% and  userid =? order by  articleid desc";
            Object[] para = {title, userid};
            rs = dao.select(sql, para);
        }
        ArrayList<TArticle> list = TArticle.toList(rs);
        dao.close();
        return list;
    }

    @Override
    public TArticle getArticleById(int articleid) {
        String sql = " select * from t_article where  articleid=? order by  articleid desc ";
        Object[] para = {articleid};
        ResultSet rs = dao.select(sql, para);
        TArticle article = new TArticle(rs);
        dao.close();
        return article;
    }


    @Override
    public boolean deleteArticle(int articleid) {
        String sql = " ";
//        if (deleteContent(articleid)) {
//            sql = " delete from t_article where articleId=?";
//        } else
        sql = " delete from t_article where articleId=? ";
        Object[] para = {articleid};
        return dao.delete(sql, para);
    }

    @Override
    public TContent getFirstContent(int articleid) {
        String sql = " select * from t_content where  articleId=? and orderid = 1 ";
        Object[] para = {articleid};
        ResultSet rs = dao.select(sql, para);
        TContent content = new TContent(rs);
        dao.close();
        return content;
    }

    @Override
    public TArticle getArticleWithHighestComment() {
        String sql = "SELECT a.*\n" +
                "FROM t_article a\n" +
                "INNER JOIN (\n" +
                "SELECT articleid, COUNT( articleid) AS CommentCount, userid\n" +
                "FROM t_comment\n" +
                "GROUP BY articleid\n" +
                "ORDER BY CommentCount desc\n" +
                "LIMIT 1) b\n" +
                "ON a.articleid = b.articleid ";
//        Object[] para = {null};
        ResultSet rs = dao.select(sql, null);
        TArticle article = new TArticle(rs);
        dao.close();
        return article;
    }


    @Override
    public List<TArticle> getArticleListByCondition(String position, String title) {
        if (position == null && title == null) {
            String sql = " select * from t_article order by articleid desc";

            ResultSet rs = dao.select(sql, null);
            List<TArticle> list = TArticle.toList(rs);
            dao.close();
            return list;
        } else {  //
            String sql = " SELECT DISTINCT( t_article.articleid), t_article.title, t_article.address, " +
                    " t_article.publishtime, t_article.userid  " +
                    " FROM t_content INNER JOIN t_article ON t_content.articleid = t_article.articleid where  ";
            String whereString = " ";
            if (position != null) {
                whereString += " t_content.textcontent  like '%" + position + "%' " +
                        "  or t_article.address  like '%" + position + "%' " +
                        " or t_article.userid  like '%" + position + "%' ";
            }
            if (title != null) {
                if (whereString.equals("")) {
                    whereString += " t_article.title like '%" + title + "%'";
                } else {
                    whereString += "  or  t_article.title like '%" + title + "%'";
                }
            }
            sql += whereString;
            ResultSet rs = dao.select(sql, null);
            List<TArticle> list = TArticle.toList(rs);
            dao.close();
            return list;
        }
    }
}
