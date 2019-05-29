package nio7;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author jsjchai.
 */
public class BlockingNIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel channel = ServerSocketChannel.open();

        channel.bind(new InetSocketAddress(9999));

        SocketChannel socketChannel = channel.accept();

        ByteBuffer buffer = ByteBuffer.allocate(10);

        int len;
        while ((len = socketChannel.read(buffer)) != -1){
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));
            buffer.clear();
        }



        socketChannel.close();
        channel.close();

    }


}
