package com.myth.yRPC.core.codec;

import com.caucho.hessian.io.Hessian2Input;
import com.sun.corba.se.impl.protocol.giopmsgheaders.FragmentMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.ReferenceCountUtil;

import java.awt.*;

/**
 * @description: Hessian 解码器
 * @author: yuang gang
 * @create: 2019-03-07 14:58
 **/
public class HessianDecoder extends LengthFieldBasedFrameDecoder {
    public HessianDecoder() {
        this(1048576);
    }
    public HessianDecoder(int maxObjectSize) {
        super(maxObjectSize, 0, 4, 0, 4);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

        Hessian2Input input = null;
        try {
            input = new Hessian2Input(new ByteBufInputStream(frame, true));
            return input.readObject();
        }finally {
            try {
                /** important,release  ByteBuf */
                ReferenceCountUtil.release(frame);
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {

            }
        }
    }
}
