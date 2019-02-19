package bogoOperation;

import java.util.Arrays;

/**
 * This class allows BogoSort and CheckSort to share the array and communicate. All methods are synchronized
 * to avoid race conditions and data corruptions of the array.
 */
public class SharedArray {

    private static int[] array;
    private static boolean readyFlag = false;
    private static boolean completedFlag = false;

    /**
     * Set the updated array
     * @param array the set array
     */
    synchronized static void setArray(int[] array) {
        SharedArray.array = array;
    }

    /**
     * Get the updated array
     * @return the array
     */
    synchronized static int[] getArray() {
        return array;
    }

    /**
     * Set whether or not the sort is complete, default false.
     */
    synchronized static void setCompletedFlag() {
        SharedArray.completedFlag = true;
    }

    /**
     * Set whether or not it is OK for another class to access the array setters and getters
     * @param readyFlag the flag indicating whether or not the setters and getters are ready to be accessed
     */
    synchronized static void setReadyFlag(boolean readyFlag) {
        SharedArray.readyFlag = readyFlag;
    }

    /**
     * See whether or not the array is sorted
     * @return the completed flag
     */
    synchronized static boolean isCompleted() {
        return completedFlag;
    }

    /**
     * See whether or not the getters and setters are ready to be accessed
     * @return the ready flag
     */
    synchronized static boolean isReady() {
        return readyFlag;
    }

    /**
     * Get the sorted array outside of the package (e.g. for tests, or the main method)
     * @return the sorted array
     */
    public synchronized static int[] getArrayExternal() {
        return array;
    }
}
