package nio5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散读取：将通道中数据分散到多个缓存区
 * 聚集写入：将多个缓存区写入到通道
 * @author jsjchai.
 */
public class NIOTest {


    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("1.txt","rw");

        FileChannel channel = file.getChannel();

        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(200);

        ByteBuffer[] bufArr = {buf1,buf2};
        channel.read(bufArr);

        for (ByteBuffer arr : bufArr ) {
            arr.flip();
        }

        System.out.println(new String(buf1.array()));
        System.out.println("==============================");
        System.out.println(new String(buf2.array()));


        RandomAccessFile newFile = new RandomAccessFile("6.txt","rw");
        newFile.getChannel().write(bufArr);

        newFile.getChannel().close();
        newFile.close();
        channel.close();
        file.close();



    }
}
