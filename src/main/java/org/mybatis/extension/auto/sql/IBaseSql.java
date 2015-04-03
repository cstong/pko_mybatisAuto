package org.mybatis.extension.auto.sql;

import java.util.List;

/**
 * 
 * SQL statement interface
 * 
 * @author popkidorc
 * @since 2015年4月2日
 * 
 */
public interface IBaseSql {

	/**
	 * 
	 * Get SQL statements
	 * 
	 * @return SQL statements
	 */
	public List<String> getSqls();
}
