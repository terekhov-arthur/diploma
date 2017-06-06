package ua.nure.data.level_1.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringTaskTest {

    @Test
    public void test(){
        StringTask task = new StringTask();

        assertEquals("1", task.convert(1));
        assertEquals("100", task.convert(100));
        assertEquals("1234", task.convert(1234));
    }
}
