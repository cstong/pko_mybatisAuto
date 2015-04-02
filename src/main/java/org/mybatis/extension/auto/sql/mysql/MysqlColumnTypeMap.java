package org.mybatis.extension.auto.sql.mysql;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.extension.auto.sql.BaseSql;
import org.mybatis.extension.auto.sql.IColumnTypeMap;
import org.mybatis.extension.auto.type.ColumnType;

public class MysqlColumnTypeMap extends BaseSql implements IColumnTypeMap {

	private Map<Class<?>, ColumnType> columnTypeMap;

	public MysqlColumnTypeMap() {
		columnTypeMap = new HashMap<Class<?>, ColumnType>();
		columnTypeMap.put(String.class, ColumnType.VARCHAR);
		columnTypeMap.put(int.class, ColumnType.INT);
		columnTypeMap.put(float.class, ColumnType.FLOAT);
		columnTypeMap.put(double.class, ColumnType.DOUBLE);
	}

	@Override
	public ColumnType getColumnType(Class<?> clazz) {
		return columnTypeMap.get(clazz);
	}

}
