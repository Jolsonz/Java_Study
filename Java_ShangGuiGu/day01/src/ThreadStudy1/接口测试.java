package ThreadStudy1;

/**
 * @author Jolson
 * @Create 2020-08-11 22:15
 */
public class 接口测试 {
        public static void main(String[] args) {
            A a=new B();//实际类型为b，但a为b的父类，可以用a来表示。
            a.fuck();
        }
}
interface A{
    void fuck();
}


class B implements A{
    public void fuck() {
            System.out.println("A fuck");
    }
}

