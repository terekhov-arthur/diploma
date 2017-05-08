package ua.nure.util;

import ua.nure.model.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile("class\\s+([\\w\\d]+)");
    public static final String TEST_METHOD_NAME = "test";


    public static String getSourceFileName(Task task) {
        return task.getOwner().getLogin() + "_" + task.getName() + "_source.java";
    }
    public static String getTestFileName(Task task) {
        return task.getOwner().getLogin() + "_" + task.getName() + "_test.java";
    }
    public static String getClassName(String data){
        Matcher matcher = CLASS_NAME_PATTERN.matcher(data);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("wrong class data. Can't find class name");
    }
}
