package org.mybatis.extension.auto.sql;

import java.sql.SQLException;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.entity.TableEntity;

/**
 * 
 * Drop table SQL statement
 * 
 * @author popkidorc
 * @since 2015年4月2日
 * 
 */
public interface IDropTableSql extends IBaseSql {

	/**
	 * 
	 * Initialize SQL statement
	 * 
	 * @param autoDataSourceParam
	 *            autoDataSourceParam
	 * @param tableEntity
	 *            tableEntity
	 */
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity);

}
