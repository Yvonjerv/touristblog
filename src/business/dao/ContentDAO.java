package business.dao; 

import model.TContent;
import java.util.List;

public interface ContentDAO {
    /**
     * Registered contents (including hotel administrators and ordinary members)
     *
     * @param content TContent object containing content registration information
     * @return boolean, True is returned for successful and false for failed
     */
    int addContent(TContent content);

    /**
     *
     * @param articleid
     * @return
     */
    public boolean deleteContentsByArticleid(int  articleid);
    /**
     * Modify content information
     *
     * @param content TContent object containing content modification information
     * @return boolean, Return true for success and false for failure
     */
    boolean modifyContent(TContent content);

    /**
     *
     * @param contentid
     * @return
     */
    TContent getContentById(int contentid);

    /**
     *
     * @param contentid
     * @return
     */
    boolean deleteContent(int contentid);

    /**
     *
     * @param textcontent
     * @param order
     * @return
     */
    List<TContent> getAllContents(int articleid, String textcontent, int order);

    /**
     * Returns a list of objects for all hotel information
     *
     * @return List<TContent>
     */
    List<TContent> getContentListByCondition(String position, String textcontent);
}