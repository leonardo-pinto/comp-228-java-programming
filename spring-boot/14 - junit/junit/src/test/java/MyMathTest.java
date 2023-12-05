import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyMathTest {

    MyMath math = new MyMath();

    @BeforeEach
    void beforeEach() {
        System.out.println("do something");
    }


    @Test
    void calculateSum_NumbersArray() {
        int result = math.calculateSum(new int[] {1, 2, 3});
        int expectedResult = 6;

        assertEquals(expectedResult, result);
    }

    @Test
    void calculateSum_EmptyArray() {
        int result = math.calculateSum(new int[] {});
        int expectedResult = 0;

        assertEquals(expectedResult, result);
    }
}
