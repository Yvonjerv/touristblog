package test;

import business.dao.ArticleDAO;
import business.impl.ArticleDaoImpl;
import model.TArticle;
import org.junit.Test;

import java.util.List;

public class ArticleDaoImplTest {
    ArticleDAO hdao = new ArticleDaoImpl();


    @Test
    public void getArticleListByCondition() {
        List<TArticle> list   = hdao.getArticleListByCondition("lorem", "lorem");

        for (TArticle article: list
             ) {
            System.out.println(article.getTitle() + " " + article.getPublishtime());
        }

    }

}