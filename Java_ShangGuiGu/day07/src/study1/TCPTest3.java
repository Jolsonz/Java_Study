package study1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。
 * 并关闭相应的连接。
 *
 *  其实它这里为什么不加socket.shutdownOutput()会死锁，视频并没有讲的很明白，之后再去了解吧。
 *
 * @author Jolson
 * @Create 2020-09-14 17:16
 */
public class TCPTest3 {
    @Test
    public void client() throws IOException {
        //1. 造socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);

        //2. 获得一个输出流
        OutputStream os = socket.getOutputStream();

        //3. 文件输入流
        FileInputStream fis = new FileInputStream(new File("IMG.JPG"));

        //4. 从文件读入并输出到socket
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer))!=-1){
            os.write(buffer,0,len);
        }

        socket.shutdownOutput();//明确指示终止。

        //接受来自服务器端的数据并显示到控制台上
        InputStream is = socket.getInputStream();
        //考虑为了不出现乱码，使用ByteArrayOutputStream
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        byte[] buffer1 = new byte[20];
        while ((len = is.read(buffer1))!= -1){
            b.write(buffer1,0,len);
        }
        System.out.println(b.toString());

        b.close();
        fis.close();
        os.close();
        socket.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);

        Socket socket = ss.accept();//接受客户端传来的socket
        InputStream is = socket.getInputStream();//获得socket中的输入流。
        FileOutputStream fos = new FileOutputStream(new File("IMG2.JPG"));
        byte[] buffer = new byte[1024];
        int len;
        /*read是一个阻塞式的方法，如果不指明的话，是不会退出循环的。
         因为没有明确的结束标识，它会一直等客户端发数据，但是等不到。*/
        while((len = is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }


        System.out.println("图片传输完成");//不加socket.shutdownOutput()这句话是输出不了的，卡在这前面了。

        //在这里加个反馈
        OutputStream os = socket.getOutputStream();
        os.write("你好，照片我已收到".getBytes());


        //从下往上资源关闭
        os.close();
        fos.close();
        is.close();
        socket.close();
        ss.close();


    }

}
