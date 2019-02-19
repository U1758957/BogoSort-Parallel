package arrayGeneration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class generates an array of a defined size, using a flag for randomness.
 */
public class GenerateArray {

    private boolean flag;
    private int size;

    /**
     * The default constructor with parameters
     * @param flag true for random numbers, false for reverse in-order (e.g. 5,4,3,2,1).
     * @param size the size of the array
     */
    public GenerateArray(boolean flag, int size) {
        this.flag = flag;
        this.size = size;
    }

    /**
     * A constructor where the user forgets to input a size, default is 5.
     * @param flag true for random numbers, false for reverse in-order (e.g. 5,4,3,2,1).
     */
    public GenerateArray(boolean flag) {
        this.flag = flag;
        this.size = 5;
    }

    /**
     * A constructor where the user forgets to input a flag, the default is true.
     * @param size true for random numbers, false for reverse in-order (e.g. 5,4,3,2,1).
     */
    public GenerateArray(int size) {
        this.flag = true;
        this.size = size;
    }

    /**
     * A constructor where the user forgets everything, default flag is true, default size is 5.
     */
    public GenerateArray() {
        this.flag = true;
        this.size = 5;
    }

    /**
     * Generate a new array according to the flag and size, and return it.
     * @return the generated array
     */
    public int[] getNewArray() {

        int[] array = new int[size];
        int length = array.length;

        if (flag) {
            for (int x = 0; x < length; x++) {
                array[x] = ThreadLocalRandom.current().nextInt();
            }
        } else {
            for (int x = 0; x < length; x++) {
                array[x] = length - x - 1;
            }
        }
        return array;
    }
}
