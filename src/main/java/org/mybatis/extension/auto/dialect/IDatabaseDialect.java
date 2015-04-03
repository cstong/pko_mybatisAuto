package org.mybatis.extension.auto.dialect;

import java.sql.SQLException;

/**
 * 
 * Automatically create table dialect interface
 * 
 * @author popkidorc
 * @since 2015年3月28日
 * 
 */
public interface IDatabaseDialect {

	/**
	 * 
	 * Invoke SQL statement
	 * 
	 * @throws SQLException
	 *             invoke SQL exception
	 */
	public void invoke() throws SQLException;
}
