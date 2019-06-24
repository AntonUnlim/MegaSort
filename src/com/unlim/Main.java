package com.unlim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] unsortedArray = fillArrayWithRandomInts();
        List<int[]> unsortedChunks = Mass.divideArrayToChunks(unsortedArray);
        List<Sort> threads = new ArrayList<>();
        addChunksToThreads(unsortedChunks, threads);
        startAllThreads(threads);
        List<int[]> sortedChunks = callAllThreads(threads);
        int[] sortedArray = Mass.mergeSortedChunksToArray(sortedChunks);
    }

    private static void addChunksToThreads(List<int[]> chunks, List<Sort> threads) {
        for(int[] chunk : chunks) {
            threads.add(new Sort(chunk));
        }
    }

    private static void startAllThreads(List<Sort> threads) {
        for(Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<int[]> callAllThreads(List<Sort> threads) {
        List<int[]> sortedChunks = new ArrayList<>();
        for(int i = 0; i < threads.size(); i++) {
            try {
                sortedChunks.add(threads.get(i).call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sortedChunks;
    }

    private static int[] fillArrayWithRandomInts() {
        int[] unsortedArray = new int[getRandomInt(4_000)];
        for(int i = 0; i < unsortedArray.length; i++) {
            unsortedArray[i] = getRandomInt(1_000);
        }
        return unsortedArray;
    }

    private static int getRandomInt(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }
}


