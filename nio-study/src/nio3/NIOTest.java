package nio3;


import java.nio.ByteBuffer;

/**
 * 直接缓存区与非直接缓存区
 *  减少内存与jvm copy
 *  @author jsjchai.
 */
public class NIOTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.isDirect());
        buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());
    }

}
