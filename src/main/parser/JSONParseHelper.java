package main.parser;

/**
 * Created by kalsi on 11/10/17.
 */
public class JSONParseHelper {
    public static Boolean getBooleanValue(String booleanString) {
        return booleanString.equals("true") ? true : false;
    }

    public static String getStringValue(Boolean b) {
        return b ? "true" : "false";
    }

    public static String removeQuotes(String s) {
        if (s.charAt(0) == '\'' || s.charAt(0) == '\"') {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}
