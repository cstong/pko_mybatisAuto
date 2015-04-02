package org.mybatis.extension.auto.sql;

import org.mybatis.extension.auto.sql.mysql.MysqlAlterColumnSql;
import org.mybatis.extension.auto.sql.mysql.MysqlAlterForeignKeySql;
import org.mybatis.extension.auto.sql.mysql.MysqlAlterPrimaryKeySql;
import org.mybatis.extension.auto.sql.mysql.MysqlColumnTypeMap;
import org.mybatis.extension.auto.sql.mysql.MysqlConstraintSql;
import org.mybatis.extension.auto.sql.mysql.MysqlCreateTableSql;
import org.mybatis.extension.auto.sql.mysql.MysqlDropAllConstraintSql;
import org.mybatis.extension.auto.sql.mysql.MysqlDropPrimaryKeySql;
import org.mybatis.extension.auto.sql.mysql.MysqlDropTableSql;

public class MysqlAutoDataSqlFactory implements IAutoDataSqlFactory {

	@Override
	public IAlterColumnSql getAlterColumnSql() {
		return new MysqlAlterColumnSql();
	}

	@Override
	public IAlterForeignKeySql getAlterForeignKeySql() {
		return new MysqlAlterForeignKeySql();
	}

	@Override
	public IAlterPrimaryKeySql getAlterPrimaryKeySql() {
		return new MysqlAlterPrimaryKeySql();
	}

	@Override
	public IConstraintSql getConstraintSql() {
		return new MysqlConstraintSql();
	}

	@Override
	public ICreateTableSql getCreateTableSql() {
		return new MysqlCreateTableSql();
	}

	@Override
	public IDropAllConstraintSql getDropAllConstraintSql() {
		return new MysqlDropAllConstraintSql();
	}

	@Override
	public IDropPrimaryKeySql getDropPrimaryKeySql() {
		return new MysqlDropPrimaryKeySql();
	}

	@Override
	public IDropTableSql getDropTableSql() {
		return new MysqlDropTableSql();
	}

	@Override
	public IColumnTypeMap getColumnTypeMap() {
		return new MysqlColumnTypeMap();
	}

}
