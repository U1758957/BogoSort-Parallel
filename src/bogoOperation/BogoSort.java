package bogoOperation;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class for performing a BogoSort. A BogoSort takes a given array, checks if it is sorted, and if not, shuffles
 * the entire array, and repeats, until sorted. A real life parallel to this would be taking a deck of cards, seeing if
 * the deck is sorted, and if the deck is not sorted, shuffling the entire deck of cards randomly, and repeating.
 */
public class BogoSort implements Runnable {

    private int[] array;
    private static boolean cont;

    /**
     * A default constructor
     * @param array an array to sort
     */
    public BogoSort(int[] array) {
        this.array = array;
    }

    /**
     * Swap two elements of an array
     * @param arr the array to modify
     * @param x the first index to swap
     * @param y the second index to swap
     */
    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     * the sorting algorithm, BogoSort
     */
    private void sort() {
        for (int x = 0; x < array.length; x++) {
            swap(array, x, ThreadLocalRandom.current().nextInt(array.length));
        }
    }

    /**
     * Allows CheckSort to see if it is allowed to continue
     * @return continue flag
     */
    static boolean isCont() {
        return cont;
    }

    @Override
    public void run() {
        SharedArray.setReadyFlag(true);
        while (!(SharedArray.isCompleted())) {
            if (SharedArray.isReady()) {
                SharedArray.setReadyFlag(false);
                if (CheckSort.isCont()) {
                    cont = false;
                    sort();
                    SharedArray.setArray(array);
                }
                SharedArray.setReadyFlag(true);
                cont = true;
            } else cont = true;
        }
    }
}
