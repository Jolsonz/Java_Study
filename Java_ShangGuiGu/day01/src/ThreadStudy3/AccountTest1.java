package ThreadStudy3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 用继承runnable接口，和lock方法实现
 * 另外有个疑问，为何总是一个线程存3k，然后轮到另一个线程？做不到甲存1k，乙存1k轮流着来么，难道一个线程抢到锁了不会释放？
 * @author Jolson
 * @Create 2020-08-15 15:48
 */

class Account1 {
    private double balance;
    public Account1(double balance){
        this.balance= balance;
    }
    //    存钱
//    private ReentrantLock lock = new ReentrantLock();
    public synchronized void deposit(double money){//这个改成同步方法即可，很简单。这里的this是account的对象
//        try {
//            lock.lock();
            if (money > 0) {
                /*
                 * 这里balance是共享数据，整个sleep慢一点,容易表现线程安全问题
                 * 注意甲进来之后，睡着了，然后乙也进来了，他俩一起存了1000，就表现为2000了
                 * */
                balance += money;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "存钱成功，余额为" + balance);
            }
//        }finally {
//            lock.unlock();
//        }
    }

}

class Customer1 implements Runnable{
    private Account1 a;

    public Customer1(Account1 a){
        this.a=a;
    }
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 3; i++) {
                a.deposit(1000);//每次存1000
            }
        }finally {
            lock.unlock();
        }
    }
}

public class AccountTest1 {
    public static void main(String[] args) {
        Account1 a =new Account1(0);
        Customer1 t = new Customer1(a);
        Thread c1 = new Thread(t,"甲");
        Thread c2 = new Thread(t,"乙");
//        此时两个线程是共用的同一个账户

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
