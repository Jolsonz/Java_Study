package ThreadStady;

/**
 * 这里用接口的方式实现
 * 创建3个窗口买票，总票数为100张。
 * 存在线程安全问题待解决。
 *
 * @author Jolson
 * @Create 2020-08-12 9:57
 */
/*
* 开发中：优先选择：实现Runnable接口的方式
        * 原因：1. 实现的方式没类的单继承性的局限性
        *      2. 实现的方式更适合来处理多个线程共享数据的情况。
        *
        * 联系：public class Thread implements Runnable
* 相同点：两种方式都需要重写run(),将线程要执行的逻辑声明在run()中。
        目前两种方式，要想启动线程，都是调用的Thread类中的start()。

 */
class window implements Runnable {
    private int ticket = 100;//这里不需要static，因为我们只造了一个对象，三个构造器都用的一个window，所以总共是100张。

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);//加了这个之后容易出现错票，也是同步问题。有教大概论能卖出票号为0,-1的
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }//没加sleep也有可能出现-1这种错误。
                System.out.println(Thread.currentThread().getName() + ":买票，票号为" + ticket);
                ticket--;
            } else break;
        }
    }
}

public class WindowsTest1 {
    public static void main(String[] args) {
        window t = new window();
        Thread t1 = new Thread(t, "线程1");
        Thread t2 = new Thread(t, "线程2");
        Thread t3 = new Thread(t, "线程3");

        t1.start();
        t2.start();
        t3.start();
    }
}
