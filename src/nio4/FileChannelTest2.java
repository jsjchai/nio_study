package nio4;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author jsjchai.
 */
public class FileChannelTest2 {

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
        FileChannel out = FileChannel.open(Paths.get("3.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inBuf = in.map(FileChannel.MapMode.READ_ONLY,0,in.size());
        MappedByteBuffer outBuf = out.map(FileChannel.MapMode.READ_WRITE,0,in.size());

        byte[] dst = new byte[inBuf.limit()];
        inBuf.get(dst);
        outBuf.put(dst);

        out.close();
        in.close();

    }
}
