package arrayGeneration;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GenerateArrayTest {

    @Test
    void testForgetAll() {
        GenerateArray generateArray = new GenerateArray();
        System.out.println(Arrays.toString(generateArray.getNewArray()));
    }

    @Test
    void testSizeNoFlagWithSize() {
        GenerateArray generateArray = new GenerateArray(10);
        System.out.println(Arrays.toString(generateArray.getNewArray()));
    }

    @Test
    void testFlagFalseNoSize() {
        GenerateArray generateArray = new GenerateArray(false);
        System.out.println(Arrays.toString(generateArray.getNewArray()));
    }

    @Test
    void testFlagTrueNoSize() {
        GenerateArray generateArray = new GenerateArray(true);
        System.out.println(Arrays.toString(generateArray.getNewArray()));
    }

    @Test
    void testFlagTrueWithSize() {
        GenerateArray generateArray = new GenerateArray(true, 10);
        System.out.println(Arrays.toString(generateArray.getNewArray()));
    }

    @Test
    void testFlagFalseWithSize() {
        GenerateArray generateArray = new GenerateArray(false, 10);
        System.out.println(Arrays.toString(generateArray.getNewArray()));
    }

}