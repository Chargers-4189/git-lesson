package app.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    private static LogLevel logSeverity;

    public Logger(LogLevel severity) {
        Logger.logSeverity = severity;
    }

    public static void logEvent(LogLevel level, String source, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS");

        String levelFormat = level == LogLevel.ERROR ? Constants.ANSI_RED + " [" + level + "] " + Constants.ANSI_RESET
                : level == LogLevel.WARN ? Constants.ANSI_YELLOW + " [" + level + "] " + Constants.ANSI_RESET
                        : level == LogLevel.DEBUG ? Constants.ANSI_CYAN + " [" + level + "] " + Constants.ANSI_RESET
                                : " [" + level + "] ";
        String sourceFormat = source == "MARKET" ? Constants.ANSI_GREEN + " [" + source + "]:" + Constants.ANSI_RESET
                : Constants.ANSI_BLUE + " [" + source + "]:" + Constants.ANSI_RESET;

        if (level.ordinal() >= logSeverity.ordinal()) {
            System.err.println(
                    formatter.format(LocalDateTime.now()) + levelFormat + sourceFormat + " "
                            + message);
        }
    }

}
