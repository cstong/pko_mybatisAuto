package org.mybatis.extension.auto.sql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;

/**
 * 
 * Constraint status SQL statement
 * 
 * @author popkidorc
 * @since 2015年4月1日
 * 
 */
public interface IConstraintSql extends IBaseSql {

	/**
	 * 
	 * Initialize SQL statement
	 * 
	 * @param autoDataSourceParam
	 *            autoDataSourceParam
	 * @param enable
	 *            enable
	 */
	public void init(AutoDataSourceParam autoDataSourceParam, boolean enable);

}
