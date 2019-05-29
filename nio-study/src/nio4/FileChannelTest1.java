package nio4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jsjchai.
 */
public class FileChannelTest1 {



    public static void main(String[] args) {

        long st = System.currentTimeMillis();
        try {
            copy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - st);
    }

    private static void copy() throws IOException {
        FileInputStream in = new FileInputStream("1.txt");
        FileOutputStream out = new FileOutputStream("2.txt");

        FileChannel inFileChannel = in.getChannel();
        FileChannel outFileChannel = out.getChannel();


        ByteBuffer buf = ByteBuffer.allocate(1024);
        while(inFileChannel.read(buf) != -1){
            buf.flip();
            outFileChannel.write(buf);
            buf.clear();
        }

        inFileChannel.close();
        outFileChannel.close();

        in.close();
        out.close();
    }
}
