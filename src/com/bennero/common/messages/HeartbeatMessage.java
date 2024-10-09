package com.bennero.common.messages;

import java.util.UUID;

public class HeartbeatMessage extends Message {
    public HeartbeatMessage(UUID senderUuid, boolean ok) {
        super(senderUuid, ok, MessageType.HEARTBEAT);
    }

    public HeartbeatMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        // Nothing to do here - heartbeat has no data
    }

    @Override
    protected void writeData() {
        // Nothing to do here - heartbeat has no data
    }
}
