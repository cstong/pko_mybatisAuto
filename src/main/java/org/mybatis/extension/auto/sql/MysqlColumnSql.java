package org.mybatis.extension.auto.sql;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.extension.auto.annotation.FieldType;
import org.mybatis.extension.auto.annotation.ForeignKey;
import org.mybatis.extension.auto.annotation.IdType;

public class MysqlColumnSql extends BaseSql {

	private String tableName;

	private String fieldName;

	private String fieldType;

	private int fieldLength;

	private boolean fieldNullable;

	private boolean fieldIsId;

	private String fieldIdType;

	private ForeignKey[] fieldForeignKeys;

	private List<String> foreignSqls;

	private String primaryKeySql;

	/**
	 * @param isFormatSql
	 * @param tableName
	 * @param fieldName
	 * @param fieldType
	 * @param fieldLength
	 * @param fieldNullable
	 * @param fieldIsId
	 * @param fieldIdType
	 * @param fieldForeignKeys
	 */
	public MysqlColumnSql(boolean isFormatSql, String tableName,
			String fieldName, String fieldType, int fieldLength,
			boolean fieldNullable, boolean fieldIsId, String fieldIdType,
			ForeignKey[] fieldForeignKeys) {
		super(isFormatSql);
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldLength = fieldLength;
		this.fieldNullable = fieldNullable;
		this.fieldIsId = fieldIsId;
		this.fieldForeignKeys = fieldForeignKeys;
		this.fieldIdType = "";
		this.sql = new StringBuffer();
		this.foreignSqls = new ArrayList<String>();
		if (this.fieldIsId) {
			this.fieldType = FieldType.INT.toString();
			this.fieldLength = 11;
			this.fieldNullable = false;
			if (fieldIdType.equals(IdType.AUTO_INCREMENT.toString())) {
				this.fieldIdType = fieldIdType;
			} else {
				this.fieldIdType = "";
			}
		}
	}

	public StringBuffer getCreateColumn() {
		if (this.isFormatSql) {
			this.sql.append("\t");
		}
		this.sql.append(this.fieldName);
		this.sql.append(" ");
		this.sql.append(this.fieldType + "(" + this.fieldLength + ")");
		this.sql.append(" ");
		this.sql.append(this.fieldNullable ? "" : "NOT NULL");
		this.sql.append(" ");
		this.sql.append(this.fieldIdType);
		return this.sql;
	}

	public List<String> getForeignSqls() {
		if (this.fieldForeignKeys.length <= 0) {
			return this.foreignSqls;
		}
		String foreignKeyTableName = this.fieldForeignKeys[0].tableName();
		String foreignKeyFieldName = this.fieldForeignKeys[0].fieldName();
		if (!foreignKeyTableName.equals("") && !foreignKeyFieldName.equals("")) {
			MysqlForeignKeySql mysqlForeignKeySql = new MysqlForeignKeySql(
					this.isFormatSql, this.tableName, this.fieldName,
					foreignKeyTableName, foreignKeyFieldName);
			this.foreignSqls.add(mysqlForeignKeySql.getCreateForeignKey()
					.toString());
		}
		return this.foreignSqls;
	}

	public String getPrimaryKeySql() {

		return this.primaryKeySql;
	}
}
