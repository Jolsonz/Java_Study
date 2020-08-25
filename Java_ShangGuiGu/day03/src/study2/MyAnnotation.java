package study2;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author Jolson
 * @Create 2020-08-21 14:09
 */
@Inherited//继承性
@Repeatable(MyAnnotations.class)//重复注解Repeatable
@Retention(RetentionPolicy.RUNTIME)//生命周期是runtime
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})//指定被修饰的 Annotation 能用于修饰哪些程序元素
public @interface MyAnnotation {

    String value() default "hello";
}
