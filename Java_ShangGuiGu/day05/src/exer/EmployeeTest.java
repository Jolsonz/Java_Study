package exer;

import org.junit.Test;

import java.util.*;

/**
 * 创建该类的 5 个对象，并把这些对象放入 TreeSet 集合中（下一章：TreeSet 需使用泛型来定义）
 分别按以下两种方式对集合中的元素进行排序，并遍历输出：

 1). 使Employee 实现 Comparable 接口，并按 name 排序
 2). 创建 TreeSet 时传入 Comparator对象，按生日日期的先后排序。

 * @author Jolson
 * @Create 2020-08-26 22:40
 */
public class EmployeeTest {
    //1. 用自然排序
    @Test
    public void test1(){
        TreeSet<Employee> set = new TreeSet();//当然，可以再这里面直接用匿名实现类的匿名对象也行。

        Employee e1 = new Employee("刘德华",55,new MyDate(1965,5,4));
        Employee e2 = new Employee("张学友",43,new MyDate(1987,5,4));
        Employee e3 = new Employee("郭富城",44,new MyDate(1987,5,9));
        Employee e4 = new Employee("黎明",51,new MyDate(1954,8,12));
        Employee e5 = new Employee("梁朝伟",21,new MyDate(1978,12,4));
        //中文的是按UTF-8看数字谁打谁小。

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator<Employee> it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());//调用toString了
        }
    }

    @Test
    public void test2(){
        Comparator c = new Comparator<Employee>() {//匿名实现类的对象c，<Employee>写在后面可能是因为是传输参数。
            @Override
            public int compare(Employee e1, Employee e2) {
                    //拿到生日
                    MyDate b1 = e1.getBirthday();
                    MyDate b2 = e2.getBirthday();
                    return b1.compareTo(b2);//要在MyDate里继承comparable,重写这个方法。

            }
        };
        TreeSet<Employee> set = new TreeSet(c);

        Employee e1 = new Employee("刘德华",55,new MyDate(1965,5,4));
        Employee e2 = new Employee("张学友",43,new MyDate(1987,5,4));
        Employee e3 = new Employee("郭富城",44,new MyDate(1987,5,9));
        Employee e4 = new Employee("黎明",51,new MyDate(1954,8,12));
        Employee e5 = new Employee("梁朝伟",21,new MyDate(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());//调用toString了
        }
    }

}
