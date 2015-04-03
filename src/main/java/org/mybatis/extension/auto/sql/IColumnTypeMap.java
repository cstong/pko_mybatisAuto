package org.mybatis.extension.auto.sql;

import org.mybatis.extension.auto.type.ColumnType;

/**
 * 
 * Column type map
 * 
 * @author popkidorc
 * @since 2015年4月2日
 * 
 */
public interface IColumnTypeMap extends IBaseSql {

	/**
	 * 
	 * Get column type
	 * 
	 * @param clazz
	 *            class
	 * @return ColumnType
	 */
	public ColumnType getColumnType(Class<?> clazz);

}
