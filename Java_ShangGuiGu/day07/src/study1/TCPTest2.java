package study1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题2：客户端发送文件给服务端，服务端将文件保存在本地。
 * 这里其实也应该用try-catch-finally
 * @author Jolson
 * @Create 2020-09-14 12:31
 */
public class TCPTest2 {
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

        fis.close();
        os.close();
        socket.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);

        Socket socket = ss.accept();//接受客户端传来的socket
        InputStream is = socket.getInputStream();//获得socket中的输入流。
        FileOutputStream fos = new FileOutputStream(new File("IMG1.JPG"));
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        //从下往上资源关闭
        fos.close();
        is.close();
        socket.close();
        ss.close();


    }


}
