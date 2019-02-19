package bogoOperation;

import java.util.Arrays;

/**
 * A class for checking if an array is sorted
 */
public class CheckSort implements Runnable {

    private static boolean cont = true;

    /**
     * Check to see if an array is sorted in ascending order.
     * @param arr the array to check
     */
    private void sortCheck(int[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            if (arr[x] > arr[x + 1]) return;
        }
        SharedArray.setReadyFlag(false);
        int[] verify = arr.clone();
        Arrays.sort(verify);
        if (Arrays.equals(arr, verify)) {
            SharedArray.setCompletedFlag();
        }
    }

    /**
     * Allows BogoSort to check if it's allowed to continue
     * @return continue flag
     */
    static boolean isCont() {
        return cont;
    }

    @Override
    public void run() {
        while (!(SharedArray.isCompleted())) {
            if (SharedArray.isReady()) {
                if (BogoSort.isCont()) {
                    cont = false;
                    SharedArray.setReadyFlag(false);
                    sortCheck(SharedArray.getArray());
                }
                SharedArray.setReadyFlag(true);
                cont = true;
            } else cont = true;
        }
    }
}

