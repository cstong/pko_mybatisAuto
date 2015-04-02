package org.mybatis.extension.auto.sql;

import java.sql.SQLException;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.entity.TableEntity;

/**
 * 
 * Alter column SQL statement
 * 
 * @author popkidorc
 * @date 2015年4月2日
 * 
 */
public interface IAlterColumnSql extends IBaseSql {
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity) throws SQLException;
}
