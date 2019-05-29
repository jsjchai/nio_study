package nio10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author jsjchai.
 */
public class Receive {

    public static void main(String[] args) throws IOException {

        DatagramChannel channel = DatagramChannel.open();

        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buf = ByteBuffer.allocate(32);
        channel.receive(buf);
        buf.clear();

        System.out.println(new String(buf.array()));

        channel.close();

    }
}
