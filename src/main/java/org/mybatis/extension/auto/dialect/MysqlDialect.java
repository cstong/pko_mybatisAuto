package org.mybatis.extension.auto.dialect;

import java.sql.SQLException;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.mybatis.extension.auto.sql.MysqlAutoDataSqlFactory;

/**
 * 
 * Automatically create table dialect using mysql.
 * 
 * @author popkidorc
 * @creation 2015年3月28日
 * 
 */
public class MysqlDialect extends DatabaseDialect {

	public MysqlDialect(AutoDataSourceParam autoDataSourceParam) {
		super(autoDataSourceParam);
		this.autoDataSqlFactory = new MysqlAutoDataSqlFactory();
	}

	@Override
	protected void create() throws SQLException {
		super.create();
	}

	@Override
	protected void update() throws SQLException {
		super.update();
	}

}
