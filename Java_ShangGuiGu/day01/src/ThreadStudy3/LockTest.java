package ThreadStudy3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁  --- JDK5.0新增
 *
 * 1. 面试题：synchronized 与 Lock的异同？
 *   相同：二者都可以解决线程安全问题
 *   不同：synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
 *        Lock需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
 *
 * 2.优先使用顺序：
 *  Lock  同步代码块（已经进入了方法体，分配了相应资源）  同步方法（在方法体之外）
 *
 *  注意，这里是实现的runnable类，如果用继承Thread类的话，记得用private static ReentrantLock lock来声明。
 *
 *  面试题：如何解决线程安全问题？有几种方式
 * @author Jolson
 * @Create 2020-08-15 11:26
 */
class Window implements Runnable {
    private int ticket = 100;
    //1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock(true);//如果用继承Thread类，需加static

    @Override
    public void run() {
        while (true) {
            try {
                //2.调用锁定方法lock()
                lock.lock();//就相当于下面就是同步代码块了。

                if (ticket > 0) {
                    try {//写try增大错误概论
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":售票，票号为：" + ticket);
                    ticket--;
                } else break;
            }finally {
                //3.调用解锁方法：unlock()
                lock.unlock();
                /*如果不解锁，就一直是第一个抢到锁的进程在用。
                * 为何用try catch，是因为可能在if中break了，还是需要unlock。
                * */
            }
        }
    }
}
public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w,"窗口1");
        Thread t2 = new Thread(w,"窗口2");
        Thread t3 = new Thread(w,"窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}

