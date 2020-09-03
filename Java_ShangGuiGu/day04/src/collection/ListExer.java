package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 区分List中remove(int index)和remove(Object obj)
 * @author Jolson
 * @Create 2020-08-25 23:01
 */
public class ListExer {

    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//
    }

    private void updateList(List list) {
        list.remove(2);//删掉位置2的对象。注意这里不会自动装箱，因为形参是有选择的，默认是基本数据。
        list.remove(new Integer(2));//删掉equals 这个对象的对象，即值为2的数据
    }

}
