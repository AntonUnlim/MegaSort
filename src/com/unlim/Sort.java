package com.unlim;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Sort extends Thread implements Callable<int[]> {

    volatile int[] array;

    public Sort(int[] unsorted) {
        this.array = unsorted;
    }

    @Override
    public void run() {
        Arrays.sort(array);
    }

    @Override
    public int[] call() throws Exception {
        return array;
    }
}
