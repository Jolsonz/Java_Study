package ThreadStudy1;

/**
 * Thread类中的常用的方法:
 *
 *1. start():启动当前线程；调用当前线程的run()
 *2. run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 *3. currentThread():静态方法，返回执行当前代码的线程
 *4. getName():获取当前线程的名字
 *5. setName():设置当前线程的名字
 *6. yield():释放当前cpu的执行权
 *7. join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态。
 * * 8. stop():已过时。调不了的。当执行此方法时，强制结束当前线程。
 * * 9. sleep(long millitime):让当前线程“睡眠”指定的millitime毫秒。在指定的millitime毫秒时间内，当前线程是阻塞状态。
 * * 10. isAlive():判断当前线程是否存活

 * 线程的优先级：
 * 1.
 * MAX_PRIORITY：10
 * MIN _PRIORITY：1
 * NORM_PRIORITY：5  -->默认优先级
 * 2.如何获取和设置当前线程的优先级：
 *   getPriority():获取线程的优先级
 *   setPriority(int p):设置线程的优先级
 *
 *   说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下被执行。并不意味着只当高优先级的线程执行完以后，低优先级的线程才执行。


 线程通信：wait() / notify() / notifyAll() :此三个方法定义在Object类中的。

 * @author Jolson
 * @Create 2020-08-11 13:21
 */
class Thread3 extends Thread{
    @Override
    public void run() {
//        Thread.currentThread().setName("hello");//这样做无论那个实例，只要属于这个类，就是这个名字了，不太好。
        for (int i = 0; i < 100; i++) {
            if(i%2==0){
//                try {
//                    sleep(100);//这也是一种阻塞
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(getName() +":"+ i + " 优先级：" +getPriority());//在线程内可以不写Thread.currentThread()
            }
            if(i%20 == 0){
                this.yield();
                //就是遇到0,20,40之后就释放CPU执行权，但也不一定，说不定下一刻又分配给这个线程了。
            }

            /*
            * this在这里指当前类的对象。
            * 相当于h1,h3
            * 相当于Thread.currentThread()
            * 省略掉this貌似不行,奇怪*/
        }
    }
    public Thread3(String name){
        super(name);//Thread里有这个构造器可以用于设置线程名
    }

    public Thread3() {
    }
}
public class ThreadMethodTest {
    public static void main(String[] args) {
        Thread3 t1 = new Thread3("线程X");
        t1.setName("线程一");
        t1.setPriority(10);//高优先级
        t1.start();


        Thread.currentThread().setName("主线程");
        for (int i = 0; i < 100 ; i++) {
            if(i%2==0)
                System.out.println(Thread.currentThread().getName() +":"+ i + " 优先级：" + Thread.currentThread().getPriority());
//            if(i==20) {
//                try {
//                    t1.join();//主线程到20的时候，被阻塞，线程1参与进来，执行完之后再轮到主线程,这东西优先级很高啊。
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        System.out.println(t1.isAlive());//t1这个线程肯定不存活了
    }
}
