package wkyyzl.cn.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    public WebSocketFrameHandler() {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            WebSocketServerProtocolHandler.HandshakeComplete event = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            System.out.println(event.requestUri());
            System.out.println(event.requestHeaders());
            if (event.requestUri().contains("?")) {
                if (ctx.channel() != null) {
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);

        Channel channel = ctx.channel();
        System.out.println("客户端断开：" + channel);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof TextWebSocketFrame) {
            System.out.println("WebSocketFrameHandler " + "TextWebSocketFrame");
        } else if (frame instanceof BinaryWebSocketFrame) {
            System.out.println("WebSocketFrameHandler " + "BinaryWebSocketFrame");
        } else if (frame instanceof CloseWebSocketFrame) {
            System.out.println("WebSocketFrameHandler " + "CloseWebSocketFrame");
        } else if (frame instanceof ContinuationWebSocketFrame) {
            System.out.println("WebSocketFrameHandler " + "ContinuationWebSocketFrame");
        } else if (frame instanceof PingWebSocketFrame) {
            System.out.println("WebSocketFrameHandler " + "PingWebSocketFrame");
        } else if (frame instanceof PongWebSocketFrame) {
            System.out.println("WebSocketFrameHandler " + "PongWebSocketFrame");
        } else {
            String message = "unsupported frame type: " + frame.getClass().getName();
            throw new UnsupportedOperationException(message);
        }
    }
}
