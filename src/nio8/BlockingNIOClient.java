package nio8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author jsjchai.
 */
public class BlockingNIOClient {


    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));

        FileChannel fileChannel = FileChannel.open(Paths.get("1.txt"),StandardOpenOption.READ);

        ByteBuffer data = ByteBuffer.allocate(1024);
        while(fileChannel.read(data) != -1){
            data.flip();
            channel.write(data);
            data.clear();
        }
        System.out.println("transfer files end");

        channel.shutdownOutput();

        //打印服务端保存文件结果
        int len;
        while ((len = channel.read(data)) != -1){
            data.flip();
            System.out.println(new String(data.array(),0,len));
            data.clear();
        }

        fileChannel.close();
        channel.close();

        channel.close();
    }
}
