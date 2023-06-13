package wkyyzl.cn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class NettyWebSocketServer implements Runnable {
    private int port;
    private ChannelFuture f;

    public NettyWebSocketServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //System.out.println("客户端连接：" + ch);
                            // 添加会话
                            /*NettySession session = new NettySession(ch, ch.remoteAddress().getAddress().getHostAddress(), false);
                            ChannelUtils.addChannelSession(ch, session);*/

                            ChannelPipeline pipeline = ch.pipeline();

                            //pipeline.addLast("idleStateHandler", new IdleStateHandler(15, 5, 0));

                            // Decoder
                            pipeline.addLast("httpServerCodec", new HttpServerCodec(4096, 8192, 8192));
                            //以块的方式来写的处理器
                            pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
                            pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(314572800));

                            pipeline.addLast("webSocketServerCompressionHandler", new WebSocketServerCompressionHandler());
                            //处理websocket的编解码器
                            WebSocketServerProtocolConfig wsConfig = WebSocketServerProtocolConfig.newBuilder()
                                    .websocketPath("/socket")
                                    .maxFramePayloadLength(Integer.MAX_VALUE)
                                    .checkStartsWith(true).build();
                            pipeline.addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler(wsConfig));

                            pipeline.addLast("webSocketFrameHandler", new WebSocketFrameHandler());

                            // timeout和心跳，先注释掉，后期再看是否需要打开
                            //pipeline.addLast("timeoutHandler", new TimeoutHandler(false));

                            //sessions.put(ch, session);
                            //onSessionConnected(session);
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128) // (5)
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 2)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_RCVBUF, 1024 * 1024 * 2)
                    .childOption(ChannelOption.TCP_NODELAY, true); // (6)

            // Bind and start to accept incoming connections.
            f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
