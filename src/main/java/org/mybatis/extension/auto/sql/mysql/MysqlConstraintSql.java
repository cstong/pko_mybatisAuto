package org.mybatis.extension.auto.sql.mysql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.BaseSql;
import org.mybatis.extension.auto.sql.IConstraintSql;

public class MysqlConstraintSql extends BaseSql implements IConstraintSql {

	@Override
	public void init(AutoDataSourceParam autoDataSourceParam, boolean enable) {
		StringBuffer sql = new StringBuffer();
		if (autoDataSourceParam.isFormatSql()) {
			sql.append("\n");
		}
		sql.append("SET FOREIGN_KEY_CHECKS = ");
		sql.append(enable ? "1" : "0");
		sql.append(";");
		this.sqls.add(sql.toString());
	}
}
