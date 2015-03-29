package demo.test.pojo;

import org.mybatis.extension.auto.annotation.Entity;

@Entity
public class Test3 {
	private String TestName3;

	public String getTestName3() {
		return TestName3;
	}

	public void setTestName3(String testName3) {
		TestName3 = testName3;
	}
}
