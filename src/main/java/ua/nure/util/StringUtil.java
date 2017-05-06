package ua.nure.util;

import ua.nure.model.Task;

public class StringUtil {

    public static String getSourceFileName(Task task) {
        return task.getOwner().getLogin() + "_" + task.getName() + "_source.java";
    }
    public static String getTestFileName(Task task) {
        return task.getOwner().getLogin() + "_" + task.getName() + "_test.java";
    }

}
