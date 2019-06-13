package com.unlim;

import java.util.Arrays;

public class Sort extends Thread {

    volatile int[] array;

    public Sort(int[] unsorted) {
        this.array = unsorted;
    }

    @Override
    public void run() {
        Arrays.sort(array);
    }

    public int[] getSorted() {
        return array;
    }
}
