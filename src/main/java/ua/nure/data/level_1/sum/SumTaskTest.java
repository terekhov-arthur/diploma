package ua.nure.data.level_1.sum;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SumTaskTest {

    private SumTask task = new SumTask();
    private Random random = new Random();

    @Test
    public void test(){
        for (int i = 0; i < 20; i++) {
            int a = random.nextInt(10000);
            int b = random.nextInt(10000);

            int result = task.sum(a,b);
            assertEquals(a+b, result);
        }
    }
}
