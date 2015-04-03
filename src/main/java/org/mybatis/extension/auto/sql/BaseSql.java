package org.mybatis.extension.auto.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.extension.auto.driver.AutoDataSourceParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Base SQL statement, implements IBaseSql
 * 
 * @author popkidorc
 * @since 2015年4月3日
 * 
 */
public class BaseSql implements IBaseSql {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected List<String> sqls;

	protected BaseSql() {
		this.sqls = new ArrayList<String>();
	}

	public List<String> getSqls() {
		return sqls;
	}

	/**
	 * 
	 * Execute query SQL statement
	 * 
	 * @param autoDataSourceParam
	 *            autoDataSourceParam
	 * @param sql
	 *            SQL statement
	 * @return ResultSet
	 * @throws SQLException
	 *             SQLException
	 */
	protected ResultSet executeQuery(AutoDataSourceParam autoDataSourceParam,
			String sql) throws SQLException {
		return autoDataSourceParam
				.getConnection()
				.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery();
	}

}
