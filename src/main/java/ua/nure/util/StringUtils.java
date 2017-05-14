package ua.nure.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile("class\\s+([\\w\\d]+)");
    private static final String PACKAGE_PATTERN = "package .*;\\s*";

    public static String getClassName(String data){
        Matcher matcher = CLASS_NAME_PATTERN.matcher(data);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("wrong class data. Can't find class name");
    }

    public static String removePackage(String data) {
        return data.replaceAll(PACKAGE_PATTERN, "");
    }
}
