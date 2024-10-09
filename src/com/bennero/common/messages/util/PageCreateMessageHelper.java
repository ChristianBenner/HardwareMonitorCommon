package com.bennero.common.messages.util;

import com.bennero.common.PageData;
import com.bennero.common.messages.PageCreateMessage;

import java.util.UUID;

public class PageCreateMessageHelper {
    public static PageCreateMessage create(UUID senderUuid, PageData pageData) {
        final int R = 0;
        final int G = 1;
        final int B = 2;

        byte[] pageColour = Common.colorToBytes(pageData.getColour());
        byte[] titleColour = Common.colorToBytes(pageData.getTitleColour());
        byte[] subtitleColour = Common.colorToBytes(pageData.getSubtitleColour());

        return new PageCreateMessage(senderUuid, true, pageData.getUniqueId(),
                pageColour[R], pageColour[G], pageColour[B], titleColour[R], titleColour[G], titleColour[B],
                subtitleColour[R], subtitleColour[G], subtitleColour[B], (byte)pageData.getRows(),
                (byte)pageData.getColumns(), pageData.getNextPageId(), (byte)pageData.getTransitionType(),
                pageData.getTransitionTime(), pageData.getDurationMs(), pageData.getTitle(), pageData.isTitleEnabled(),
                (byte)pageData.getTitleAlignment(), pageData.getSubtitle(), pageData.isSubtitleEnabled(),
                (byte)pageData.getSubtitleAlignment(), pageData.getBackgroundImage());
    }
}
