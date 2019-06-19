package com.unlim;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Sort extends Thread implements Callable<int[]> {

    private int[] array;

    Sort(int[] unsorted) {
        this.array = unsorted;
    }

    @Override
    public void run() {
        Arrays.sort(array);
    }

    @Override
    public int[] call() {
        return array;
    }
}
