package study3;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jolson
 * @Create 2020-08-29 18:04
 */
public class GenericTest {

    /*
    1. 泛型在继承方面的体现

      虽然类A是类B的父类，但是G<A> 和G<B>二者不具备子父类关系，二者是并列关系。

       补充：类A是类B的父类，A<G> 是 B<G> 的父类。例如test2里的，List<String>是ArrayList<String>的父类

     */
    @Test
    public void test1(){

        Object obj = null;
        String str = null;
        obj = str;//多态的体现

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;//也是对的


        //编译不通过
//        Date date = new Date();
//        str = date;

        List<Object> list1 = null;
        List<String> list2 = new ArrayList<String>();
        //此时的list1和list2的类型不具有子父类关系，尽管Object是String的父类
        //编译不通过
//        list1 = list2;

        /*
        反证法：
        假设list1 = list2;
           list1.add(123);导致混入非String的数据。出错。
         */

        show(list1);
        show1(list2);

    }



    public void show1(List<String> list){

    }

    public void show(List<Object> list){

    }

    @Test
    public void test2(){

        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;

        list1 = list3;
        list2 = list3;

        List<String> list4 = new ArrayList<>();//因为实际上List就是ArrayList的父类

    }


    /*
    2. 通配符的使用
       通配符：?

       类A是类B的父类，G<A>和G<B>是没有关系的（例如List<Object> list1和List<String> list2），二者共同的父类是：G<?>


     */

    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;//通配符，能配String也能配Object.相当于作为了list1和list2的通用父类

        list = list1;
        list = list2;
        //编译通过

        print(list1);
        print(list2);


        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");

        list = list3;//list是List3的父类。所以是多态的感觉。
        //添加(写入)：对于List<?>就不能向其内部添加数据,除了添加null之外。
//        list.add("DD");
//        list.add('?');
        list.add(null);//不管什么类型的对象都可以是null

        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }

    public void print(List<?> list){//这个?可以为object和String
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /*
    3.有限制条件的通配符的使用。
        ? extends A:
                G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类
                看成小于等于A。有个上限是A
        ? super A:
                G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类
                看成大于等于A。有个下限是A
     */
    @Test
    public void test4(){

        List<? extends Person> list1 = null;//可以作为List<person>和List<student>的父类，因为student是person的子类
        List<? super Person> list2 = null;//可以作为List<person>和List<Object>的父类，因为object是person的父类

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据：
        list1 = list3;//List1要小于等于Person,所以用person或者它的父类接受是合适的（因为多态）。用子类接受是不合适的。
        Person p = list1.get(0);
        Object obj0 = list1.get(0);
        //编译不通过
        //Student s = list1.get(0);


        list2 = list4;//List2要大于等于person，你不知道它里面装的什么的情况下，用最大的父类接受是合适的，object。
        Object obj = list2.get(0);
        ////编译不通过
//        Person obj = list2.get(0);

        //写入数据：
        //编译不通过
//        list1.add(new Student());//因为可能list1的实际类型比Student还小，是它的子类，那么是不能把父类的值赋给子类的。

        //编译通过，因为显然list2类比Student大，把子类赋给父类是可以的，多态。
        list2.add(new Person());
        list2.add(new Student());

    }
}
