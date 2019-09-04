package com.gamelectronics.evaluateProject.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

class MyHtmlFormatter extends Formatter {
    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append("<tr>\n");

        if (rec.getLevel().intValue() == Level.WARNING.intValue()) {
            buf.append("\t<td class=\"btn danger\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
        } else if (rec.getLevel().intValue() == Level.INFO.intValue()) {
            buf.append("\t<td class=\"btn info\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
        } else if (rec.getLevel().intValue() == Level.SEVERE.intValue()) {
            buf.append("\t<td class=\"btn warning\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
        } else if (rec.getLevel().intValue() == Level.FINE.intValue()) {
            buf.append("\t<td class=\"btn success\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
        } else {
            buf.append("\t<td class=\"btn default\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
        }

        buf.append("</td>\n");
        buf.append("\t<td>");
        buf.append(calcDate(rec.getMillis()));
        buf.append("</td>\n");
        buf.append("\t<td>");
        buf.append(formatMessage(rec));
        buf.append("</td>\n");
        buf.append("</tr>\n");

        return buf.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

    public String getHead(Handler h) {
        return "<!DOCTYPE html>\n<head>\n<style>\n"
                + "table { width: 100% }\n"
                + "th { font:bold 10pt Tahoma; }\n"
                + "td { font:normal 10pt Tahoma; }\n"
                + "h1 {font:normal 11pt Tahoma;}\n"
                + ".btn {"
                + "border: none;"
                + "color: white;"
                + "padding: 14px 28px;"
                + "cursor: pointer;}"
                + ".success {background-color: #4CAF50;}"
                + ".success:hover {background-color: #46a049;}"
                + ".info {background-color: #2196F3;}"
                + ".info:hover {background: #0b7dda;}"
                + ".warning {background-color: #ff9800;}"
                + ".warning:hover {background: #e68a00;}"
                + ".danger {background-color: #f44336;}"
                + ".danger:hover {background: #da190b;}"
                + ".default {background-color: #e7e7e7; color: black;}"
                + ".default:hover {background: #ddd;}"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + (new Date()) + "</h1>\n"
                + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
                + "<tr align=\"left\">\n"
                + "\t<th style=\"width:10%\">Loglevel</th>\n"
                + "\t<th style=\"width:15%\">Time</th>\n"
                + "\t<th style=\"width:75%\">Log Message</th>\n"
                + "</tr>\n";
    }

    public String getTail(Handler h) {
        return "</table>\n</body>\n</html>";
    }
}