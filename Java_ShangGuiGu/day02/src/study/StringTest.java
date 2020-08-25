package study;

import org.junit.Test;

/**
 * @author Jolson
 * @Create 2020-08-17 16:13
 */
public class StringTest {

    /**
    String:字符串，使用一对""引起来表示。
    1.String声明为final的，不可被继承
    2.String实现了Serializable接口：表示字符串是支持序列化的。意思是可以通过网络来字节传输的。
            实现了Comparable接口：表示String可以比较大小
    3.String内部定义了final char[] value用于存储字符串数据
    4.String:代表不可变的字符序列。简称：不可变性。
        体现：1.当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值。
             2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
             3. 当调用String的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
    5.通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。
    6.字符串常量池中是不会存储相同内容的字符串的。
     */
    @Test
    public void test1(){
        String s1 = "abc";//它这个是不用new的，是比较特殊的对象。是字面量的定义方式。
        String s2 = "abc";//这里s1与s2是用的同一个
        System.out.println(s1 == s2);//是true，说明s1与s2指向的是同一个"abc"，字符串值声明在字符串常量池中。
        s1 = "hello";
        System.out.println(s1);//hello
        System.out.println(s2);//abc

        System.out.println("**************");
        String s3 = "abc";
        s3 += "def";//拼接之后，是在字符串常量池中新建了一个"abcdef"
        System.out.println(s3);
        System.out.println(s2);

        System.out.println("*****************");

        String s4 = "abcaaaaa";
        String s5 = s4.replace('a', 'm');
        System.out.println(s4);//abc
        System.out.println(s5);//mbc
    }

    /*
    String的实例化方式：
    方式一：通过字面量定义的方式
    方式二：通过new + 构造器的方式

     面试题：String s = new String("abc");方式创建对象，在内存中创建了几个对象？
            两个:一个是堆空间中new结构，另一个是char[]对应的常量池中的数据："abc"

    注意常量池中的数据永远是不可变的。
     */
    @Test
    public void test2(){
        //通过字面量定义的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中。
        String s1 = "javaEE";
        String s2 = "javaEE";

        //通过new + 构造器的方式:此时的s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址值。
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false

        System.out.println("***********************");
        Person p1 = new Person("Tom",12);
        Person p2 = new Person("Tom",12);

        System.out.println(p1.name.equals(p2.name));//true,内容是一样的。
        System.out.println(p1.name == p2.name);//true,这里也是在常量池中，因为是用字面量的方式定义的。

        p1.name = "Jerry";
        System.out.println(p2.name);//Tom

    }

    /**
    结论：
    1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
    2.只要其中有一个是变量，结果就在堆中。
    3.如果拼接的结果调用intern()方法，返回值就在常量池中
     */
    @Test
    public void test3(){
        String s1 = "javaEE";//常量池
        String s2 = "hadoop";//常量池
        String s3 = "javaEEhadoop";//常量池
        String s4 = "javaEE" + "hadoop";//常量池
        String s5 = s1 + "hadoop";//堆
        String s6 = "javaEE" + s2;//堆
        String s7 = s1 + s2;//堆

        System.out.println(s3 == s4);//true,都在常量池中，是一个字符串
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        String s8 = s6.intern();//返回值得到的s8使用的常量值中已经存在的“javaEEhadoop”
        //intern应该就是返回字符串的常量池的引用（指针）
        System.out.println(s3 == s8);//true


    }

    @Test
    public void test4(){
        String s1 = "javaEEtest";
        String s2 = "test";
        String s3 = s2 + "test";
        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//改成final之后就是常量了，在常量池了
        String s5 = s4 + "test";//这里s5也在常量池了。
        System.out.println(s5 == s1);//true
    }
}
