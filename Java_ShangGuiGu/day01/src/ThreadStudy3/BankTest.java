package ThreadStudy3;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 * @author Jolson
 * @Create 2020-08-14 10:45
 */
public class BankTest {
}
class Bank{//单例的，只能创建一个当前类实例
    private Bank(){}

    private static Bank instance = null;//懒汉式构造

    public static Bank getInstance(){
        //方式一：效率稍差
//        这个方式会每次都抢同步代码块，效率低。没必要每次抢，只有instance没被new时才需要抢，已经被new了就直接返回就行了。
//        synchronized (Bank.class) {
//            if(instance == null){
//
//                instance = new Bank();
//            }
//            return instance;
//        }
        //方式二：效率更高
        if(instance == null){
            synchronized (Bank.class) {
                if(instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}


