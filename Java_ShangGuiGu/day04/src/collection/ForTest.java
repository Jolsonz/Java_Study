package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * jdk 5.0 新增了foreach循环，用于遍历集合、数组
 *
 * @author Jolson
 * @Create 2020-08-25 22:18
 */
public class ForTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合元素的类型 局部变量 : 集合对象)
        //内部仍然调用了迭代器。
        for(Object obj : coll){
            System.out.println(obj);//动态绑定，会调用实际类型的toString
        }
    }

    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        //for(数组元素的类型 局部变量 : 数组对象)
        for(int i : arr){
            System.out.println(i);
        }
    }

    //练习题
    @Test
    public void test3(){

        String[] arr = new String[]{"MM","MM","MM"};

//        //方式一：普通for赋值
        for(int i = 0;i < arr.length;i++){
            arr[i] = "GG";//可以改变每个元素指向的String，堆中指针变了，指向常量池中的"GG"
        }


        /**
         *  方式二：增强for循环
         *  这里是把arr中的每个元素取出来赋给s。
         *  s改变只改变s指向的常量池中的元素。实际上arr数组的堆中的指针都是指着"MM"的
         */
        for(String s : arr){
            s = "GG";
        }

        for(var s : arr){
            System.out.println(s);
        }


    }
}
