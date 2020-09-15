package study1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 *
 *  先启动服务器端，服务器是被动的一端
 *
 * @author Jolson
 * @Create 2020-09-11 20:30
 */
public class TCPTest1 {
    //客户端
    @Test
    public void client(){
        Socket socket = null;//socket也是一个资源，需要关闭
        OutputStream os = null;
        try {
            //1. 创建socket的对象，指明服务器端的IP和端口号
            InetAddress i = InetAddress.getByName("127.0.0.1");
            socket = new Socket(i,8899);

            //2. 获取一个输出流，用于输出数据
            os = socket.getOutputStream();

            //3. 写出数据的操作
            os.write("你好，我是客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 资源的关闭
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //服务端
    @Test
    public void server() throws IOException{
        //1. 创建socket，不过是服务器端的，指明自己的端口号即可。服务端的IP就是该主机的IP,所以不用指明。
        ServerSocket ss = new ServerSocket(8899);//把端口设置成8899

        //2. 调用accept()，表明接受来自客户端的socket
        Socket socket = ss.accept();//拿到socket

        //获取一个输入流
        InputStream is = socket.getInputStream();

       /* //注意这里有中文，而且用的字节流，因为socket貌似只能拿到字节流。所以这里要开大一点，防止乱码。或者不用这个。
        byte[] buffer = new byte[20];
        int len;
        while ((len = is.read(buffer))!= -1){
            String temp = new String(buffer,0,len);
            System.out.print(temp);
        }*/

        //4. 读取输入流中的数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[5];
        int len;
        while ((len = is.read(buffer))!=-1){
            baos.write(buffer,0,len);//ByteArrayOutputStream类里面有个byte数组可以放东西，它会把数据每buffer个放进去一次。
        }
        System.out.println(baos.toString());
        System.out.println("收到了来自于"+socket.getInetAddress().getHostAddress());

        //5. 关闭资源
        baos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
