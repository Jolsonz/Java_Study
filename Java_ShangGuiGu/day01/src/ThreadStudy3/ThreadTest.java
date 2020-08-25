package ThreadStudy3;

/**
 * @author Jolson
 * @Create 2020-08-14 14:38
 * 演示线程的死锁问题
 *
 *  注意这里用了匿名类实现，加深对匿名类的理解
 *  注意这个代码不加sleep也可能死锁，只是概率小很多。
 *
 *
 * 1.死锁的理解：不同的线程分别占用对方需要的同步资源不放弃，
 * 都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 *
 * 2.说明：
 * 1）出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 * 2）我们使用同步时，要避免出现死锁。
 *
 */
public class ThreadTest {

    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
/*
 * 正常来写应该是写
 * class test extends Thread{
 *   @override
 *   public void run(){
 *       XXX;
 *   }
 * }
 * Thread m = new test();//这是多态
 * m.start();//由于动态绑定，这里实际上是调用了test的run方法。
 * 这样写的。
 *
 * */
        //匿名实现类的匿名对象
        new Thread(){//里面是匿名实现，注意，由于有override，所以实际上这个类继承了Thread，是Thread的子类。
            //这里实际上用到了多态
            @Override
            public void run() {

                synchronized (s1){

                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }


                }

            }
        }.start();//匿名实现类的匿名对象调用start(),由于多态，调的是其中重写的的start()
/*
* 正常来写应该是写
* class test implement Runnable{
*   @override
*   public void run(){
*       XXX;
*   }
* }
* test t = new test();
* thread m = new thread(t);
* m.start();
* 这样写的。
*
* */
//        因为用实现runnable接口的方式是要把其实现类做参数输入Thread类,所以和上面的稍有不同。
        new Thread(new Runnable() {//实现runnable接口,这里面也是匿名实现类
            @Override
            public void run() {
                synchronized (s2){

                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }


                }



            }
        }).start();


    }


}
