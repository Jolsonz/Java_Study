package project2;

import java.util.Scanner;

/**
 * @author Jolson
 * @Create 2020-08-08 21:54
 */
public class CMUtility {
    private static Scanner input = new Scanner(System.in);//重定向
    /**
     从键盘读取一个字符，并将其作为方法的返回值。
     */
    public static char readChar(){
        String str = readKeyBoard(1,false);
        return str.charAt(0);
    }
    /**
     从键盘读取一个字符，并将其作为方法的返回值。
     */
    public static char readChar(char defaultValue){
        String str = readKeyBoard(1,true);
        return (str.length()==0)? defaultValue:str.charAt(0);
    }

    /**
     * 用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
     */
    public static char readConfirmSelection(){
        char c;
        while (true){
            String str = readKeyBoard(1,false).toUpperCase();//转大写
            c=str.charAt(0);
            if(c=='Y' || c=='N'){
                return c;
            }else System.out.println("请重新输入");
        }
    }
    /**
     从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值。
     */
    public static int readInt(){
        int t;
        while (true) {
            String str = readKeyBoard(2,false);
            try {
                t = Integer.parseInt(str);
                break;//如果能执行到这儿说明上面没报错
            } catch (RuntimeException e) {//NumberFormatException比较好
                System.out.println("请输入一个长度不超过2位的整数");
            }
        }
        return t;
    }
    /**
     从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值。
     如果用户不输入字符而直接回车，方法将以defaultValue 作为返回值。
     */
    public static int readInt(int defaultValue){
        int t;
        while (true) {//反复读
            String str = readKeyBoard(2,true);//允许为空了。
            if(str.length() == 0) return defaultValue;
            try {
                t = Integer.parseInt(str);
                break;//如果能执行到这儿说明上面没报错
            } catch (RuntimeException e) {//NumberFormatException比较好
                System.out.println("请输入一个长度不超过2位的整数");
            }
        }
        return t;
    }

    /**
     * 几个方法都调用它，用于读键盘，
     * @param limit
     * @param blankReturn
     * @return line
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";
        while (input.hasNextLine()){//这个应该用hasNext()也没差别
            line = input.nextLine();
            if(line.length() == 0){
                if(blankReturn) return "";
                else continue;//这句删了会如何？
            }
            if(line.length()<1 || line.length()>limit){
                System.out.println("输入长度（不大于" + limit + "）错误，请重新输入：");
            }else break;
        }
        return line;
    }
    /**
     用于界面菜单的选择。该方法读取键盘，如果用户键入’1’-’5’中的任意字符，则方法返回。返回值为用户键入字符。
     */
    public static char readMenuSelection() {
        char c;
        while (true){
            String str = readKeyBoard(1,false);
            c = str.charAt(0);//string转char
            if( c<'1'||c>'5'){
                System.out.println("输入错误，请重新输入");
            }else return c;//都不用break，直接return也能终结循环
        }
    }
    /**
     从键盘读取一个长度不超过limit的字符串，并将其作为方法的返回值。
     如果用户不输入字符而直接回车，方法将以defaultValue 作为返回值。
     */
    public static String readString(int limit, String defaultValue) {
        String str = readKeyBoard(limit, true);//可以为空
        return str.length()==0? defaultValue : str;//这里要不要加括号啊。
    }
    /**
     从键盘读取一个长度不超过limit的字符串，并将其作为方法的返回值。
     */
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }
}
