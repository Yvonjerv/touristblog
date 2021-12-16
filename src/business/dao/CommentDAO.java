package business.dao;

import model.TComment;

import java.util.List;

public interface CommentDAO {
    /**
     *
     * @param tComment
     * @return
     */
    int addComment(TComment tComment);

    /**
     * Modify comment information
     *
     * @param tcomment Tcomment object containing comment modification information
     * @return boolean, Return true for success and false for failure
     */
//    public boolean modifyComment(TComment tComment);

    /**
     *
     * @param commentid
     * @return
     */
    public TComment getCommentById(int  commentid);

    public boolean deleteCommentsByArticleid(int  articleid);
    /**
     *
     * @param commentid
     * @return
     */
    boolean deleteComment(int commentid);

    /**
     * 
     * @param articleid
     * @param userid
     * @return
     */
    List<TComment> getAllComments(int articleid, String userid);

   
}