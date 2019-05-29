package nio6;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.function.BiConsumer;

/**
 * @author jsjchai.
 */
public class NIOTest {

    public static void main(String[] args) throws CharacterCodingException {
        String str = "编码格式";
        String charsetName = "utf-8";

        ByteBuffer data = encode(str,charsetName);


        System.out.println(decode(data,charsetName));

        data = encode(str,charsetName);

        System.out.println("gbk:"+decode(data,"gbk"));

        list();

    }

    /**
     * 编码
     * @param str 编码字符串
     * @param charsetName 编码格式
     * @return ByteBuffer
     * @throws CharacterCodingException
     */
    private static ByteBuffer encode(String str,String charsetName) throws CharacterCodingException {
        Charset charset = Charset.forName(charsetName);

        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer buffer = CharBuffer.allocate(512);
        buffer.put(str);
        buffer.flip();

        ByteBuffer data = encoder.encode(buffer);

        for ( byte e : data.array()){
            System.out.print(e+"  ");
        }

        System.out.println();


        return data;
    }

    /**
     *  解码
     * @param buf 解码字符串
     * @param charsetName 解码格式
     * @return 解码数据
     */
    private static String decode(ByteBuffer buf,String charsetName){

        Charset charset = Charset.forName(charsetName);
        CharBuffer buffer = charset.decode(buf);

        return buffer.toString();
    }

    private static  void list(){
        Charset.availableCharsets().forEach(new BiConsumer<String, Charset>() {
            @Override
            public void accept(String s, Charset charset) {
                System.out.println(s+"       "+charset.name());
            }
        });
    }
}
