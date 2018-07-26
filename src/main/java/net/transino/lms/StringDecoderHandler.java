package net.transino.lms;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import net.transino.lms.modules.comm.cfg.Message;
import net.transino.lms.modules.comm.client.AbstractMessageAdapter;

/**
 * @author veggieg
 * @since 5.0
 */
@Slf4j
public class StringDecoderHandler extends SimpleChannelInboundHandler<String> {
    private AbstractMessageAdapter adapter;
    public StringDecoderHandler(AbstractMessageAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String str) throws Exception {
        log.info("--->接收 Message:" + str);
        this.adapter.getOutput().setHead("ERROR!");
        this.adapter.getOutput().setBody("{\"error\":\""+ str +"\"}");
    }
}
