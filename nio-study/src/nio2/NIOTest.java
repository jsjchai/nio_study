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
        buf.get(data, 0, 2);
        System.out.printf("capacity:%d,limit:%d,position:%d,data:%s\n", buf.capacity(), buf.limit(), buf.position(), new String(data));

        //标记
        buf.mark();
        buf.get(data, 2, 3);
        System.out.printf("capacity:%d,limit:%d,position:%d,data:%s\n", buf.capacity(), buf.limit(), buf.position(), new String(data));

        //恢复到mark标记的位置
        buf.reset();
        System.out.printf("capacity:%d,limit:%d,position:%d,data:%s\n", buf.capacity(), buf.limit(), buf.position(), new String(data));

        //是否还有剩余多少数据
        if (buf.hasRemaining()) {
            System.out.printf("capacity:%d,limit:%d,position:%d,remain:%d\n", buf.capacity(), buf.limit(), buf.position(), buf.remaining());
        }
    }
}
