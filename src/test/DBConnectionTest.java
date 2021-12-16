package test;

import db.DBConnection;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

/**
 * Test class about DB.DBConnection
 *
 * @author Yvon Apedo
 * @version 1.0 2021/9/23
 */
public class DBConnectionTest {
    //Define object of tested class as memeber variable
    DBConnection dbc = null;

    //before noted method will execute before any other method
    @Before
    public void init() {
        dbc = new DBConnection();
    }


    @Test
    public void testGetConnection() throws Exception {
        Connection con = dbc.getConnection();
        System.out.println("database connected successfully");
        dbc.close(con, null, null);
        System.out.println("database closed successfully");
    }
}

