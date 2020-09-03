package study1;

/**
 * @author Jolson
 * @Create 2020-08-27 23:16
 */
public class SubOrder1<T> extends Order<T>{//SubOrder1<T>:仍然是泛型类,并且Order里的泛型也被指定为T
    /*
    * 写成 public class SubOrder1<T> extends Order{} ，会默认order里的泛型为object类型
    * 写成 public class SubOrder1<T> extends Order<E>{}，会需要你去实现类E，因为这里显然没有E可以输入的
    * 写成 public class SubOrder1<T> extends Order<T>{} , 那么输入的T同时会给Order的泛型。使其类型一致。
    * */


}
