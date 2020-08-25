package ThreadStudy1;

/**
 * 练习：创建两个线程，一个遍历100以内的偶数，一个遍历100以内的奇数
 * @author Jolson
 * @Create 2020-08-11 10:54
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        Thread1 t1 = new Thread1();
//        t1.start();
//        Thread2 t2 = new Thread2();
//        t2.start();


//  可以选择创建Thread1的匿名子类的方式，因为用了一次就不用了
        new Thread(){
            @Override
            public void run() {
                for (int i = 0;i<=100;i+=2){//偶数
                    System.out.println(Thread.currentThread().getName()+" "+i);
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int j = 1; j <100; j+=2){//奇数
                    System.out.println(Thread.currentThread().getName()+" "+ j);
                }
            }
        }.start();
    }
}
class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0;i<=100;i+=2){//偶数
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}


class Thread2 extends Thread{
    @Override
    public void run() {
        for (int j = 1; j <100; j+=2){//奇数
            System.out.println(Thread.currentThread().getName()+" "+ j);
        }
    }
}