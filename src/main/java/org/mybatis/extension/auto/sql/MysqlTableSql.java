package org.mybatis.extension.auto.sql;

//public class MysqlTableSql extends BaseSql {
//	
//	private String tableName;
//
//
//	// public String getCreateTable(String tableName, String tableComment) {
//	// return "CREATE TABLE " + tableName + "(\n";
//	// }
//
//	public StringBuffer getCreateTable() {
//		if (this.isFormatSql) {
//			this.sql.append("\t");
//		}
//		this.sql.append("CREATE TABLE " + tableName);
//		this.sql.append("(");
//		if (this.isFormatSql) {
//			this.sql.append("\n");
//		}
//
//		//
//		// if (!idField.equals("")) {
//		// sql.append("\tPRIMARY KEY (" + idField + ")");
//		// }
//		if (this.isFormatSql) {
//			this.sql.append("\n");
//		}
//		sql.append(");");
//		// sqls.add(sql.toString());
//		return this.sql;
//	}
// }
