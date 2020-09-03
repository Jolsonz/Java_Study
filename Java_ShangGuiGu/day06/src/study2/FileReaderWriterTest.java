package study2;

import org.junit.Test;

import java.io.*;

/**
 *
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）                               缓冲流（处理流的一种,作用在已有节点流之上的）
 * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
 * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
 * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
 *
 * @author Jolson
 * @Create 2020-09-02 16:29
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");//相较于当前工程。
        System.out.println(file.getAbsolutePath());

        File file1 = new File("day06\\hello.txt");//相对路径，day09下面的了
        System.out.println(file1.getAbsolutePath());
    }

    /**
将day06下的hello.txt文件内容读入程序中，并输出到控制台

说明点：
1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
3. 读入的文件一定要存在，否则就会报FileNotFoundException。

     注意这里不建议直接public void testFileReader() throws IOException
     有可能文件打开之后没关闭。流没有关闭。用try-catch-finally

 */
    @Test
    public void testFileReader(){
        FileReader fr = null;//这里要先定义，不然finally那里是访问不到try里的数据的。

        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");//相对路径，相较于当前模块(module)
//            File file = new File("hello1.txt");//相对路径，相较于当前模块(module)
            //System.out.println(file.getAbsolutePath());//获得文件的绝对路径

            //2.提供具体的流
            fr = new FileReader(file);

            //3.数据的读入
            //read():返回读入的一个字符。如果达到文件末尾，返回-1

/*            这么写不够好
            int data = fr.read();
            while (data != -1){
                System.out.print((char)data);//这只是读了一个，还得读下一个
                data = fr.read();//读下一个。
            }*/
            int data;
            while((data = fr.read())!=-1){
                System.out.print((char) data);
            }

        } catch (FileNotFoundException e){//这个在IO exception里面，所以要写在它的前面。
            System.out.println("没有这个文件");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作，需要手动关闭资源。
            try {
                if(fr != null)//因为可能对象fr都没造好就抛异常到这儿了，那肯定不用close了。
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    /**
        对read()操作升级：使用read的重载方法，前两步和之前一样，第三步不一样。



        如果涉及读大量数据。用read()每次读一个效率低，每次都和硬盘做交互，慢。
        一次性读一批是效率更好的。

        read(char[] cbuf)  返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1
        注意这个方法的一些特点。

     *  之后涉及流的读入操作基本就这四步
     *  1.File类的实例化
     *  2.FileReader流的实例化
     *  3.读入的操作
     *  4.资源的关闭
     *  后续就是2,3会不一样。
     */
    @Test
    public void testFileReader1()
    {
        FileReader fr = null;
        try {
            //1.File类的实例化
            File file = new File("hello.txt");

            //2.FileReader流的实例化
            fr = new FileReader(file);

            //3.读入的操作
            //read(char[] cbuf):返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1
            char[] cbuf = new char[5];//每次读5个。
            int len;
            while((len = fr.read(cbuf)) != -1){
                //方式一：遍历
                //错误的写法
//                for(int i = 0;i < cbuf.length;i++){//可能越界
//                    System.out.print(cbuf[i]);
//                }
//                结果helloworld123ld，因为上次数组的是world,读入三个123只替代了前三个。

                //正确的写法
//                for(int i = 0;i < len;i++){
//                    System.out.print(cbuf[i]);
//                }

                //方式二：
                //错误的写法,对应着方式一的错误的写法
//                String str = new String(cbuf);//也是整体把整个数组全取出来了。
//                System.out.print(str);

                //正确的写法
                String str = new String(cbuf,0,len);//只取0~len
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr != null){
                //4.资源的关闭
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    /*
    从内存中写出数据到硬盘的文件里。
    一样的4步操作。

    说明：
    1. 输出操作，对应的File可以不存在的。并不会报异常
    2.
         File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
         File对应的硬盘中的文件如果存在：
                如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖,默认是覆盖的。
                如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容

     */
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("hello1.txt");

            //2.提供FileWriter的对象，用于数据的写出，这里文件如果不存在，在输出的过程中，会自动创建此文件。文件如果存在，自动覆盖。除非加参数true
            fw = new FileWriter(file);

            //3.写出的操作
            fw.write("I have a dreeeeeeeeeeeeeeeeeam!\n");
            fw.write("you need to have a dream!\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fw != null){
                try {
                    //4.流资源的关闭
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Test
    public void testFileReaderFileWriter(){
        FileReader fr = null;
        FileWriter fw = null;

        try {
            //1.创建File类的对象
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");
            //2.创建流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            //3.数据的读入和写出
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf))!=-1){
                fw.write(cbuf,0,len);//每次从0位置开始，写入Len个字符
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭，这样并列着写没有问题。因为try-catch是能处理掉异常的。
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
