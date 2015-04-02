package org.mybatis.extension.auto.sql.mysql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.BaseSql;
import org.mybatis.extension.auto.sql.IDropTableSql;
import org.mybatis.extension.auto.sql.entity.TableEntity;

public class MysqlDropTableSql extends BaseSql implements IDropTableSql {

	@Override
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity) {
		StringBuffer sql = new StringBuffer();
		if (autoDataSourceParam.isFormatSql()) {
			sql.append("\n");
		}
		sql.append("DROP TABLE IF EXISTS ");
		sql.append(tableEntity.getTableName());
		sql.append(";");
		sqls.add(sql.toString());
	}

}
