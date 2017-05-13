package ua.nure.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringTaskTest {

    @Test
    public void test(){
        StringTask task = new StringTask();

        assertEquals("1", task.convert(1));
        assertEquals("100", task.convert(100));
        assertEquals("1234", task.convert(1234));
    }
}
