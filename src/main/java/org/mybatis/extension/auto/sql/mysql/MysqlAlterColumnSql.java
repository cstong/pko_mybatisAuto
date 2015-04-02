package org.mybatis.extension.auto.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.BaseSql;
import org.mybatis.extension.auto.sql.IAlterColumnSql;
import org.mybatis.extension.auto.sql.entity.ColumnEntity;
import org.mybatis.extension.auto.sql.entity.TableEntity;

public class MysqlAlterColumnSql extends BaseSql implements IAlterColumnSql {

	@Override
	public void init(AutoDataSourceParam autoDataSourceParam,
			TableEntity tableEntity) throws SQLException {
		List<String> columnAddSqls = new ArrayList<String>();
		List<String> columnModifySqls = new ArrayList<String>();
		// Check column exists
		Set<String> existColumnNames = new HashSet<String>();
		StringBuffer describeSql = new StringBuffer();
		describeSql.append("DESCRIBE");
		describeSql.append(" ");
		describeSql.append(tableEntity.getTableName());
		if (autoDataSourceParam.isShowSql()) {
			logger.info("mybatiSql : " + describeSql);
		}
		ResultSet resultSet = this.executeQuery(autoDataSourceParam,
				describeSql.toString());
		while (resultSet.next()) {
			System.out.println(resultSet.getString("FIELD"));
			existColumnNames.add(resultSet.getString("FIELD"));
		}
		for (ColumnEntity columnEntity : tableEntity.getColumnEntities()) {
			if (!existColumnNames.contains(columnEntity.getColumnName())) {
				// alter column
				StringBuffer columnAddSql = new StringBuffer();
				if (autoDataSourceParam.isFormatSql()) {
					columnAddSql.append("\n");
				}
				columnAddSql
						.append("ALTER TABLE " + tableEntity.getTableName());
				columnAddSql.append(" ");
				columnAddSql.append("ADD");
				columnAddSql.append(" ");
				columnAddSql.append(columnEntity.getColumnName());
				columnAddSql.append(" ");
				columnAddSql.append(columnEntity.getColumnType());
				columnAddSql.append("(" + columnEntity.getColumnLength() + ")");
				columnAddSql.append(" ");
				columnAddSql.append(columnEntity.getColumnNullable());
				columnAddSql.append(" ");
				columnAddSql.append(" COMMENT '"
						+ columnEntity.getColumnComment() + "'");
				columnAddSql.append(";");
				columnAddSqls.add(columnAddSql.toString());
			} else
			// modify column
			{
				StringBuffer columnModifySql = new StringBuffer();
				if (autoDataSourceParam.isFormatSql()) {
					columnModifySql.append("\n");
				}
				columnModifySql.append("ALTER TABLE "
						+ tableEntity.getTableName());
				columnModifySql.append(" ");
				columnModifySql.append("MODIFY");
				columnModifySql.append(" ");
				columnModifySql.append(columnEntity.getColumnName());
				columnModifySql.append(" ");
				columnModifySql.append(columnEntity.getColumnType());
				columnModifySql.append("(" + columnEntity.getColumnLength()
						+ ")");
				columnModifySql.append(" ");
				columnModifySql.append(columnEntity.getColumnNullable());
				columnModifySql.append(" ");
				columnModifySql.append(" COMMENT '"
						+ columnEntity.getColumnComment() + "'");
				columnModifySql.append(";");
				columnModifySqls.add(columnModifySql.toString());
			}
		}
		this.sqls.addAll(columnAddSqls);
		this.sqls.addAll(columnModifySqls);
	}
}
