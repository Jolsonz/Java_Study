package study1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jolson
 * @Create 2020-08-27 23:15
 */
public class SubOrder extends Order<Integer> {//SubOrder:不是泛型类,若这里不指名order的类型，默认为object
    public static <E> List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;

    }
}
