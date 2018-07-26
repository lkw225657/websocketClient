package net.transino.lms.server;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class BytePrintHandler extends ByteToMessageDecoder { // (1)
    private static final Logger log = LoggerFactory.getLogger(BytePrintHandler.class);
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//
//        ByteBuf in = (ByteBuf) msg;
//
//    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
//        ByteBuf in = (ByteBuf) msg;

        try {
            byte[] datas = new byte[in.readableBytes()];
            in.readBytes(datas);
            String out = new String(datas,"UTF-8");
            log.info(out);

//            while (in.isReadable()) { // (1)
//                char c = (char) in.readByte();
//                System.out.print(c);
////                System.out.print( in.readByte());
////                log.info(String.valueOf(c));
//
//
//            }
        } catch (UnsupportedEncodingException e) {

        } finally {
//            System.out.flush();
//            ReferenceCountUtil.release(byteBuf); // (2)
        }
        in.readerIndex(0);
//       byteBuf.readByte();

        ByteBuf otherByteBufRef = in.slice(0, in.readableBytes());

        otherByteBufRef.retain();

        in.readerIndex(in.readableBytes());

        ctx.writeAndFlush(otherByteBufRef).addListener(ChannelFutureListener.CLOSE);

//
////            future.addListener(ChannelFutureListener.CLOSE).sync().channel().closeFuture().sync();
//        future.addListener(ChannelFutureListener.CLOSE);
//        ctx.close();
    }
//
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
//        // 当出现异常就关闭连接
////        cause.printStackTrace();
//        ctx.close();
//    }
}