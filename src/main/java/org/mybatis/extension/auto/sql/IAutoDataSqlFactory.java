package org.mybatis.extension.auto.sql;

/**
 * 
 * AutoData SQL statement Factory interface
 * 
 * @author popkidorc
 * @since 2015年4月3日
 * 
 */
public interface IAutoDataSqlFactory {

	public IConstraintSql getConstraintSql();

	public IAlterColumnSql getAlterColumnSql();

	public IAlterForeignKeySql getAlterForeignKeySql();

	public IAlterPrimaryKeySql getAlterPrimaryKeySql();

	public ICreateTableSql getCreateTableSql();

	public IDropAllConstraintSql getDropAllConstraintSql();

	public IDropPrimaryKeySql getDropPrimaryKeySql();

	public IDropTableSql getDropTableSql();

	public IColumnTypeMap getColumnTypeMap();
}
