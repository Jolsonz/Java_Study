package study3;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 *
 * 4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果，这是它的独特操作。可以用在断点续传和多线程下载。
 *
 * 有点像C++里的流输入输出。
 * 没有转换流，缓冲流重要
 *
 * @author Jolson
 * @Create 2020-09-10 15:19
 */
public class RandomAccessFileTest {

    @Test
    public void test1() {
        RandomAccessFile r1 = null;
        RandomAccessFile r2 = null;
        try {
            r1 = new RandomAccessFile(new File("测试图.jpg"),"r");
            r2 = new RandomAccessFile(new File("测试图3.jpg"),"rw");
            //这是个字节流
            byte[] buffer = new byte[1024];
            int len;
            while ((len = r1.read(buffer))!= -1){
                r2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(r1!=null){
                try {
                    r1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(r2!=null){
                try {
                    r2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置
        raf1.write("xyz".getBytes());//getBytes可能是吧字符转成字节，因为这个是字节流，直接插字符可能不行。
        //如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）。这里从位置3开始覆盖。

        raf1.close();

    }
    /*
    使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void test3() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置

        //保存指针3后面的所有数据到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());//length返回的是long

        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer,0,len)) ;
        }

        //调回指针，写入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());

        //将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();

        //思考：将StringBuilder替换为ByteArrayOutputStream
    }
}

