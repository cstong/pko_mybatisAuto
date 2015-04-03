package org.mybatis.extension.auto.sql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.entity.TableEntity;

/**
 * 
 * Alter foreign key SQL statement
 * 
 * @author popkidorc
 * @since 2015年4月2日
 * 
 */
public interface IAlterForeignKeySql extends IBaseSql {
	
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
