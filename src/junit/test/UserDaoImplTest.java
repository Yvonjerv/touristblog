package junit.test;

import business.dao.UserDAO;
import business.impl.UserDaoImpl;
import model.TUser;
import org.junit.Test;

public class UserDaoImplTest {
    UserDAO hdao = new UserDaoImpl();

    @Test
    public void registerUser() {
        TUser user = new TUser();
        user.setUserid("userid");
        user.setUsername("username");
        user.setPwd("pwd");
        user.setMail("mail");
        user.setMobile("mobile");
        System.out.println(hdao.registerUser(user) + " " + user.getUserid());

    }
}