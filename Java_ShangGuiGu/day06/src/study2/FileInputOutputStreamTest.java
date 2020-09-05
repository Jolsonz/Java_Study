package study2;

import org.junit.Test;

import java.io.*;

/**
 * 测试FileInputStream和FileOutputStream的使用
 *
 * 结论：
 * 1. 对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
 * 2. 对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 *
 * @author Jolson
 * @Create 2020-09-03 15:54
 */
public class FileInputOutputStreamTest {

    /**
     没问题，一样能输出。因为hello.txt里是英文，用的UFT-8，一个字节能表示一个字符
     但用中文就一个字节存不下了，UTF-8里的中文用3个字节存的。
     在hello.txt里加中文，发现这里会输出部分乱码。
     “helloworld123中国人”，每次读5个字节，第三次读了123，以及中的前两个字节，所以中字是乱码。
     */
    @Test
    public void testFileInputStream() throws IOException {
        //1.造文件
        File file = new File("hello.txt");

        //2.造流
        FileInputStream fis = new FileInputStream(file);

        //3.读数据
        byte[] b = new byte[5];
        int len;
        while ((len = fis.read(b))!=-1){
            String str = new String(b,0,len);
            System.out.print(str);
        }
        //4.关闭资源
        fis.close();
    }

    /**
     * 同理，这里调Read，每次读一个字符，那更是所有中文都是乱码。
     * @throws IOException
     */
    @Test
    public void testFileInputStream1() throws IOException {
        //1.造文件
        File file = new File("hello.txt");

        //2.造流
        FileInputStream fis = new FileInputStream(file);

        //3.读数据
        int data;
        while ((data = fis.read())!=-1){
            System.out.print((char)data);
        }
        //4.关闭资源
        fis.close();
    }

/*
实现对图片的复制操作
 */
    @Test
    public void testFileInputOutputStream(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //1.造文件
            File srcFile = new File("测试图.jpg");
            File destFile = new File("测试图1.jpg");

            //2.造流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //3.读数据
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        //4.关闭资源,并列的处理也行
            if(fos!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
    方法：指定路径下文件的复制
     *
     */
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //1.造文件
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //2.造流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //3.读数据
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源,并列的处理也行
            if(fos!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testCopyFile(){
        long start = System.currentTimeMillis();

        String src = "D:\\迅雷下载\\赤脚医生手册-湖南版.pdf";
        String dest = "D:\\迅雷下载\\memme.pdf";
        //花费253ms
        /*
        * 这时候字节流又能正确复制中文
        * 因为之前字节流复制文本的时候，它把字节拆开了放在string里然后打印出来了，所以会乱码。相当于你要去看它，所以它拆开之后是乱码。
        * 这里不读，只是复制，反而可以。
        * */
//        String src = "hello.txt";
//        String dest = "hello3.txt";

        copyFile(src,dest);

        long end = System.currentTimeMillis();

        System.out.println("复制花费的时间为 " + (end - start));
    }

}
