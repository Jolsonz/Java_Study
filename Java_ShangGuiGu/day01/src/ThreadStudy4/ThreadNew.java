package ThreadStudy4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现Callable接口。 --- JDK 5.0新增
 *
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
 * 1. call()可以有返回值的。
 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息。(Runnable只能try-catch)
 * 3. Callable是支持泛型的
 *
 * @author Jolson
 * @Create 2020-08-16 16:51
 */
//1.创建一个实现Callable的实现类
class NumThread implements Callable{
    @Override
    //2.实现call方法，将此线程需要执行的操作声明在call()中
    public Object call() throws Exception {
//        遍历100以内的偶数，并返回其和
        int sum = 0;
        for (int i = 0; i <= 100 ; i++) {
            if(i%2==0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
                sum+=i;
            }
        }
        return sum;
    }
}

public class ThreadNew {
    public static void main(String[] args) {
        //3.创建Callable接口实现类的对象
        NumThread t = new NumThread();
        //4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask f = new FutureTask(t);
        //5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(f).start();

        try {
            //6.获取Callable中call方法的返回值
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
            Object sum = f.get();
            System.out.println("这是总计值"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
