package nio2;

import java.nio.ByteBuffer;

/**
 * @author jsjchai.
 */
public class NIOTest {

    public static void main(String[] args) {

        ByteBuffer buf = ByteBuffer.allocate(1024);

        String str = "hello,nio2";

        buf.put(str.getBytes());

        buf.flip();

        byte[] data = new byte[buf.limit()];
        buf.get(data,0,2);
        System.out.printf("capacity:%d,limit:%d,position:%d,data:%s\n",buf.capacity(),buf.limit(),buf.position(),new String(data));
    }
}
