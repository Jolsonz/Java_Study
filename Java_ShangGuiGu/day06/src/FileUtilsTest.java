import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 这是测试导入的第三方jar包的。
 * 直接在该module下创建一个目录，一般命名为lib，或libs
 * 将jar包直接粘贴或移入该目录，右键，add as a library即可
 *
 * @author Jolson
 * @Create 2020-09-11 15:23
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        File scrFile = new File("day06\\测试图.jpg");
        File destFile = new File("day06\\测试图4.jpg");
        try {
            FileUtils.copyFile(scrFile,destFile);//这里很方便的就能复制
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
