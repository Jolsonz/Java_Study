package ThreadStudy1;

/**
 * 方式二：实现Runnable接口的方式：
 *  1. 创建一个实现了Runnable接口的类
 *  2. 实现类去实现Runnable中的抽象方法：run()
 *  3. 创建实现类的对象
 *  4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 *  5. 通过Thread类的对象调用start()
 *
 * thread类也实现了runnable接口
 *
 * @author Jolson
 * @Create 2020-08-11 21:56
 */
// 1. 创建一个实现了Runnable接口的类
class MyThread2 implements Runnable{
// 2. 实现类去实现Runnable中的抽象方法：run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2==0) System.out.println( Thread.currentThread().getName()+i);
            //因为这不是继承的thread类，没有getname()这个方法
        }
    }
}
public class ThreadTest1 {
    public static void main(String[] args) {
//        3. 创建实现类的对象
        MyThread2 myThread2 = new MyThread2();
//        4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t1 = new Thread(myThread2);
//        5. 通过Thread类的对象调用start()
        t1.start();
//        再创建一个线程
        Thread t2 = new Thread(myThread2);
        t2.start();
        /*
        * runnable这个接口被MyThread2实现后，类似于多态
        * 需要runnable类的地方可以用MyThread2的实例去代替。
        *
        * */
    }

}
