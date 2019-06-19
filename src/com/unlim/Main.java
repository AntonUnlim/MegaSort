package com.unlim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static List<int[]> chunks;
    private static List<Sort> threads;
    private static int[] unsortedArray;

    public static void main(String[] args) {
        unsortedArray = new int[getRandomInt(4_000)];
        FileIO.clearLogFile();
        fillArrayWithRandomInts();
        chunks = Mass.divideArrayToChunks(unsortedArray);
        writeArrayToLogFile("Main unsorted array: Size = " + unsortedArray.length + ". Chunk amount = " + chunks.size());
        FileIO.writeMsgToLogFile("\nUnsorted Chunks\n");
        writeListToLogFile();
        addChunksToThreads();
        startAllThreads();
        joinAllThreads();
        callAllThreads();
        FileIO.writeMsgToLogFile("\nSorted chunks\n");
        writeListToLogFile();
        unsortedArray = Mass.mergeSortedChunksToArray(chunks);
        writeArrayToLogFile("\n\nResult");
    }

    private static void startAllThreads() {
        for(Thread thread : threads) {
            thread.start();
        }
    }

    private static void addChunksToThreads() {
        threads = new ArrayList<>();
        for(int[] chunk : chunks) {
            threads.add(new Sort(chunk));
        }
    }

    private static void writeListToLogFile() {
        for(int[] i : chunks) {
            writeArrayToLogFile("\nChunk #" + chunks.indexOf(i) + ". Size = " + i.length);
        }
    }

    private static void callAllThreads() {
        for(int i = 0; i < chunks.size(); i++) {
            try {
                chunks.set(i, threads.get(i).call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void joinAllThreads() {
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeArrayToLogFile(String message) {
        StringBuilder logger = new StringBuilder();
        logger.setLength(0);
        FileIO.writeMsgToLogFile(message + "\n");
        int amountOfIntsToLogFile = 50;
        boolean isSmallArray = unsortedArray.length <= amountOfIntsToLogFile;
        for(int i = 0; i < (isSmallArray ? unsortedArray.length : amountOfIntsToLogFile); i++) {
            logger.append(unsortedArray[i]).append((i == amountOfIntsToLogFile-1) ? " ..." : " ");
        }
        FileIO.writeMsgToLogFile(logger + "\n");
        logger.setLength(0);
    }

    private static void fillArrayWithRandomInts() {
        for(int i = 0; i < unsortedArray.length; i++) {
            unsortedArray[i] = getRandomInt(1_000);
        }
    }

    private static int getRandomInt(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }
}


