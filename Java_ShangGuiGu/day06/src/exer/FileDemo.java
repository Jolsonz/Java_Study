package exer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Jolson
 * @Create 2020-08-31 11:49
 */
public class FileDemo {
    @Test
    public void test() throws IOException {
        File f = new File("hello.txt");
        File destFile = new File(f.getParent(),"haha.txt");
        boolean newFile = destFile.createNewFile();
        if(newFile) System.out.println("创建成功");

    }
}
