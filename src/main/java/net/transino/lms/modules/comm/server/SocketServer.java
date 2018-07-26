package net.transino.lms.modules.comm.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import net.transino.lms.modules.comm.DefaultDecoderHandler;
import net.transino.lms.modules.comm.FileDecoderHandler;
import net.transino.lms.modules.comm.MessageDecoderHandler;
import net.transino.lms.modules.comm.MessageEncoderHandler;
import net.transino.lms.modules.comm.client.AbstractMessageAdapter;
import net.transino.lms.modules.comm.client.DefaultMessageAdapter;
import net.transino.lms.server.BytePrintHandler;

/**
 *
 * @author lee
 * @since 5.0
 */
@Slf4j
public class SocketServer {

    private String port;
    private String groupParent;
    private String groupChild;

    public void start(){
        EventLoopGroup parentGroup = new NioEventLoopGroup(Integer.parseInt(this.groupParent));
        EventLoopGroup childGroup = new NioEventLoopGroup(Integer.parseInt(this.groupChild));

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            AbstractMessageAdapter adapter = null;
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new BytePrintHandler());
                            pipeline.addLast(new MessageEncoderHandler());
                            pipeline.addLast(new SocketServerHandler());
                            pipeline.addLast(new MessageDecoderHandler(adapter));
                            pipeline.addLast(new FileDecoderHandler(adapter));
                            pipeline.addLast(new SocketServerHandler());
                        }
                    });
            log.info("服务端启动 Start ...");
            ChannelFuture channelFuture = serverBootstrap.bind(Integer.parseInt(this.port)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setGroupParent(String groupParent) {
        this.groupParent = groupParent;
    }

    public void setGroupChild(String groupChild) {
        this.groupChild = groupChild;
    }

    public void init() {
        this.start();
    }
}