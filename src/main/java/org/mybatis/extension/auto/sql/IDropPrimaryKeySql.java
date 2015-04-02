package org.mybatis.extension.auto.sql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.entity.TableEntity;

/**
 * 
 * Drop primary key SQL statement
 * 
 * @author popkidorc
 * @date 2015年4月2日
 * 
 */
public interface IDropPrimaryKeySql extends IBaseSql {

	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity);

}
