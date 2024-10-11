package com.bennero.common.messages;

import java.util.UUID;

public class ConfirmationMessage extends Message {
    public ConfirmationMessage(UUID senderUuid, boolean ok) {
        super(senderUuid, ok, MessageType.CONFIRMATION);
    }

    public ConfirmationMessage(byte[] bytes) {
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
