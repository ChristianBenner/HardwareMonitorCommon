package com.bennero.common.messages;

import java.util.UUID;

import static com.bennero.common.Constants.MESSAGE_TYPE_POS;
import static com.bennero.common.messages.HeartbeatDataPositions.STATUS_POS;
import static com.bennero.common.messages.MessageUtils.readUuid;
import static com.bennero.common.Constants.MESSAGE_NUM_BYTES;

public class HeartbeatMessage {
    private static final byte STATUS_GOOD = 1;
    private static final byte STATUS_BAD = 2;

    private UUID instanceUuid;
    private boolean ok;

    public static HeartbeatMessage readHeartbeatMessage(byte[] bytes) {
        HeartbeatMessage heartbeatMessage = new HeartbeatMessage();
        heartbeatMessage.instanceUuid = readUuid(bytes, HeartbeatDataPositions.INSTANCE_GUID_POS);
        heartbeatMessage.ok = bytes[STATUS_POS] == STATUS_GOOD;
        return heartbeatMessage;
    }

    public static byte[] create(UUID instanceUuid, boolean ok) {
        byte[] message = new byte[MESSAGE_NUM_BYTES];
        message[MESSAGE_TYPE_POS] = MessageType.HEARTBEAT_MESSAGE;
        MessageUtils.writeToMessage(message, HeartbeatDataPositions.INSTANCE_GUID_POS, instanceUuid);
        message[STATUS_POS] = ok ? STATUS_GOOD : STATUS_BAD;
        return message;
    }

    public UUID getInstanceUuid() {
        return instanceUuid;
    }

    public boolean isOk() {
        return ok;
    }
}
