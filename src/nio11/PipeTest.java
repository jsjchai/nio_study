package nio11;



import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author jsjchai.
 */
public class PipeTest {

    public static void main(String[] args) throws IOException {

        Pipe pipe = Pipe.open();

        Pipe.SinkChannel sinkChannel =  pipe.sink();

        ByteBuffer buf = ByteBuffer.allocate(16);
        buf.clear();
        buf.put("hello,pipe".getBytes());
        buf.flip();

        while(buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

        sinkChannel.close();


        ByteBuffer source = ByteBuffer.allocate(16);
        Pipe.SourceChannel sourceChannel = pipe.source();
        int bytesRead = sourceChannel.read(source);
        System.out.println(bytesRead +"--------"+new String(source.array()));
    }
}
