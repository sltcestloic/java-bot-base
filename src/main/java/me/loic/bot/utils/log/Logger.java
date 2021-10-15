package me.loic.bot.utils.log;

import me.loic.bot.Bot;
import me.loic.bot.Constants;
import me.loic.bot.utils.ExceptionUtils;

import java.util.Date;

public class Logger {

    private static void log(String message, LogType logType)
    {
        System.out.println('[' + Constants.DATE_FORMAT.format(new Date()) + "] [" + logType.displayName + "] " + message);
    }

    public static void info(String message)
    {
        log(message, LogType.INFO);
    }

    public static void error(String message)
    {
        log(message, LogType.ERROR);
    }

    public static void warning(String message)
    {
        log(message, LogType.WARNING);
    }

    public static void verbose(String message)
    {
        if (Bot.getInstance().getConfig().VERBOSE)
            log(message, LogType.VERBOSE);
    }

    public static void verboseStackTrace(Throwable throwable)
    {
        if (Bot.getInstance().getConfig().VERBOSE)
            log(ExceptionUtils.getStackTrace(throwable), LogType.VERBOSE);
    }

    enum LogType {

        INFO("Info"),
        ERROR("Error"),
        WARNING("Warning"),
        VERBOSE("Debug");

        private String displayName;

        LogType(String displayName){
            this.displayName = displayName;
        }
    }
}
