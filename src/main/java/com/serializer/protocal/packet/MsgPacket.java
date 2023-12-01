package com.serializer.protocal.packet;

import lombok.*;

import static com.serializer.protocal.commond.Command.MSG;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MsgPacket extends Packet {
    /**
     * 序号
     */
    private Integer no;

    /**
     * 会话ID
     */
    private Integer session;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 内容
     */
    private String content;

    @Override
    public Byte getCommand() {
        return MSG;
    }
}
