package org.mybatis.extension.auto.sql;

import org.mybatis.extension.auto.type.ColumnType;

/**
 * 
 * Column type map
 * 
 * @author popkidorc
 * @date 2015年4月2日
 * 
 */
public interface IColumnTypeMap extends IBaseSql {

	public ColumnType getColumnType(Class<?> clazz);

}
