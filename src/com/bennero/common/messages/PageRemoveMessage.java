package com.bennero.common.messages;

import java.util.UUID;

/**
 * PageRemoveMessage stores the data of a page removal request. The message is sent by a connected client only. The
 * message only contains the page ID
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.1
 */
public class PageRemoveMessage extends Message {
    private byte pageId;

    public PageRemoveMessage(UUID senderUuid, boolean ok, byte pageId) {
        super(senderUuid, ok, MessageType.PAGE_REMOVE);
        this.pageId = pageId;
    }

    public PageRemoveMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        pageId = readByte();
    }

    @Override
    protected void writeData() {
        writeByte(pageId);
    }

    public byte getPageId() {
        return pageId;
    }
}
