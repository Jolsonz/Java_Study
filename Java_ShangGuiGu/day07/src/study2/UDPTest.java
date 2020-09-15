package study2;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * 仅做了解
 * 注意到这里先启动发送端是不会报错的，而TCP先启动发送端会报错，因为UDP不管你收不收到的，只管发
 * @author Jolson
 * @Create 2020-09-14 21:36
 */
public class UDPTest {
    //发送端
    @Test
    public void send() throws IOException {
        DatagramSocket socket = new DatagramSocket();//不指名端口号，表明本机。
        //这时数据不是放在socket里了，而是放在数据报中。

        String str = "我是UDP方式发送的信息";
        byte[] data = str.getBytes();
        InetAddress i = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,i,9090);//此时数据都要放在这里面，这里指定的是接收方的端口号

        socket.send(packet);

        socket.close();
    }

    //接收端
    @Test
    public void receive() throws IOException{
        DatagramSocket socket = new DatagramSocket(9090);//这里要指明端口号了

        byte[] buffer = new byte[100];

        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);//这里是指定自己，没有必要指定端口号了

        socket.receive(packet);
        //本质上现在数据在buffer里的。

        System.out.println(new String(packet.getData(),0, packet.getLength()));

        socket.close();
    }

}
