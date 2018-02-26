package com.dredh;

import com.dredh.model.CmdHeader;
import com.dredh.model.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class GameClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8090"));

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new GameClientInitializer());

            // Make a new connection.
            Channel ch = b.connect(HOST, PORT).sync().channel();

            while (true) {
                Thread.sleep(1000);
                ch.writeAndFlush(new Message(new CmdHeader((byte)1, "test.TestHandler.test", true), "test"));
            }
            // Close the connection.
            //ch.closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }
}
