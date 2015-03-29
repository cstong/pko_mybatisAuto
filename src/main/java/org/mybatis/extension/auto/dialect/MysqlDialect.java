package org.mybatis.extension.auto.dialect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.extension.auto.annotation.Entity;
import org.mybatis.extension.auto.annotation.Field;
import org.mybatis.extension.auto.annotation.ForeignKey;
import org.mybatis.extension.auto.annotation.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

/**
 * 
 * Automatically create or update table dialect using mysql.
 * 
 * @author popkidorc
 * @creation 2015年3月28日
 * 
 */
public class MysqlDialect extends DatabaseDialect {

	public MysqlDialect(Connection connection, boolean isShowSql,
			boolean isFormatSql, List<Class<?>> clazzes) {
		super(connection, isShowSql, isFormatSql, clazzes);
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void create() throws SQLException {
		System.out.println("======create====");
		List<String> sqls = new ArrayList<String>();
		List<String> foreignSqls = new ArrayList<String>();
		// 取消外键约束，方便create或者update
		sqls.add("SET FOREIGN_KEY_CHECKS = 0;");
		for (Class<?> clazz : this.clazzes) {
			StringBuffer sql = new StringBuffer("");
			String idField = "";
			if (!clazz.isAnnotationPresent(Entity.class)) {
				continue;
			}
			Entity tableAnnotion = (Entity) clazz.getAnnotation(Entity.class);
			String tableName = tableAnnotion.tableName();
			if (tableName.equals("")) {
				tableName = ClassUtils.getShortName(clazz).toUpperCase();
			}
			sqls.add("DROP TABLE IF EXISTS " + tableName + ";");
			sql.append("CREATE TABLE " + tableName + "(\n");
			java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
			for (java.lang.reflect.Field field : fields) {
				if (!field.isAnnotationPresent(Field.class)) {
					continue;
				}
				Field fieldAnnotion = (Field) field.getAnnotation(Field.class);
				String fieldName = fieldAnnotion.fieldName();
				String fieldType = fieldAnnotion.type().toString();
				int fieldLength = fieldAnnotion.length();
				boolean fieldNullable = fieldAnnotion.nullable();
				if (fieldName.equals("")) {
					fieldName = field.getName().toUpperCase();
				}
				boolean hasIdAnnotion = field.isAnnotationPresent(Id.class);
				if (hasIdAnnotion) {
					fieldType = "INT";
					fieldLength = 11;
					sql.append("\t" + fieldName + " " + fieldType + "("
							+ fieldLength + ") NOT NULL AUTO_INCREMENT");
				} else {
					if (fieldAnnotion.fKey().length > 0) {
						ForeignKey foreignKey = fieldAnnotion.fKey()[0];
						String foreignKeyTableName = foreignKey.tableName();
						String foreignKeyFieldName = foreignKey.fieldName();
						if (!foreignKeyTableName.equals("")
								&& !foreignKeyFieldName.equals("")) {
							String foreign_key_name = "FK_" + tableName + "_"
									+ fieldName;
							StringBuffer foreignSql = new StringBuffer("");
							foreignSql.append("ALTER TABLE " + tableName + " ");
							foreignSql.append("ADD CONSTRAINT "
									+ foreign_key_name + " FOREIGN KEY("
									+ fieldName + ") REFERENCES "
									+ foreignKeyTableName + "("
									+ foreignKeyFieldName + ");\n");
							foreignSqls.add(foreignSql.toString());
							fieldType = "INT";
							fieldLength = 11;
						}
					}
					if (fieldType.endsWith("INT")) {
						fieldLength = fieldLength == 255 ? 11 : fieldLength;
					}
					sql.append("\t" + fieldName + " " + fieldType + "("
							+ fieldLength + ")");
					sql.append(fieldNullable ? "" : " NOT NULL");
				}
				if (idField.equals("")) {
					idField = hasIdAnnotion ? fieldName : "";
				}
				sql.append(",\n");
			}
			if (!idField.equals("")) {
				sql.append("\tPRIMARY KEY (" + idField + ")");
			}
			sql.append("\n);");
			sqls.add(sql.toString());
		}
		sqls.addAll(foreignSqls);
		sqls.add("SET FOREIGN_KEY_CHECKS = 1;");
		this.executeSqls(sqls);
	}

	@Override
	public void update() throws SQLException {
		System.out.println("======update====");
		List<String> sqls = new ArrayList<String>();
		List<String> foreignSqls = new ArrayList<String>();
		List<String> alterUpdateSqls = new ArrayList<String>();
		sqls.add("SET FOREIGN_KEY_CHECKS = 0;");
		for (Class<?> clazz : this.clazzes) {
			StringBuffer sql = new StringBuffer("");
			String idField = "";
			if (!clazz.isAnnotationPresent(Entity.class)) {
				continue;
			}
			Entity tableAnnotion = (Entity) clazz.getAnnotation(Entity.class);
			String tableName = tableAnnotion.tableName();
			if (tableName.equals("")) {
				tableName = ClassUtils.getShortName(clazz).toUpperCase();
			}
			sql.append("CREATE TABLE IF NOT EXISTS " + tableName + "(\n");

			java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
			for (java.lang.reflect.Field field : fields) {
				if (!field.isAnnotationPresent(Field.class)) {
					continue;
				}
				Field fieldAnnotion = (Field) field.getAnnotation(Field.class);
				String fieldName = fieldAnnotion.fieldName();
				String fieldType = fieldAnnotion.type().toString();
				int fieldLength = fieldAnnotion.length();
				boolean fieldNullable = fieldAnnotion.nullable();
				if (fieldName.equals("")) {
					fieldName = field.getName().toUpperCase();
				}
				boolean hasIdAnnotion = field.isAnnotationPresent(Id.class);
				if (hasIdAnnotion) {
					sql.append("\t" + fieldName
							+ " INT(11) NOT NULL AUTO_INCREMENT");
				} else {
					if (fieldAnnotion.fKey().length > 0) {
						ForeignKey foreignKey = fieldAnnotion.fKey()[0];
						String foreignKeyTableName = foreignKey.tableName();
						String foreignKeyFieldName = foreignKey.fieldName();
						if (!foreignKeyTableName.equals("")
								&& !foreignKeyFieldName.equals("")) {
							String foreign_key_name = "FK_" + tableName + "_"
									+ fieldName;
							PreparedStatement preparedStatement = this.connection
									.prepareStatement(
											"SELECT COLUMN_NAME FROM information_schema.KEY_COLUMN_USAGE where constraint_name='"
													+ foreign_key_name + "'",
											ResultSet.TYPE_SCROLL_INSENSITIVE,
											ResultSet.CONCUR_READ_ONLY);
							ResultSet resultSet = preparedStatement
									.executeQuery();
							resultSet.last();
							if (resultSet.getRow() == 0) {
								StringBuffer foreignSql = new StringBuffer("");
								foreignSql.append("ALTER TABLE " + tableName
										+ " ");
								foreignSql.append("ADD CONSTRAINT "
										+ foreign_key_name + " FOREIGN KEY("
										+ fieldName + ") REFERENCES "
										+ foreignKeyTableName + "("
										+ foreignKeyFieldName + ");\n");
								foreignSqls.add(foreignSql.toString());
							}
							fieldType = "INT";
							fieldLength = 11;
						}
					}
					if (fieldType.endsWith("INT")) {
						fieldLength = fieldLength == 255 ? 11 : fieldLength;
					}
					sql.append("\t" + fieldName + " " + fieldType + "("
							+ fieldLength + ")");
					sql.append(!fieldNullable ? " NOT NULL" : "");
				}
				if (idField.equals("")) {
					idField = hasIdAnnotion ? fieldName : "";
				}
				// 查看表结构，是否已有该字段
				String assertField = "DESCRIBE " + tableName + " " + fieldName;
				PreparedStatement preparedStatement = this.connection
						.prepareStatement(assertField,
								ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.last();
				if (resultSet.getRow() == 0) {
					if (fieldType.equalsIgnoreCase("INT")) {
						fieldLength = fieldLength == 255 ? 11 : fieldLength;
					}
					StringBuffer alterUpdateSql = new StringBuffer("");
					alterUpdateSql.append("ALTER TABLE " + tableName
							+ " ADD COLUMN ");
					alterUpdateSql.append(fieldName + " " + fieldType + "("
							+ fieldLength + ")");
					alterUpdateSql.append(!fieldNullable ? " NOT NULL" : "");
					alterUpdateSqls.add(alterUpdateSql.toString());
				}
				sql.append(",\n");
			}
			if (!idField.equals("")) {
				sql.append("\tPRIMARY KEY (" + idField + ")");
			}
			sql.append("\n);\n");
			sqls.add(sql.toString());
		}
		sqls.addAll(foreignSqls);
		sqls.addAll(alterUpdateSqls);
		sqls.add("SET FOREIGN_KEY_CHECKS = 1;");
		this.executeSqls(sqls);
	}

	private void executeSqls(List<String> sqls) throws SQLException {
		Statement statement = this.connection.createStatement();
		for (String sql : sqls) {
			if (this.isShowSql) {
				logger.info("mybatiSql : " + sql);
			}
			statement.addBatch(sql);
		}
		statement.executeBatch();
		this.connection.close();
	}
}
