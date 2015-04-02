package org.mybatis.extension.auto.sql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;

/**
 * 
 * Constraint status SQL statement
 * 
 * @author popkidorc
 * @date 2015年4月1日
 * 
 */
public interface IConstraintSql extends IBaseSql {

	public void init(AutoDataSourceParam autoDataSourceParam, boolean enable);

}
