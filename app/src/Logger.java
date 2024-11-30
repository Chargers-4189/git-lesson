package app.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    private static LogLevel logSeverity;

    /**
     * Constructs a new Logger instance with the specified log severity level. Only
     * log events with a severity level equal to or greater than this level will be
     * output.
     *
     * @param severity The minimum log level to be output (e.g., INFO, WARNING,
     *                 ERROR, DEBUG).
     *                 A lower severity level will output more logs. If null,
     *                 defaults to INFO.
     * @throws NullPointerException if the severity parameter is null.
     */
    public Logger(LogLevel severity) {
        if (severity == null) {
            logSeverity = LogLevel.INFO;
        } else {
            Logger.logSeverity = severity;
        }
    }

    /**
     * Returns the currently configured log severity level for this logger instance.
     *
     * @return The current log severity level.
     */
    public static LogLevel getLogSeverity() {
        return logSeverity;
    }

    /**
     * Logs an event with a specified log level, source, and message. The log
     * message is formatted with a timestamp and color-coded based on the log level.
     * ERROR and WARN messages are printed to `System.err`, while INFO messages are
     * printed to `System.out`. DEBUG messages are handled like INFO messages.
     * <p>
     * This method uses ANSI escape codes for color output. Ensure your console
     * supports ANSI escape codes for proper color rendering.
     *
     * @param level   The severity level of the log event.
     * @param source  The source or component where the event originated (e.g.,
     *                "MARKET", "ACCOUNT").
     * @param message The log message string.
     * @throws NullPointerException if any of the parameters are null.
     */
    public static void logEvent(LogLevel level, String source, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS");

        String levelFormat = level == LogLevel.ERROR ? Constants.ANSI_RED + " [" + level + "] " + Constants.ANSI_RESET
                : level == LogLevel.WARN ? Constants.ANSI_YELLOW + " [" + level + "] " + Constants.ANSI_RESET
                        : level == LogLevel.DEBUG ? Constants.ANSI_CYAN + " [" + level + "] " + Constants.ANSI_RESET
                                : " [" + level + "] ";
        String sourceFormat = source == "MARKET" ? Constants.ANSI_GREEN + " [" + source + "]:" + Constants.ANSI_RESET
                : Constants.ANSI_BLUE + " [" + source + "]:" + Constants.ANSI_RESET;

        if (level.ordinal() >= logSeverity.ordinal()) {
            if (level == LogLevel.INFO) {
                System.out.println(
                        formatter.format(LocalDateTime.now()) + levelFormat + sourceFormat + " "
                                + message);
            }
            System.err.println(
                    formatter.format(LocalDateTime.now()) + levelFormat + sourceFormat + " "
                            + message);
        }
    }

}
