package nio7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author jsjchai.
 */
public class BlockingNIOClient {


    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));

        ByteBuffer data = ByteBuffer.allocate(16);
        data.put("hello,server".getBytes());
        data.flip();
        channel.write(data);

        channel.close();
    }
}
