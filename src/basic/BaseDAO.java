package basic;

import java.sql.ResultSet;

/**
 * Database access interface
 * @author  Jinsheng Zhang
 * @version 1.0
 * @since 2017-9-4
 */
public interface BaseDAO {	
	/**
	 *Encapsulate the close method of the parent class again to provide a
	 * simple method of this class to close the database object
	 * @return void
	 */
    void close();

	/**
	 * Data insertion method
	 *@ param sql,  insert into sql statement which can contain placeholder '?'
	 *@ param para , object of object[] type, which contain all value of corresponding '?'
	 *@ return int, 0 means failure, > 0 means success.
	 * If the primary key ID is an automatic number, the number value is returned
	 */
    int insert(String sql, Object[] para);
	
	/**
	 * Data update method
	 * @param sql,  update sql statement which can contain placeholder '?'
	 * @param para, object of object[] type, which contain all value of corresponding '?'
	 * @return boolean,  true means success, false means failure
	 */
    boolean update(String sql, Object[] para);

	/**
	 * Data deletion method
	 * @param sql   delete sql statement which can contain placeholder '?'
	 * @param para, object of object[] type, which contain all value of corresponding '?'
	 * @return boolean  true means success, false means failure
	 */
    boolean delete(String sql, Object[] para);
	
	/**
	 * Data query method. After calling this method, you need to call close()
	 * of BaseDao class to close related resource objects
	 * @param sql  select sql statement which can contain placeholder '?'
	 * @param para, object of object[] type, which contain all value of corresponding '?'
	 * @return ResultSet
	 */
    ResultSet select(String sql, Object[] para);

	/**
	 * Returns the total number of records in the query structure
	 * @param sql  select sql statement which can contain placeholder '?'
	 * @param para, object of object[] type, which contain all value of corresponding '?'
	 * @return int
	 */
    int selectCount(String sql, Object[] para);

	/**
	 *Call the database stored procedure with the function of select and return the query results to rs
	 * @param procName  the name of procedure, which can contain placeholder '?'
	 * @param para, object of object[] type, which contain all value of corresponding '?'
	 * @return ResultSet
	 */
    ResultSet callProcedureWithQuery(String procName, Object[] para);
}
