package junit.test;

import business.dao.CommentDAO;
import business.impl.CommentDaoImpl;
import model.TComment;
import org.junit.Test;

import java.util.List;

public class CommentDaoImplTest {
    CommentDAO hdao = new CommentDaoImpl();


    @Test
    public void getAllComments() {
        List<TComment> list = hdao.getAllComments(1, null);

        for (TComment comment : list
        ) {
            System.out.println(comment.getTextcomment() + " " + comment.getUserid());
        }
    }

    @Test
    public void addComment() {
        TComment comment = new TComment();
        comment.setTextcomment("title");
        comment.setArticleid(1);
        comment.setUserid("beike1");

        System.out.println(hdao.addComment(comment));
    }

}