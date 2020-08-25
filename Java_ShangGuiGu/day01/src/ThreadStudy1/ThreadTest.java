package ThreadStudy1;

/**
 * 方式一：继承Thread类的方式：
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start()：①启动当前线程 ② 调用当前线程的run()
 *
 * 例子，遍历100以内的所有偶数
 * @author Jolson
 * @Create 2020-08-10 15:32
 */
//1. 创建一个继承于Thread类的子类
class MyThread extends Thread{
// 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i%2==0) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }

}

public class ThreadTest {
    public static void main(String[] args) {
//        3. 创建Thread类的子类的对象
        MyThread t1 = new MyThread();
//        4. 通过此对象调用start()：①启动当前线程 ② 调用当前线程的run()

        t1.start();
//        问题一：我们启动一个线程，必须调用start()，不能调用run()的方式启动线程。
//        t1.run();//如果这样也调上面的方法，但不是调的线程。顺序调用是上面的先调用，再执行下面的。所以是一个线程。

//        问题二：如果再启动一个线程，遍历100以上的偶数。必须重新创建一个Thread子类的对象，调用此对象的start().
//            t1.start();//会报错
        MyThread t2 = new MyThread();
        t2.start();//多造一个对象

        System.out.println("hello");
        for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
        }
        /*看打印结果就可以明白这两个进程是并行的，同时进行的*/
    }
}
