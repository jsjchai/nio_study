package nio1;

import java.nio.ByteBuffer;

/**
 * @author jsjchai.
 */
public class NIOTest {

    public static void main(String[] args) {

        ByteBuffer buf = ByteBuffer.allocate(1024);

        String str = "hello,nio";

        System.out.printf("capacity:%d,limit:%d,position:%d\n",buf.capacity(),buf.limit(),buf.position());

        //写入数据到缓存区
        buf.put(str.getBytes());

        System.out.printf("capacity:%d,limit:%d,position:%d\n",buf.capacity(),buf.limit(),buf.position());

        //切换读模式
        buf.flip();

        System.out.printf("capacity:%d,limit:%d,position:%d\n",buf.capacity(),buf.limit(),buf.position());

        //读取数据到缓存区
        byte[] data = new byte[buf.limit()];
        buf.get(data);
        System.out.printf("capacity:%d,limit:%d,position:%d,data:%s\n",buf.capacity(),buf.limit(),buf.position(),new String(data));

        //可重复读
        buf.rewind();
        System.out.printf("capacity:%d,limit:%d,position:%d\n",buf.capacity(),buf.limit(),buf.position());

        //清空缓冲区，但缓存区数据依然存在
        buf.clear();
        System.out.printf("capacity:%d,limit:%d,position:%d,data:%c\n",buf.capacity(),buf.limit(),buf.position(),buf.get());

    }
}
