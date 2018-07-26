package net.transino.lms.modules.comm.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import net.transino.lms.modules.comm.DefaultDecoderHandler;
import net.transino.lms.modules.comm.cfg.Message;

import java.util.List;

/**
 *
 * @author lee
 * @since 5.0
 */
@Slf4j
public class SocketServerHandler extends DefaultDecoderHandler {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        super.decode(ctx,in,list);

//        Message outMsg = new Message();
//        outMsg.setEof(true);
//        for(Object obj: list){
//            if (obj instanceof Message){
//                String head = ((Message)obj).getHead();
//                outMsg.setHead(head.substring(0,head.length() - 4));
//                outMsg.setBody(((Message)obj).getBody());
//                break;
//            }
//        }
//        ctx.writeAndFlush(outMsg).addListener(ChannelFutureListener.CLOSE);
    }
}