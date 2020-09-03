package study1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jolson
 * @Create 2020-08-27 23:11
 */
public class Order<T> {//一般是T,E，在实例化的时候才会指定T是什么类型。
    String orderName;
    int orderId;
    //类的内部结构就可以使用类的泛型

    T orderT;//T是类型名，orderT是变量名

    public Order() {
//        T[] arr = new T[10];//编译不通过
        T[] arr =(T[]) new Objects[10];//这个是new一个T类型的数组的方法
    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }




    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    //静态方法中不能使用类的泛型。
//    public static void show(T orderT){//因为静态方法得确定参数，而T是实例化的时候才能确定的。
//        System.out.println(orderT);
//    }


    //泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。比如类的泛型参数是T，而方法里的泛型参数是E，没有关系。
    //          换句话说，泛型方法所属的类是不是泛型类都没有关系。
    //泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在实例化类时确定。
    public static <E> List<E> copyFromArrayToList(E[] arr){
//public static List<E> copyFromArrayToList(E[] arr) 这么写编译器会认为有个类型叫做E，而不是泛型。
        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;

    }
}
