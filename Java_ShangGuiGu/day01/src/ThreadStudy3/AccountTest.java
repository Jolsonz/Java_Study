package ThreadStudy3;

/**
 * 银行有一个账户。
 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。

 分析：
 1.是否是多线程问题？ 是，两个储户线程
 2.是否有共享数据？ 有，账户（或账户余额）
 3.是否有线程安全问题？有
 4.需要考虑如何解决线程安全问题？同步机制：有三种方式。

 * @author Jolson
 * @Create 2020-08-15 13:45
 */
class Account{
    private double balance;
    public Account(double balance){
        this.balance= balance;
    }
//    存钱
    public synchronized void deposit(double money){//这个改成同步方法即可，很简单。这里的this是account的对象
        if(money>0){
/*
* 这里balance是共享数据，整个sleep慢一点,容易表现线程安全问题
* 注意甲进来之后，睡着了，然后乙也进来了，他俩一起存了1000，就表现为2000了
* */
            balance+=money;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "存钱成功，余额为" + balance);
        }
    }

}

class Customer extends Thread{
    private Account1 a;

    public Customer(Account1 a){
        this.a=a;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            a.deposit(1000);//每次存1000
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account1 a =new Account1(0);
//        Customer t = new Customer(a);
//        Thread c1 = new Thread(t,"甲");
//        Thread c2 = new Thread(t,"乙");
//        此时两个线程是共用的同一个账户

        Customer c1 = new Customer(a);
        Customer c2 = new Customer(a);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
