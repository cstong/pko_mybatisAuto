package org.mybatis.extension.auto.sql.mysql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.BaseSql;
import org.mybatis.extension.auto.sql.ICreateTableSql;
import org.mybatis.extension.auto.sql.entity.ColumnEntity;
import org.mybatis.extension.auto.sql.entity.TableEntity;

public class MysqlCreateTableSql extends BaseSql implements ICreateTableSql {

	@Override
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity) {
		StringBuffer sql = new StringBuffer();
		if (autoDataSourceParam.isFormatSql()) {
			sql.append("\n");
		}
		sql.append("CREATE TABLE IF NOT EXISTS ");
		sql.append(tableEntity.getTableName());
		sql.append("(");
		if (autoDataSourceParam.isFormatSql()) {
			sql.append("\n");
		}
		for (ColumnEntity columnEntity : tableEntity.getColumnEntities()) {
			// generate column SQL statement
			if (autoDataSourceParam.isFormatSql()) {
				sql.append("\t");
			}
			sql.append(columnEntity.getColumnName());
			sql.append(" ");
			sql.append(columnEntity.getColumnType());
			sql.append("(" + columnEntity.getColumnLength() + ")");
			sql.append(" ");
			sql.append(columnEntity.getColumnNullable());
			sql.append(" ");
			sql.append(" COMMENT '" + columnEntity.getColumnComment() + "'");
			sql.append(",");
			if (autoDataSourceParam.isFormatSql()) {
				sql.append("\n");
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")");
		sql.append(" ");
		sql.append("COMMENT = '" + tableEntity.getTableComment() + "'");
		sql.append(" ");
		sql.append("ENGINE = " + tableEntity.getEngine());
		sql.append(" ");
		sql.append("DEFAULT CHARSET = " + tableEntity.getDefaultCharset());
		sql.append(";");
		sqls.add(sql.toString());
	}

}
