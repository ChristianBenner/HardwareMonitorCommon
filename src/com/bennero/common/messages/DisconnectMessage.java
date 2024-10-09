package com.bennero.common.messages;

import java.util.UUID;

public class DisconnectMessage extends Message {
    public DisconnectMessage(UUID senderUuid, boolean ok) {
        super(senderUuid, ok, MessageType.DISCONNECT);
    }

    public DisconnectMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        // Nothing to read
    }

    @Override
    protected void writeData() {
        // Nothing to write
    }
}
