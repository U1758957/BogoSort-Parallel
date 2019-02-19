package controller;

import arrayGeneration.GenerateArray;
import bogoOperation.BogoSort;
import bogoOperation.CheckSort;
import bogoOperation.SharedArray;

import java.util.Arrays;

/**
 * The main method for BogoSort-Parallel. Creates two threads (one to bogo, one to check sorted), and runs.
 * Play around with the code, basically only with the GenerateArray bit right under the main declaration.
 */
public class BogoMC {

    /**
     * Main method
     * @param args flag and size respectively. If no arguments, sets default values of false and 8.
     * @throws InterruptedException if the main thread is interrupted / killed.
     */
    public static void main(String[] args) throws InterruptedException {

        int size;
        boolean flag;

        if (args.length > 0) {
            flag = Boolean.parseBoolean(args[0]);
            size = Integer.parseInt(args[1]);
        } else {
            flag = false;
            size = 8;
        }

        GenerateArray generateArray = new GenerateArray(flag, size);

        int[] array = generateArray.getNewArray();

        Thread bogoWorker = new Thread(new BogoSort(array));
        Thread checkWorker = new Thread(new CheckSort());

        System.out.println("Initial Array: " + Arrays.toString(array));

        bogoWorker.start();
        //noinspection StatementWithEmptyBody
        while (SharedArray.getArrayExternal() == null); // Busy-wait until the first array sort is done
        checkWorker.start();

        bogoWorker.join();
        checkWorker.join();

        System.out.println("Sorted Array: " + Arrays.toString(SharedArray.getArrayExternal()));

    }

}
