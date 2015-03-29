package org.mybatis.extension.auto.parse;

import java.util.Iterator;
import java.util.List;

import org.mybatis.extension.auto.annotation.Entity;

/**
 * 
 * Parse scan package, and get entity classes
 * 
 * @author popkidorc
 * @creation 2015年3月30日
 * 
 */
public class EntityParseScanPackage {

	public static List<Class<?>> getClassName(String packageName) {
		List<Class<?>> clazzes = ParseScanPackage.getClassName(packageName,
				true);
		Iterator<Class<?>> clazzIterable = clazzes.iterator();
		while (clazzIterable.hasNext()) {
			if (!clazzIterable.next().isAnnotationPresent(Entity.class)) {
				clazzIterable.remove();
			}
		}
		return clazzes;
	}

	public static void main(String[] args) {
		for (Class<?> clazz : getClassName("demo.test.pojo")) {
			System.out.println(clazz.getName());
		}
	}
}
