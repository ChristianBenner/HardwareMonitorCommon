package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class PageCreateMessageTest {
    private static PageCreateMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte pageId = (byte)random.nextInt();
        byte pageColourR = (byte)random.nextInt();
        byte pageColourG = (byte)random.nextInt();
        byte pageColourB = (byte)random.nextInt();
        byte titleColourR = (byte)random.nextInt();
        byte titleColourG = (byte)random.nextInt();
        byte titleColourB = (byte)random.nextInt();
        byte subtitleColourR = (byte)random.nextInt();
        byte subtitleColourG = (byte)random.nextInt();
        byte subtitleColourB = (byte)random.nextInt();
        byte pageRow = (byte)random.nextInt();
        byte pageColumns = (byte)random.nextInt();
        byte nextPageId = (byte)random.nextInt();
        byte pageTransitionType = (byte)random.nextInt();
        int pageTransitionTime = random.nextInt();
        int pageDurationMs = random.nextInt();
        String title = "page title test";
        boolean titleEnabled = true;
        byte titleAlignment = (byte)random.nextInt();
        String subtitle = "page subtitle test";
        boolean subtitleEnabled = false;
        byte subtitleAlignment = (byte)random.nextInt();
        String backgroundImage = "background/image";

        PageCreateMessage out = new PageCreateMessage(uuid, ok, pageId, pageColourR, pageColourG, pageColourB, titleColourR,
                titleColourG, titleColourB, subtitleColourR, subtitleColourG, subtitleColourB, pageRow, pageColumns,
                nextPageId, pageTransitionType, pageTransitionTime, pageDurationMs, title, titleEnabled, titleAlignment,
                subtitle, subtitleEnabled, subtitleAlignment, backgroundImage);

        return out;
    }

    @Test
    public void testWriteRead() {
        PageCreateMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.PAGE_CREATE);

        PageCreateMessage in = new PageCreateMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());
        Assert.assertEquals(out.getPageId(), in.getPageId());
        Assert.assertEquals(out.getColourR(), in.getColourR());
        Assert.assertEquals(out.getColourG(), in.getColourG());
        Assert.assertEquals(out.getColourB(), in.getColourB());
        Assert.assertEquals(out.getTitleColourR(), in.getTitleColourR());
        Assert.assertEquals(out.getTitleColourG(), in.getTitleColourG());
        Assert.assertEquals(out.getTitleColourB(), in.getTitleColourB());
        Assert.assertEquals(out.getSubtitleColourR(), in.getSubtitleColourR());
        Assert.assertEquals(out.getSubtitleColourG(), in.getSubtitleColourG());
        Assert.assertEquals(out.getSubtitleColourB(), in.getSubtitleColourB());
        Assert.assertEquals(out.getRows(), in.getRows());
        Assert.assertEquals(out.getColumns(), in.getColumns());
        Assert.assertEquals(out.getNextPageId(), in.getNextPageId());
        Assert.assertEquals(out.getTransitionType(), in.getTransitionType());
        Assert.assertEquals(out.getTransitionTime(), in.getTransitionTime());
        Assert.assertEquals(out.getDurationMs(), in.getDurationMs());
        Assert.assertEquals(out.getTitle(), in.getTitle());
        Assert.assertEquals(out.isTitleEnabled(), in.isTitleEnabled());
        Assert.assertEquals(out.getTitleAlignment(), in.getTitleAlignment());
        Assert.assertEquals(out.getSubtitle(), in.getSubtitle());
        Assert.assertEquals(out.isSubtitleEnabled(), in.isSubtitleEnabled());
        Assert.assertEquals(out.getSubtitleAlignment(), in.getSubtitleAlignment());
        Assert.assertEquals(out.getBackgroundImage(), in.getBackgroundImage());
    }

    @Test
    public void testWriteReadCorrupt() {
        PageCreateMessage out = createMsg();
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            PageCreateMessage in = new PageCreateMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
