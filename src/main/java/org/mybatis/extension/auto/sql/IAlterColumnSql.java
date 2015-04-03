package org.mybatis.extension.auto.sql;

import java.sql.SQLException;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.entity.TableEntity;

/**
 * 
 * Alter column SQL statement
 * 
 * @author popkidorc
 * @since 2015年4月2日
 * 
 */
public interface IAlterColumnSql extends IBaseSql {

	/**
	 * 
	 * Initialize SQL statement
	 * 
	 * @param autoDataSourceParam
	 *            autoDataSourceParam
	 * @param tableEntity
	 *            tableEntity
	 * @throws SQLException
	 *             SQLException
	 */
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity) throws SQLException;

}
