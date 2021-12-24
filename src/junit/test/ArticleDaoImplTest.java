package junit.test;

import business.dao.ArticleDAO;
import business.impl.ArticleDaoImpl;
import model.TArticle;
import org.junit.Test;

import java.util.List;

public class ArticleDaoImplTest {
    ArticleDAO hdao = new ArticleDaoImpl();


    @Test
    public void getArticleListByCondition() {
        List<TArticle> list = hdao.getArticleListByCondition("edit", "edit");

        for (TArticle article : list
        ) {
            System.out.println(article.getTitle() + " " + article.getPublishtime());
        }
    }

    @Test
    public void getArticleWithHighestComment() {
        TArticle article = hdao.getArticleWithHighestComment();
        System.out.println(article.getTitle());
    }

    @Test
    public void addArticle() {
        TArticle article = new TArticle();
        article.setTitle("title");
        article.setAddress("address");
        article.setUserid("beike1");

        System.out.println(hdao.addArticle(article));
    }

}