package business.dao;

import model.TArticle;
import model.TContent;

import java.util.List;

public interface ArticleDAO {
    /**
     * Registered articles (including hotel administrators and ordinary members)
     *
     * @param article TArticle object containing article registration information
     * @return boolean, True is returned for successful and false for failed
     */
    int addArticle(TArticle article);

    /**
     * Modify article information
     *
     * @param article TArticle object containing article modification information
     * @return boolean, Return true for success and false for failure
     */
    boolean modifyArticle(TArticle article);

    /**
     *
     * @param articleid
     * @return
     */
    TArticle getArticleById(int articleid);

    /**
     *
     * @param articleid
     * @return
     */
    boolean deleteArticle(int articleid);

    /**
     *
     * @param articleid
     * @return
     */
    TContent getFirstContent(int articleid);
    /**
     *
     * @param title
     * @param userid
     * @return
     */
    List<TArticle> getAllArticles(String title, String userid);

    /**
     * Returns a list of objects for all hotel information
     *
     * @return List<TArticle>
     */
    List<TArticle> getArticleListByCondition(String position, String title);
}