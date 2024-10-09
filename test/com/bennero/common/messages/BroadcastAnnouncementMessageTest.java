package com.bennero.common.messages;

import com.bennero.common.Constants;
import org.junit.*;

import java.util.Random;
import java.util.UUID;

public class BroadcastAnnouncementMessageTest {
    @Test
    public void testWriteRead() {
        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        long systemIdentifier = Constants.HW_EDITOR_SYSTEM_UNIQUE_CONNECTION_ID;
        byte[] ipv4 = TestUtil.randomIp4();

        BroadcastAnnouncementMessage message = new BroadcastAnnouncementMessage(uuid, ok, systemIdentifier, ipv4);
        byte[] bytes = message.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.BROADCAST);

        BroadcastAnnouncementMessage readMessage = new BroadcastAnnouncementMessage(bytes);

        Assert.assertEquals(uuid, readMessage.getSenderUuid());
        Assert.assertEquals(ok ? Message.STATUS_OK : Message.STATUS_BAD_RECEIVE, readMessage.getStatus());
        Assert.assertEquals(systemIdentifier, readMessage.getSystemIdentifier());
        Assert.assertArrayEquals(ipv4, readMessage.getIp4Address());
    }

    @Test
    public void testWriteReadCorrupt() {
        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        long systemIdentifier = Constants.HW_EDITOR_SYSTEM_UNIQUE_CONNECTION_ID;
        byte[] ipv4 = TestUtil.randomIp4();

        BroadcastAnnouncementMessage message = new BroadcastAnnouncementMessage(uuid, ok, systemIdentifier, ipv4);
        byte[] bytes = message.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            BroadcastAnnouncementMessage readMessage = new BroadcastAnnouncementMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, readMessage.getStatus());
        }
    }
}
