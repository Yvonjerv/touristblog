package basic;

import db.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Implements class of Data access Layer
 */
public class BaseDaoImpl extends DBConnection implements BaseDAO {
    private Connection con = null;
    private PreparedStatement ptmt = null;
    private CallableStatement ctmt = null;
    private ResultSet rs = null;


    @Override
    public void close() {
        super.close(con, ptmt, rs);
    }

    @Override
    public int insert(String sql, Object[] para) {
        try {
            con = super.getConnection();
            //set sql string to ptmt
            ptmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //set real parameters to sql
            if (para != null) {
                for (int i = 0; i < para.length; i++) {
                    ptmt.setObject(i + 1, para[i]);
                }
            }
            int effectRow = ptmt.executeUpdate();
            if (effectRow > 0) {
                rs = ptmt.getGeneratedKeys(); //get primary key
                if (rs != null && rs.next()) {
                    int key = rs.getInt(1);
                    close();//close database
                    return key;
                }
            }
            close(); //close database

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean update(String sql, Object[] para) {
        int row = 0;
        try {
            con = super.getConnection();
            //set sql string to ptmt
            ptmt = con.prepareStatement(sql);
            //set real parameters to sql
            if (para != null) {
                for (int i = 0; i < para.length; i++) {
                    ptmt.setObject(i + 1, para[i]);
                }
            }
            row = ptmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        close(); //close database
        return row > 0;
    }

    @Override
    public boolean delete(String sql, Object[] para) {
        int row = 0;
        try {
            con = super.getConnection();
            //set sql string to ptmt
            ptmt = con.prepareStatement(sql);
            //set real parameters to sql
            if (para != null) {
                for (int i = 0; i < para.length; i++) {
                    ptmt.setObject(i + 1, para[i]);
                }
            }
            row = ptmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        close(); //close database
        return row > 0;

    }

    @Override
    public ResultSet select(String sql, Object[] para) {

        try {
            con = super.getConnection();
            //set sql string to ptmt
            ptmt = con.prepareStatement(sql);
            //set real parameters to sql
            if (para != null) {
                for (int i = 0; i < para.length; i++) {
                    ptmt.setObject(i + 1, para[i]);
                }
            }
            rs = ptmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        since RS is returned, close( cannot be used for destruction,
        but who calls select() will destroy the database object
         */
        return rs;
    }

    @Override
    public int selectCount(String sql, Object[] para) {
        int count = 0;
        try {
            con = super.getConnection();
            //set sql string to ptmt
            ptmt = con.prepareStatement(sql);
            //set real parameters to sql
            if (para != null) {
                for (int i = 0; i < para.length; i++) {
                    ptmt.setObject(i + 1, para[i]);
                }
            }
            rs = ptmt.executeQuery();
            if (rs != null && rs.last()) {
                count = rs.getRow();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        close(); //close database

        return count;
    }

    @Override
    public ResultSet callProcedureWithQuery(String procName, Object[] para) {

        try {
            con = getConnection();
            //set sql string to ptmt
            ctmt = con.prepareCall("{call " + procName + "}");
            //set real parameters to sql
            if (para != null) {
                for (int i = 0; i < para.length; i++) {
                    ctmt.setObject(i + 1, para[i]);
                }
            }
            boolean type = ctmt.execute();
            if (type) {
                rs = ctmt.getResultSet();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close();
            //close database
        }
        return rs;
    }
}
