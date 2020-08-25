package exer;

/**
 * 一道面试题
 * @author Jolson
 * @Create 2020-08-18 11:57
 */
public class StringTest {

    String str = new String("good");
    char[] ch = { 't', 'e', 's', 't' };

    /*
    这里str拿到了外面str的指针，但内部的这个str和外部的不一样，它是另一个局部变量了。
    在内部改变str的指向，让它指向"test ok",但外部的str是不会变的。
    */
    public void change(String str, char ch[]) {
        str = "test ok";//string是不可变的，故重新指向了一个字符串变量
        ch[0] = 'b';//数组不是不可变的，故可以改。
    }
    public static void main(String[] args) {
        StringTest ex = new StringTest();//非静态，故先造对象。
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best
    }
}

