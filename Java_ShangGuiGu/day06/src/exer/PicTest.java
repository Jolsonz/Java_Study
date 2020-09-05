package exer;

import org.junit.Test;

import java.io.*;

/**
 * @author Jolson
 * @Create 2020-09-05 16:53
 */
public class PicTest {

    //图片的加密
    @Test
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File("测试图.jpg"));
            fos = new FileOutputStream(new File("测试图secret.jpg"));

            byte[] buffer = new byte[10];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                //字节数组进行修改

                //错误的，这显然是形参被赋值了，实际值没有被改。
    //            for(byte b : buffer){
    //                b = (byte) (b ^ 5);
    //            }

                //正确的
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }

                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //图片的解密，异或同样一个数即可。
    @Test
    public void test2(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File("测试图secret.jpg"));
            fos = new FileOutputStream(new File("测试图correct.jpg"));

            byte[] buffer = new byte[1024];//这个里面可以改的
            int len;
            while ((len = fis.read(buffer)) != -1) {

                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }

                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
