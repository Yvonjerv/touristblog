package business.impl;

import basic.BaseDAO;
import basic.BaseDaoImpl;
import business.dao.ContentDAO;
import model.TContent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContentDaoImpl implements ContentDAO {
    //we need to process Database by data Access layer
    private final BaseDAO dao = new BaseDaoImpl();

    @Override
    public int addContent(TContent content ) {

        String sql = "insert into t_content ( articleid, textcontent, photourl, orderid) " +
                "values(?,?,?,?)";
        Object[] para = {content.getArticleid(), content.getTextcontent(), content.getPhotourl(), content.getOrderid()};
        int row = dao.insert(sql, para);
        if (row > 0) return row;
        else return -1;
    }

    @Override
    public boolean modifyContent(TContent content ) {
        String sql = "update t_content  set  textcontent=? ,photourl=?, orderid=? " +
                " where  contentid=?";
        Object[] para = {content.getTextcontent(), content.getPhotourl(), content.getOrderid(), content.getContentid()};
        return dao.update(sql, para);
    }

    @Override
    public List<TContent> getAllContents(int articleid,String  textcontent, int  orderid) {
        String sql = "";
        ResultSet rs = null;

        if (textcontent == null &&  orderid == 0) {
            sql = "select * from t_content where articleid= ? order by orderid ";
            Object[] para = {articleid};
            rs = dao.select(sql, para);

        } else if (textcontent != null &&  orderid == 0) {
            sql = "select * from t_content  where articleid= ? and textcontent like %?% order by  orderid ";
            Object[] para = {articleid, textcontent};
            rs = dao.select(sql, para);

        } else if (textcontent == null &&  orderid != 0) {
            sql = "select * from t_content  where articleid= ? and  orderid =? order by  orderid ";
            Object[] para = { articleid, orderid};
            rs = dao.select(sql, para);

        } else {
            sql = "select * from t_content  where articleid= ? and  textcontent like %?% and  orderid =? order by  orderid ";
            Object[] para = {articleid, textcontent,  orderid};
            rs = dao.select(sql, para);
        }
        ArrayList<TContent> list = TContent.toList(rs);
        dao.close();
        return list;
    }

    @Override
    public TContent  getContentById(int  contentid) {
        String sql = "select * from t_content  where  contentid=? order by  orderid  ";
        Object[] para = {contentid};
        ResultSet rs = dao.select(sql, para);
        TContent  content  = new TContent(rs);
        dao.close();
        return  content ;
    }

    @Override
    public boolean deleteContent(int  contentid) {
        String sql = "delete from t_content  where  contentid=?  ";
        Object[] para = {contentid};
        return dao.delete(sql, para);
    }
    @Override
    public boolean deleteContentsByArticleid(int  articleid) {
        String sql = "delete from t_content  where  articleid=?  ";
        Object[] para = {articleid};
        return dao.delete(sql, para);
    }

    @Override
    public List<TContent> getContentListByCondition(String position, String  textcontent) {
        if (position == null &&  textcontent == null) {
            String sql = "select * from t_content  order by orderid ";

            ResultSet rs = dao.select(sql, null);
            List<TContent> list = TContent.toList(rs);
            dao.close();
            return list;
        } else {
            String sql = "select * from t_content  where ";
            String whereString = "";
            if (position != null) {
                whereString += "(publishtime like '%" + position + "%' " +
                        "or orderid like '%" + position + "%')";
            }
            if (textcontent != null) {
                if (whereString.equals("")) {
                    whereString += "textcontent like '%" +  textcontent + "%'";
                } else {
                    whereString += "and textcontent like '%" +  textcontent + "%'";
                }
            }
            sql += whereString;
            ResultSet rs = dao.select(sql, null);
            List<TContent> list = TContent.toList(rs);
            dao.close();
            return list;
        }

    }
}
