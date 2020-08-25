package ThreadStudy1;

/**
 * 这里用继承的方式实现
 * 创建3个窗口买票，总票数为100张。
 * @author Jolson
 * @Create 2020-08-11 20:55
 */
class Window extends Thread{
    private static int ticket = 100;//这里需要static,不然就是每个对象100张了
    //    这里还是有个错误，能卖出三个100号，存在线程的安全问题待解决
    @Override
    public void run() {
        while (true){
            if(ticket>0){
                System.out.println(getName() + ":买票，票号为" + ticket);
                ticket--;
            }else break;
        }
    }
    public Window(String name){
        super(name);//调用父类的构造器
    }
}

public class WindowsTest {
    public static void main(String[] args) {
        Window t1 = new Window("窗口1");
        Window t2 = new Window("窗口2");
        Window t3 = new Window("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
