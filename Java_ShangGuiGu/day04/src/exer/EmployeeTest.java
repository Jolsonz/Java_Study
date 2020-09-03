package exer;

import collection.Person;
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
        TreeSet set = new TreeSet();//当然，可以再这里面直接用匿名实现类的匿名对象也行。

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

        Iterator it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());//调用toString了
        }
    }

    @Test
    public void test2(){
        Comparator c = new Comparator() {//匿名实现类的对象c
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Employee && o2 instanceof Employee){
                    Employee e1 = (Employee) o1;
                    Employee e2 = (Employee) o2;

                    //拿到生日
                    MyDate b1 = e1.getBirthday();
                    MyDate b2 = e2.getBirthday();

                    //方式一：这么写有点麻烦，可以写到MyDate中去。
                    //比较年
//                    int minusYear = b1.getYear() - b2.getYear();
//                    if(minusYear != 0){
//                        return minusYear;
//                    }
//                    //比较月
//                    int minusMonth = b1.getMonth() - b2.getMonth();
//                    if(minusMonth != 0){
//                        return minusMonth;
//                    }
//                    //比较日
//                    return b1.getDay() - b2.getDay();

                    //方式二：
                    return b1.compareTo(b2);//要在MyDate里继承comparable,重写这个方法。
                }
                throw new RuntimeException("传入的类型不一致");

            }
        };
        TreeSet set = new TreeSet(c);

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

    //练习：在List内去除重复数字值，要求尽量简单
    @Test
    public void test3(){
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(3);
        list.add(9);
        list.add(9);
        list.add(9);
        list.add(9);
        list.add(9);
        List list2 = duplicateList(list);
        for (Object integer : list2) {
            System.out.println(integer);
        }

    }

    /**
     * 注意方法之中是不能定义方法的。
     * @param list
     * @return
     */
    public static List duplicateList(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }

    /**
     * 考察hashSet添加元素的独特方式，记住，先计算hashCode，再调用equals
     */
    @Test
    public void test4(){
        //这里的person调了collection的person.
        //其中Person类中重写了hashCode()和equal()方法
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");
        set.add(p1);
        set.add(p2);
        //此时两元素。

        p1.name = "CC";
//        System.out.println(set.remove(p1));//返回false
        set.remove(p1);
        //注意这里是删不掉的，因为编译器会拿着name:CC,age:1001去计算hashCode。这样计算出来的和一开始的p1不在一个地方，是删不掉的。
        System.out.println(set);

        set.add(new Person("CC", 1001));
        //同理，这里能添加进去，因为p1没有占name:CC,age:1001的地方。它占的name:AA,age:1001的地方。
        System.out.println(set);

        set.add(new Person(1001,"AA"));
        //这里和p1的hashCode一样，但equals返回false，所以还是可以添加成功的。
        System.out.println(set);
    }
}
