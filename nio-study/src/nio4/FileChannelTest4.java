package nio4;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author jsjchai.
 */
public class FileChannelTest4 {

    public static void main(String[] args) {

        long st = System.currentTimeMillis();
        try {
            copy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - st);
    }

    private  static  void copy() throws IOException {
        FileChannel in = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Paths.get("5.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);

       out.transferFrom(in,0,in.size());


        out.close();
        in.close();

    }
}
