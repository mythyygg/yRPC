package com.myth.yRPC.core.codec;

import com.caucho.hessian.io.Hessian2Output;
import com.myth.yRPC.core.RpcBaseModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-07 14:59
 **/
public class HessianEncoder extends MessageToByteEncoder<RpcBaseModel> {
    public static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcBaseModel rpcBaseModel, ByteBuf byteBuf) throws Exception {
        int startIndex = byteBuf.writerIndex();
        ByteBufOutputStream bout = new ByteBufOutputStream(byteBuf);
        Hessian2Output hout = null;
        try {
            bout.write(LENGTH_PLACEHOLDER);
            hout = new Hessian2Output(bout);
            hout.writeObject(rpcBaseModel);
            hout.flush();
        }finally {
            try {
                if (hout != null) {
                    hout.close();
                } else {
                    bout.close();
                }
            } catch (IOException e) {

            }
        }
        int endIndex = byteBuf.writerIndex();
        byteBuf.setIndex(startIndex, endIndex - startIndex - 4);
    }
}
