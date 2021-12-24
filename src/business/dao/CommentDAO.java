package business.dao;

import model.TComment;

import java.util.List;

public interface CommentDAO {
    /**
     * @param tComment
     * @return id
     */
    public int addComment(TComment tComment);


    /**
     * @param commentid
     * @return comment
     */
    public TComment getCommentById(int commentid);

    /**
     * @param articleid
     * @return boolean, Return true for success and false for failure
     */
    public boolean deleteCommentsByArticleid(int articleid);

    /**
     * @param commentid
     * @return boolean, Return true for success and false for failure
     */
    public boolean deleteComment(int commentid);

    /**
     * @param articleid
     * @param userid
     * @return list
     */
    public List<TComment> getAllComments(int articleid, String userid);


}