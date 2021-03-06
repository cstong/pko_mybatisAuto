package org.mybatis.extension.auto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * ForeignKey annotation
 * 
 * @author popkidorc
 * @since 2015年3月30日
 * 
 */
@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ForeignKey {

	public abstract String foreignKeyName() default "";

	public abstract String tableName();

	public abstract String columnName();
}
