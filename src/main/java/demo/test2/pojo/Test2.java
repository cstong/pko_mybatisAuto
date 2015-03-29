package demo.test2.pojo;

import org.mybatis.extension.auto.annotation.Entity;

@Entity
public class Test2 {
	private String testName2;

	public String getTestName2() {
		return testName2;
	}

	public void setTestName2(String testName2) {
		this.testName2 = testName2;
	}

}
