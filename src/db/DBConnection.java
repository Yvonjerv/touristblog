package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/blogwebdb?useUnicode=true&characterEncoding=UTF-8";
    private final String username = "root";
    private final String password = "1234";


    /**
     * Create connection from specified database
     *
     * @return connection object Java.sql.Connection
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url, username, password);

    }

    /**
     * Close database
     *
     * @param con  Java.sql.Connection if you don't need to close it input null
     * @param stmt java.sql.Statement
     * @param rs   Java.sql.ResultSet
     */
    public void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
