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

    public static void main(String[] args) throws InterruptedException {

        GenerateArray generateArray = new GenerateArray(false, 5);

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
