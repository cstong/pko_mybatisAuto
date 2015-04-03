package org.mybatis.extension.auto.dialect;

import java.sql.SQLException;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;

/**
 * 
 * Automatically create table dialect using mysql.
 * 
 * @author popkidorc
 * @since 2015年3月30日
 * 
 */
public class OracleDialect extends DatabaseDialect {

	public OracleDialect(AutoDataSourceParam autoDataSourceParam) {
		super(autoDataSourceParam);
		// TODO Auto-generated method stub
		
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
