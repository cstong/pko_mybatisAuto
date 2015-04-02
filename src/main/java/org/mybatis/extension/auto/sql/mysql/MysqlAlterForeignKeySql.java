package org.mybatis.extension.auto.sql.mysql;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.BaseSql;
import org.mybatis.extension.auto.sql.IAlterForeignKeySql;
import org.mybatis.extension.auto.sql.entity.ForeignKeyEntity;
import org.mybatis.extension.auto.sql.entity.TableEntity;

public class MysqlAlterForeignKeySql extends BaseSql implements
		IAlterForeignKeySql {

	@Override
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity) {
		if (tableEntity.getForeignKeyEntites().size() <= 0) {
			return;
		}
		for (ForeignKeyEntity foreignKeyEntity : tableEntity
				.getForeignKeyEntites()) {
			StringBuffer sql = new StringBuffer();
			if (autoDataSourceParam.isFormatSql()) {
				sql.append("\n");
			}
			sql.append("ALTER TABLE");
			sql.append(" ");
			sql.append(foreignKeyEntity.getTableName());
			sql.append(" ");
			sql.append("ADD CONSTRAINT");
			sql.append(" ");
			sql.append(foreignKeyEntity.getForeignKeyName());
			sql.append(" ");
			sql.append("FOREIGN KEY");
			sql.append("(" + foreignKeyEntity.getColumnName() + ")");
			sql.append(" ");
			sql.append("REFERENCES");
			sql.append(" ");
			sql.append(foreignKeyEntity.getForeignKeyTableName());
			sql.append("(" + foreignKeyEntity.getForeignKeyColumnName() + ");");
			this.sqls.add(sql.toString());
		}
	}

}
