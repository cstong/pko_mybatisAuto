package org.mybatis.extension.auto.sql.mysql;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.BaseSql;
import org.mybatis.extension.auto.sql.IDropPrimaryKeySql;
import org.mybatis.extension.auto.sql.entity.ColumnEntity;
import org.mybatis.extension.auto.sql.entity.TableEntity;
import org.mybatis.extension.auto.type.ColumnType;
import org.mybatis.extension.auto.type.IdType;

public class MysqlDropPrimaryKeySql extends BaseSql implements
		IDropPrimaryKeySql {

	@Override
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity) {
		if (tableEntity.getPrimaryKeyEntity() == null
				|| tableEntity.getPrimaryKeyEntity().getTableName() == null
				|| "".equals(tableEntity.getPrimaryKeyEntity().getTableName())) {
			return;
		}
		StringBuffer sql = new StringBuffer();
		List<String> columnChangeSqls = new ArrayList<String>();
		if (autoDataSourceParam.isFormatSql()) {
			sql.append("\n");
		}
		sql.append("ALTER TABLE");
		sql.append(" ");
		sql.append(tableEntity.getTableName());
		sql.append(" ");
		sql.append("ADD CONSTRAINT");
		sql.append(" ");
		sql.append(tableEntity.getPrimaryKeyEntity().getPrimaryKeyName());
		sql.append(" ");
		sql.append("PRIMARY KEY");
		sql.append(" ");
		sql.append("(");
		for (ColumnEntity columnEntity : tableEntity.getPrimaryKeyEntity()
				.getPrimaryKeyColumns()) {
			sql.append(columnEntity.getColumnName() + ",");
			if (columnEntity.getPrimaryKeyType() != null
					&& !"".equals(columnEntity.getPrimaryKeyType())) {
				StringBuffer columnChangeSql = new StringBuffer();
				if (autoDataSourceParam.isFormatSql()) {
					columnChangeSql.append("\n");
				}
				columnChangeSql.append("ALTER TABLE");
				columnChangeSql.append(" ");
				columnChangeSql.append(tableEntity.getTableName());
				columnChangeSql.append(" ");
				columnChangeSql.append("MODIFY");
				columnChangeSql.append(" ");
				columnChangeSql.append(columnEntity.getColumnName());
				columnChangeSql.append(" ");
				if (columnEntity.getPrimaryKeyType().equalsIgnoreCase(
						IdType.AUTO_INCREMENT.toString())) {
					columnChangeSql.append(ColumnType.INT.toString());
					columnChangeSql.append("(11)");
				} else {
					columnChangeSql.append(columnEntity.getColumnType());
					columnChangeSql.append("(" + columnEntity.getColumnLength()
							+ ")");
				}
				columnChangeSql.append(" ");
				columnChangeSql.append(columnEntity.getPrimaryKeyType());
				columnChangeSql.append(";");
				columnChangeSqls.add(columnChangeSql.toString());
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(");");
		this.sqls.add(sql.toString());
		this.sqls.addAll(columnChangeSqls);
	}

}
