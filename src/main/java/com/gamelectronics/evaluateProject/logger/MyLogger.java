package com.gamelectronics.evaluateProject.logger;

import java.io.IOException;
import java.util.logging.*;

class MyLogger {
    static private FileHandler fileHTML;

    static Logger loggerRegister() {
        Formatter formatterHTML;

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.ALL);
        try {
            fileHTML = new FileHandler("evaluationProject_log.html");
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        }

        formatterHTML = new MyHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
        return logger;
    }
}