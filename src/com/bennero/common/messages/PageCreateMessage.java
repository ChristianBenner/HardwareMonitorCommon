/*
 * ============================================ GNU GENERAL PUBLIC LICENSE =============================================
 * Hardware Monitor for the remote monitoring of a systems hardware information
 * Copyright (C) 2021  Christian Benner
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Additional terms included with this license are to:
 * - Preserve legal notices and author attributions such as this one. Do not remove the original author license notices
 *   from the program
 * - Preserve the donation button and its link to the original authors donation page (christianbenner35@gmail.com)
 * - Only break the terms if given permission from the original author christianbenner35@gmail.com
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <https://www.gnu.org/licenses/>.
 * =====================================================================================================================
 */

package com.bennero.common.messages;

import java.util.UUID;

/**
 * PageCreateMessage stores the data of a page creation request. The PageCreateMessage is sent by a connected client
 * only. If a PageCreateMessage is received but the ID of the page already exists, the page with that ID will be
 * updated with the new information (allowing to change of attributes such as page colour, titles etc).
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.1
 */
public class PageCreateMessage extends Message {
    private static int TITLE_STR_NUM_BYTES = 64;
    private static int SUBTITLE_STR_NUM_BYTES = 64;
    private static int BACKGROUND_IMAGE_STR_NUM_BYTES = 48;

    private byte pageId;
    private byte pageColourR;
    private byte pageColourG;
    private byte pageColourB;
    private byte titleColourR;
    private byte titleColourG;
    private byte titleColourB;
    private byte subtitleColourR;
    private byte subtitleColourG;
    private byte subtitleColourB;
    private byte pageRows;
    private byte pageColumns;
    private byte nextPageId;
    private byte pageTransitionType;
    private int pageTransitionTime;
    private int pageDurationMs;
    private String title;
    private boolean titleEnabled;
    private byte titleAlignment;
    private String subtitle;
    private boolean subtitleEnabled;
    private byte subtitleAlignment;
    private String backgroundImage;

    public PageCreateMessage(UUID senderUuid, boolean ok, byte pageId, byte pageColourR, byte pageColourG, byte pageColourB,
                             byte titleColourR, byte titleColourG, byte titleColourB, byte subtitleColourR,
                             byte subtitleColourG, byte subtitleColourB, byte pageRows, byte pageColumns, byte nextPageId,
                             byte pageTransitionType, int pageTransitionTime, int pageDurationMs, String title,
                             boolean titleEnabled, byte titleAlignment, String subtitle, boolean subtitleEnabled,
                             byte subtitleAlignment, String backgroundImage) {
        super(senderUuid, ok, MessageType.PAGE_CREATE);
        this.pageId = pageId;
        this.pageColourR = pageColourR;
        this.pageColourG = pageColourG;
        this.pageColourB = pageColourB;
        this.titleColourR = titleColourR;
        this.titleColourG = titleColourG;
        this.titleColourB = titleColourB;
        this.subtitleColourR = subtitleColourR;
        this.subtitleColourG = subtitleColourG;
        this.subtitleColourB = subtitleColourB;
        this.pageRows = pageRows;
        this.pageColumns = pageColumns;
        this.nextPageId = nextPageId;
        this.pageTransitionType = pageTransitionType;
        this.pageTransitionTime = pageTransitionTime;
        this.pageDurationMs = pageDurationMs;
        this.title = title;
        this.titleEnabled = titleEnabled;
        this.titleAlignment = titleAlignment;
        this.subtitle = subtitle;
        this.subtitleEnabled = subtitleEnabled;
        this.subtitleAlignment = subtitleAlignment;
        this.backgroundImage = backgroundImage;
    }

    public PageCreateMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        pageId = readByte();
        pageColourR = readByte();
        pageColourG = readByte();
        pageColourB = readByte();
        titleColourR = readByte();
        titleColourG = readByte();
        titleColourB = readByte();
        subtitleColourR = readByte();
        subtitleColourG = readByte();
        subtitleColourB = readByte();
        pageRows = readByte();
        pageColumns = readByte();
        nextPageId = readByte();
        pageTransitionType = readByte();
        pageTransitionTime = readInt();
        pageDurationMs = readInt();
        title = readString(TITLE_STR_NUM_BYTES);
        titleEnabled = readBool();
        titleAlignment = readByte();
        subtitle = readString(SUBTITLE_STR_NUM_BYTES);
        subtitleEnabled = readBool();
        subtitleAlignment = readByte();
        backgroundImage = readString(BACKGROUND_IMAGE_STR_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeByte(pageId);
        writeByte(pageColourR);
        writeByte(pageColourG);
        writeByte(pageColourB);
        writeByte(titleColourR);
        writeByte(titleColourG);
        writeByte(titleColourB);
        writeByte(subtitleColourR);
        writeByte(subtitleColourG);
        writeByte(subtitleColourB);
        writeByte(pageRows);
        writeByte(pageColumns);
        writeByte(nextPageId);
        writeByte(pageTransitionType);
        writeInt(pageTransitionTime);
        writeInt(pageDurationMs);
        writeString(title, TITLE_STR_NUM_BYTES);
        writeBool(titleEnabled);
        writeByte(titleAlignment);
        writeString(subtitle, SUBTITLE_STR_NUM_BYTES);
        writeBool(subtitleEnabled);
        writeByte(subtitleAlignment);
        writeString(backgroundImage, BACKGROUND_IMAGE_STR_NUM_BYTES);
    }

    public byte getPageId() {
        return pageId;
    }

    public byte getPageColourR() {
        return pageColourR;
    }

    public byte getPageColourG() {
        return pageColourG;
    }

    public byte getPageColourB() {
        return pageColourB;
    }

    public byte getTitleColourR() {
        return titleColourR;
    }

    public byte getTitleColourG() {
        return titleColourG;
    }

    public byte getTitleColourB() {
        return titleColourB;
    }

    public byte getSubtitleColourR() {
        return subtitleColourR;
    }

    public byte getSubtitleColourG() {
        return subtitleColourG;
    }

    public byte getSubtitleColourB() {
        return subtitleColourB;
    }

    public byte getPageRows() {
        return pageRows;
    }

    public byte getPageColumns() {
        return pageColumns;
    }

    public byte getNextPageId() {
        return nextPageId;
    }

    public byte getPageTransitionType() {
        return pageTransitionType;
    }

    public int getPageTransitionTime() {
        return pageTransitionTime;
    }

    public int getPageDurationMs() {
        return pageDurationMs;
    }

    public String getTitle() {
        return title;
    }

    public boolean isTitleEnabled() {
        return titleEnabled;
    }

    public byte getTitleAlignment() {
        return titleAlignment;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isSubtitleEnabled() {
        return subtitleEnabled;
    }

    public byte getSubtitleAlignment() {
        return subtitleAlignment;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }
}