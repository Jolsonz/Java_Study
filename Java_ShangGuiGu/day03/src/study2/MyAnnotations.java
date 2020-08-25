package study2;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author Jolson
 * @Create 2020-08-21 15:55
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)//这个生命周期也要是runtime。
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})//这里target要和MyAnnotation一样，不然会报错
public @interface MyAnnotations {

    MyAnnotation[] value();//一个数组，为了重复放
}

