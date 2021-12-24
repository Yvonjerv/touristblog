package business.impl;

import basic.BaseDAO;
import basic.BaseDaoImpl;
import business.dao.UserDAO;
import model.TUser;

import java.sql.ResultSet;

public class UserDaoImpl implements UserDAO {
    //we need to process Database by data Access layer
    private final BaseDAO dao = new BaseDaoImpl();

    @Override
    public TUser login(String userid, String pwd) {

        String sql = "select * from t_user where userid = ? and pwd=?";
        Object[] para = {userid, pwd};
        ResultSet rs = dao.select(sql, para);
        TUser user = new TUser(rs);
        dao.close();
        return user;
    }

    @Override
    public boolean isUseridValid(String userid) {
        String sql = "select * from t_user where userid=?";

        Object[] para = {userid};
        int row = dao.selectCount(sql, para);
        return row <= 0;
    }

    @Override
    public boolean registerUser(TUser user) {
        String sql = "insert into t_user (userid, username,pwd ,mobile,mail ) " +
                " values(?,?,?,?,?)";

        Object[] para = {user.getUserid(), user.getUsername(), user.getPwd(), user.getMobile(), user.getMail()};
        int row = dao.insert(sql, para);
        System.out.println(row);
        if (row > -1) return true;
        else return false;
    }

    @Override
    public boolean modifyUser(TUser user) {
        String sql = "update t_user set username  =? ,pwd  =? ,mobile =? , mail =?  " +
                " where userid=?";
        Object[] para = {user.getUsername(), user.getPwd(), user.getMobile(), user.getMail(),
                user.getUserid()};
        return dao.update(sql, para);
    }

    @Override
    public TUser getTUserById(String userid) {
        String sql = "select * from t_user where userid=? ";
        Object[] para = {userid};
        ResultSet rs = dao.select(sql, para);
        TUser user = new TUser(rs);
        dao.close();
        return user;
    }
}