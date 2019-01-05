package nio4;

/**
 * 通道（Channel）:用于源节点与目标节点的连接，在Java NIO中负责缓冲区中数据的传输
 * channel 本身不存储数据，需要配合缓存区进行传输
 *
 * java 主要实现类
 * java.nio.channels.Channel 接口
 *     FileChannel
 *     SocketChannel
 *     ServerSocketChannel
 *     DatagramChannel
 *
 * java getChannel()方法
 *   本地IO
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *    网络IO
 *       Socket
 *       ServerSocket
 *       DatagramSocket
 *  jdk 1.7 NIO2
 *        open()
 *        Files  newByteChannel()
 *
 * @author jsjchai.
 */
public class ChannelTest {
}
