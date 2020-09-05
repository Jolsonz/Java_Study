package study2;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的使用
 *
 * 1.缓冲流：
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 *
 * 2.作用：提供流的读取、写入的速度
 *   提高读写速度的原因：内部提供了一个缓冲区。8192，8kb大小
 *
 * 3. 处理流，就是“套接”在已有的流的基础上。
 *
 * @author Jolson
 * @Create 2020-09-05 11:46
 */
public class BufferedTest {
    /*
    实现非文本文件的复制
     */
    @Test
    public void BufferedStreamTest() throws FileNotFoundException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
            File srcFile = new File("测试图.jpg");
            File destFile = new File("测试图2.jpg");


            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造缓冲流，因为缓冲流（又可以说是处理流）是包在节点流外面的。
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节，过程：读取、写入
            byte[] buffer = new byte[10];//还是字节流
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);//用处理流read

//                bos.flush();//刷新缓冲区
                /*
                flush()
                功能：将缓冲区的内容输出，并清空

                一般主要用在IO中，即清空缓冲区数据，就是说你用读写流的时候
                其实数据是先被读到了内存中，然后用数据写到文件中，
                当你数据读完的时候不代表你的数据已经写完了，因为还有一部分有可能会留在内存这个缓冲区中。
                这时候如果你调用了 close()方法关闭了读写流，那么这部分数据就会丢失，
                所以应该在关闭读写流之前先flush()。

                实际上在write的过程中会自动调flush，
                */

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源关闭
            //要求：先关闭外层的流，再关闭内层的流
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略.
//        fos.close();
//        fis.close();
        }
    }


    //实现文件复制的方法
    public void copyFileWithBuffered(String srcPath,String destPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);
            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源关闭
            //要求：先关闭外层的流，再关闭内层的流
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略.
//        fos.close();
//        fis.close();
        }
    }

    @Test
    public void testCopyFile(){
        long start = System.currentTimeMillis();

        String src = "D:\\迅雷下载\\赤脚医生手册-湖南版.pdf";
        String dest = "D:\\迅雷下载\\memme.pdf";
        //花费35ms。用字节流是253ms。缓冲流是35ms。
        /*
         * 这时候字节流又能正确复制中文
         * 因为之前字节流复制文本的时候，它把字节拆开了放在string里然后打印出来了，所以会乱码。相当于你要去看它，所以它拆开之后是乱码。
         * 这里不读，只是复制，反而可以。
         * */

        copyFileWithBuffered(src,dest);

        long end = System.currentTimeMillis();

        System.out.println("复制花费的时间为 " + (end - start));
    }

    /*
        使用BufferedReader和BufferedWriter实现文本文件的复制
        会比用节点流快。
         */
    @Test
    public void test(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //1. 创建文件和相应的流，注意这里都以匿名的方式写。
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

            //2. 读写操作,注意是字符流，用char数组
            //方式一：使用char[]数组
//            char[] cbuf = new char[1024];
//            int len;
//            while((len = br.read(cbuf)) != -1){
//                bw.write(cbuf,0,len);//自动flush()
//            }

            //方式二：使用String
            String data;
            while((data = br.readLine()) != null){
                //方法一：
//                bw.write(data + "\n");//data中不包含换行符
//                注意/n这个换行符和源文件的换行不太一样，一个是CRLF，一个是LF，所以大小上有差别。
                //方法二：大小上无差别
                bw.write(data);//data中不包含换行符
                bw.newLine();//提供换行的操作

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源，只需关闭缓冲流，它会自动关闭内层的字符流
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
