package ThreadStudy4;

/**
 * 线程通信的应用：经典例题：生产者/消费者问题
 *
 * 生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员
 * 会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品
 * 了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *
 * 分析：
 * 1. 是否是多线程问题？是，生产者线程，消费者线程
 * 2. 是否有共享数据？是，店员（或产品）
 * 3. 如何解决线程的安全问题？同步机制,有三种方法
 * 4. 是否涉及线程的通信？是。要通知另一个线程。
 *
 * @author Jolson
 * @Create 2020-08-16 9:50
 */
class Clerk{
    private int productCount = 0;//产品数量
    public Clerk(){

    }

    public synchronized void produceProduct() {//同步监视器是Clerk的对象，只有一个
        if(productCount < 20 ){
            productCount++;
            System.out.println(Thread.currentThread().getName() + ":开始生产第" + productCount + "产品");
            notify();//如果产品不为0了，只要生产了就能通知消费了。
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void consumeProduct() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + ":开始消费第" + productCount + "产品");
            productCount--;
            //如果产品满20了，只要消费了就能通知生产。
            notify();
        }else {
            //产品没有了，要等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer extends Thread {//生产者
    private Clerk c;
    public Producer(Clerk c){
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始生产产品.....");
        while (true){//生产就完了

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            c.produceProduct();
        }
    }
}
class Customer extends Thread{
    private Clerk c;

    public Customer(Clerk c) {
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始消费产品.....");
        while (true){//消费就完了

            try {
                Thread.sleep(33);//这样消费比生产慢一些
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            c.consumeProduct();
        }
    }
}


public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        p1.setName("生产者1");
        Customer c1 = new Customer(clerk);
        Customer c2 = new Customer(clerk);
        Customer c3 = new Customer(clerk);
        c1.setName("消费者1");
        c2.setName("消费者2");
        c3.setName("消费者3");

        p1.start();
        c1.start();
        c2.start();
        c3.start();

    }

}
