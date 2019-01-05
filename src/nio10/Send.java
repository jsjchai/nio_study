package nio10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author jsjchai.
 */
public class Send {

    public static void main(String[] args) throws IOException {

        DatagramChannel channel = DatagramChannel.open();

        String newData = "hello,udp";

        ByteBuffer buf = ByteBuffer.allocate(32);

        buf.clear();

        buf.put(newData.getBytes());

        buf.flip();

        int bytesSent = channel.send(buf, new InetSocketAddress("127.0.0.1", 9999));

        System.out.println(bytesSent);

        channel.close();



    }

}
