package org.mybatis.extension.auto.sql;

public class MysqlColumnSql extends BaseSql {

	private String fieldName;

	private String fieldType;

	private int fieldLength;

	private boolean fieldNullable;

	private boolean fieldIsId;

	private String fieldIdType;

	
	public MysqlColumnSql(boolean isFormatSql, String fieldName,
			String fieldType, int fieldLength, boolean fieldNullable,
			boolean fieldIsId, String fieldIdType) {
		super(isFormatSql);
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldLength = fieldLength;
		this.fieldNullable = fieldNullable;
		this.fieldIsId = fieldIsId;
		this.sql = new StringBuffer();
		if (this.fieldIsId) {
			this.fieldType = "INT";
			this.fieldLength = 11;
			if (fieldIdType.equals("AUTO_INCREMENT")) {
				this.fieldIdType = fieldIdType;
			} else {
				this.fieldIdType = null;
			}
		}
	}

	public StringBuffer getCreateColumn() {
		if (this.isFormatSql) {
			this.sql.append("\t");
		}
		this.sql.append(fieldName);
		this.sql.append(" ");
		this.sql.append(fieldType + "(" + fieldLength + ")");
		this.sql.append(" ");
		this.sql.append(fieldNullable ? "" : "NOT NULL");
		this.sql.append(" ");
		this.sql.append(this.fieldIdType);

		return this.sql;
	}
}
