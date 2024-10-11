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

package com.bennero.common.logging;

import javafx.event.EventHandler;

import java.util.ArrayList;

/**
 * Provides custom logging with different severity levels and ability to listen to logs.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class Logger {
    private static final String LOG_LEVEL_INFO_TEXT = "[INFO] ";
    private static final String LOG_LEVEL_DEBUG_TEXT = "[DEBUG] ";
    private static final String LOG_LEVEL_ERROR_TEXT = "[ERROR] ";
    private static final String LOG_LEVEL_WARNING_TEXT = "[WARNING] ";
    private static LogLevel logLevel = LogLevel.INFO;
    private static ArrayList<EventHandler<LogEvent>> logEventHandlers = new ArrayList<>();

    public static void setLogLevel(LogLevel logLevel) {
        Logger.logLevel = logLevel;
    }

    public static void addLogEventHandler(EventHandler<LogEvent> logEventHandler) {
        logEventHandlers.add(logEventHandler);
    }

    private static boolean isLogLevelSupported(LogLevel logLevel) {
        switch (logLevel) {
            case DEBUG:
                return Logger.logLevel == LogLevel.DEBUG;
            case INFO:
                return Logger.logLevel == LogLevel.DEBUG || Logger.logLevel == LogLevel.INFO;
            case WARNING:
                return Logger.logLevel == LogLevel.DEBUG || Logger.logLevel == LogLevel.INFO || Logger.logLevel == LogLevel.WARNING;
            case ERROR:
                return Logger.logLevel == LogLevel.DEBUG || Logger.logLevel == LogLevel.INFO || Logger.logLevel == LogLevel.WARNING ||
                        Logger.logLevel == LogLevel.WARNING;
            case NONE:
            default:
                return false;
        }
    }

    private static String getLogLevelText(LogLevel logLevel) {
        switch (logLevel) {
            case INFO:
                return LOG_LEVEL_INFO_TEXT;
            case DEBUG:
                return LOG_LEVEL_DEBUG_TEXT;
            case ERROR:
                return LOG_LEVEL_ERROR_TEXT;
            case WARNING:
                return LOG_LEVEL_WARNING_TEXT;
            case NONE:
            default:
                return "";
        }
    }

    public static void log(LogLevel logLevel, String tag, String message) {
        if (isLogLevelSupported(logLevel)) {
            final String formatted = getLogLevelText(logLevel) + tag + ": " + message;

            for (int i = 0; i < logEventHandlers.size(); i++) {
                logEventHandlers.get(i).handle(new LogEvent(formatted, logLevel));
            }

            if (logLevel == LogLevel.ERROR) {
                System.err.println(formatted);
            } else {
                System.out.println(formatted);
            }
        }
    }

    public static void logf(LogLevel logLevel, String tag, String format, Object... args) {
        if (isLogLevelSupported(logLevel)) {
            final String formatted = getLogLevelText(logLevel) + tag + ": " + String.format(format, args);

            for (int i = 0; i < logEventHandlers.size(); i++) {
                logEventHandlers.get(i).handle(new LogEvent(formatted, logLevel));
            }

            if (logLevel == LogLevel.ERROR) {
                System.err.println(formatted);
            } else {
                System.out.println(formatted);
            }
        }
    }
}