package ua.nure.data;

public class StringTaskTest {

    private StringTask task;

    public StringTaskTest(StringTask task) {
        this.task = task;
    }

    public boolean test(){
        boolean result = true;

        result &= "1".equals(task.convert(1));
        result &= "2".equals(task.convert(2));
        result &= "3".equals(task.convert(3));
        result &= "1234567".equals(task.convert(1234567));

        return result;
    }
}
