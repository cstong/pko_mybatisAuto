# pko_mybatisAuto
Mybatis Automatically build table


1.Dependent jar:

slf4j-api
servlet-api
spring-web


2.Listener config in web.xml:

<!-- mybatis auto listener start -->
<listener>
	<listener-class>org.mybatis.extension.auto.listener.AutoDatabaseListener</listener-class>
</listener>
<!-- mybatis auto listener end -->


3.Spring config in applicationContext.xml:

<bean class="org.mybatis.extension.auto.driver.AutoDataSourceDriver">
	<property name="auto" value="create" />
	<property name="showSql" value="true" />
	<property name="formatSql" value="true" />
	<property name="testSql" value="select 1 from dual;" />
	<property name="autoPackages">
		<list>
			<value>demo.mybatis.pojo</value>
		</list>
	</property>
	<property name="dataSource" ref="dataSource" />
	<property name="dialectClassName"
		value="org.mybatis.extension.auto.dialect.MysqlDialect" />
</bean>


4.Entity class:

package demo.mybatis.pojo;

import org.mybatis.extension.auto.annotation.Entity;
import org.mybatis.extension.auto.annotation.Field;
import org.mybatis.extension.auto.annotation.Id;

@Entity(tableName = "DEMO_TABLE", tableComments = "a demo table , including three columns")
public class DemoEntity {

	@Field
	@Id
	private int EntityId;

	@Field
	private String EntityName;

	@Field(fieldName = "COMMENT", length = 300)
	private String EntityComment;

	public int getEntityId() {
		return EntityId;
	}

	public void setEntityId(int entityId) {
		EntityId = entityId;
	}

	public String getEntityName() {
		return EntityName;
	}

	public void setEntityName(String entityName) {
		EntityName = entityName;
	}

	public String getEntityComment() {
		return EntityComment;
	}

	public void setEntityComment(String entityComment) {
		EntityComment = entityComment;
	}

}
