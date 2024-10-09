package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class PageRemoveMessageTest {
    private static PageRemoveMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte pageId = (byte)random.nextInt();

        PageRemoveMessage out = new PageRemoveMessage(uuid, ok, pageId);

        return out;
    }

    @Test
    public void testWriteRead() {
        PageRemoveMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.PAGE_REMOVE);

        PageRemoveMessage in = new PageRemoveMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());
        Assert.assertEquals(out.getPageId(), in.getPageId());
    }

    @Test
    public void testWriteReadCorrupt() {
        PageRemoveMessage out = createMsg();
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            PageRemoveMessage in = new PageRemoveMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
