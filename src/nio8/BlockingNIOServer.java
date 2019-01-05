package nio8;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author jsjchai.
 */
public class BlockingNIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel channel = ServerSocketChannel.open();

        channel.bind(new InetSocketAddress(9999));

        SocketChannel socketChannel = channel.accept();

        FileChannel fileChannel = FileChannel.open(Paths.get("bak.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        ByteBuffer buffer = ByteBuffer.allocate(2048);

       while(socketChannel.read(buffer) != -1){
           buffer.flip();
           fileChannel.write(buffer);
           buffer.clear();
       }

        System.out.println("receive file success");



        buffer.put("receive files success".getBytes());
        buffer.flip();
        socketChannel.write(buffer);


        fileChannel.close();
        socketChannel.close();
        channel.close();

    }


}
