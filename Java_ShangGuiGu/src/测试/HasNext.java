package 测试;

/**
 * @author Jolson
 * @Create 2020-08-08 11:55
 */
import java.util.*;
public class HasNext
{
    public static void main(String[] args)
    {
        System.out.println("请输入若干单词，以空格作为分隔");
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNext("#"))
        //while(sc.hasNext)这样就永不会停
        {
            System.out.println("键盘输入的内容是："+ sc.next());
        }
        System.out.println("执行吗");
    }
}