package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Collection接口中声明的方法的测试
 *
 * 结论：
 * 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()。不然只是==的功能，不是equals的功能。
 *
 * @author Jolson
 * @Create 2020-08-25 12:15
 */
public class CollectionUse {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
//        Person p = new Person("Jerry",20);
//        coll.add(p);

        coll.add("Tom");
        coll.add(false);

        //1.contains(Object obj):判断当前集合中是否包含obj。是通过元素的equals方法来判断是否是同一个对象
        boolean contains = coll.contains(123);
        System.out.println(contains);//true
        System.out.println(coll.contains(new String("Tom")));//true,这个也调的是equals，但是是默认的equals，所以不会print东西

        //        System.out.println(coll.contains(p));//true，这个因为是同一个对象，同一个指针，所以是true。
        System.out.println(coll.contains(new Person("Jerry",20)));
        //true,因为调的是equals方法，虽然对象不一样，但内容是一样的。因为jerry在第三个，所以会调用3次equals，输出三句。
        //如果在person类中把重写的equals给注释掉，它会调用object的equals，那么发现两对象不是一个指针，就会返回false。

        //2.containsAll(Collection coll1):判断形参coll1中的所有元素是否都存在于当前集合中。
        Collection coll1 = Arrays.asList(123,4567);
        System.out.println(coll.containsAll(coll1));//false
     }


    @Test
    public void test2(){
         //3.remove(Object obj):从当前集合中移除obj元素。也会调用equals
         Collection coll = new ArrayList();
         coll.add(123);
         coll.add(456);
         coll.add(new Person("Jerry",20));
         coll.add("Tom");
         coll.add(false);

         coll.remove(123);
         System.out.println(coll);//相当于调toString方法，person中是重写了的。

         coll.remove(new Person("Jerry",20));//重写了equals之后是能正常移除的。
//         输出两次Person equals()....，因为对比了两次对比到了。
         System.out.println(coll);

         //4. removeAll(Collection coll1):差集：从当前集合中移除coll1中所有的元素。
         Collection coll1 = Arrays.asList(123,456);
         coll.removeAll(coll1);//123已经被移除了，只移除差集456
         System.out.println(coll);
     }

    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //5.retainAll(Collection coll1):交集：获取当前集合和coll1集合的交集，并返回给当前集合。注意修改了集合。
        Collection coll1 = Arrays.asList(123,456,789);
        coll.retainAll(coll1);//分别拿coll中的每个元素和coll1中的比，所以Person equals()....会输出三次
        System.out.println(coll);
        //此时Coll里是[123, 456]

        //6.equals(Object obj):要想返回true，需要当前集合和形参集合的元素都相同。
        List coll2 = new ArrayList();//这里写list也没差。
        coll2.add(123);
        coll2.add(456);
        //注意顺序也要一致。List都是有序的

        System.out.println(coll.equals(coll2));
    }

    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //7.hashCode():返回当前对象的哈希值
        System.out.println(coll.hashCode());

        //8.集合 --->数组：toArray()
        Object[] arr = coll.toArray();
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);//也会调用toString
        }


        //拓展：数组 --->集合:调用Arrays类的静态方法asList()
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);

        List<int[]> arr1 = Arrays.asList(new int[]{123, 456});//<int[]>省略之后更让人看不出来。
        System.out.println(arr1.size());//1
        //认为存了一个元素，是一个一维的数组，把这个一维数组当一个对象了。

        List<Integer> arr2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr2.size());//2

//        //9.iterator():返回Iterator接口的实例，用于遍历集合元素。放在IteratorTest.java中测试

    }
}
