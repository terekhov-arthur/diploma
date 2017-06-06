package ua.nure.data.level_2.fibonacci;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {

    @Test
    public void test(){
        Random random = new Random();
        Fibonacci fibonacci = new Fibonacci();

        for (int i = 0; i < 20; i++ ) {
            int index = random.nextInt(50);
            assertEquals(fibonacci(index), fibonacci.fibonacci(index));
        }
    }

    private long fibonacci(int index) {
        return index <= 1 ? index : fibonacci(index - 1) + fibonacci(index - 2);
    }
    
}
