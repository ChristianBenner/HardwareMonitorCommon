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
    private byte colourR;
    private byte colourG;
    private byte colourB;
    private byte titleColourR;
    private byte titleColourG;
    private byte titleColourB;
    private byte subtitleColourR;
    private byte subtitleColourG;
    private byte subtitleColourB;
    private byte rows;
    private byte columns;
    private byte nextPageId;
    private byte transitionType;
    private int transitionTime;
    private int durationMs;
    private String title;
    private boolean titleEnabled;
    private byte titleAlignment;
    private String subtitle;
    private boolean subtitleEnabled;
    private byte subtitleAlignment;
    private String backgroundImage;

    public PageCreateMessage(UUID senderUuid, boolean ok, byte pageId, byte colourR, byte colourG, byte colourB,
                             byte titleColourR, byte titleColourG, byte titleColourB, byte subtitleColourR,
                             byte subtitleColourG, byte subtitleColourB, byte rows, byte columns, byte nextPageId,
                             byte transitionType, int transitionTime, int durationMs, String title,
                             boolean titleEnabled, byte titleAlignment, String subtitle, boolean subtitleEnabled,
                             byte subtitleAlignment, String backgroundImage) {
        super(senderUuid, ok, MessageType.PAGE_CREATE);
        this.pageId = pageId;
        this.colourR = colourR;
        this.colourG = colourG;
        this.colourB = colourB;
        this.titleColourR = titleColourR;
        this.titleColourG = titleColourG;
        this.titleColourB = titleColourB;
        this.subtitleColourR = subtitleColourR;
        this.subtitleColourG = subtitleColourG;
        this.subtitleColourB = subtitleColourB;
        this.rows = rows;
        this.columns = columns;
        this.nextPageId = nextPageId;
        this.transitionType = transitionType;
        this.transitionTime = transitionTime;
        this.durationMs = durationMs;
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
        colourR = readByte();
        colourG = readByte();
        colourB = readByte();
        titleColourR = readByte();
        titleColourG = readByte();
        titleColourB = readByte();
        subtitleColourR = readByte();
        subtitleColourG = readByte();
        subtitleColourB = readByte();
        rows = readByte();
        columns = readByte();
        nextPageId = readByte();
        transitionType = readByte();
        transitionTime = readInt();
        durationMs = readInt();
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
        writeByte(colourR);
        writeByte(colourG);
        writeByte(colourB);
        writeByte(titleColourR);
        writeByte(titleColourG);
        writeByte(titleColourB);
        writeByte(subtitleColourR);
        writeByte(subtitleColourG);
        writeByte(subtitleColourB);
        writeByte(rows);
        writeByte(columns);
        writeByte(nextPageId);
        writeByte(transitionType);
        writeInt(transitionTime);
        writeInt(durationMs);
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

    public byte getColourR() {
        return colourR;
    }

    public byte getColourG() {
        return colourG;
    }

    public byte getColourB() {
        return colourB;
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

    public byte getRows() {
        return rows;
    }

    public byte getColumns() {
        return columns;
    }

    public byte getNextPageId() {
        return nextPageId;
    }

    public byte getTransitionType() {
        return transitionType;
    }

    public int getTransitionTime() {
        return transitionTime;
    }

    public int getDurationMs() {
        return durationMs;
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