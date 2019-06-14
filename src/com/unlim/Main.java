package com.unlim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static int randLength;

    public static void main(String[] args) {
        Random rand = new Random();
        randLength = rand.nextInt(4_000);
        int[] arr = new int[randLength];
        List<int[]> chunks;
        List<Sort> threads;

        FileIO.clear();

        fillRandomArray(arr);

        chunks = Mass.divide(arr);

        writeArrToFile("Main unsorted array: Size = " + arr.length + ". Chunk amount = " + chunks.size(), arr);

        FileIO.writeToFile("\nUnsorted Chunks\n");
        for(int[] i : chunks) {
            writeArrToFile("\nChunk #" + chunks.indexOf(i) + ". Size = " + i.length, i);
        }

        threads = new ArrayList<>();
        for(int[] chunk : chunks) {
            threads.add(new Sort(chunk));
        }

        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < chunks.size(); i++) {
            chunks.set(i, threads.get(i).getSorted());
        }

        FileIO.writeToFile("\nSorted chunks\n");

        for(int[] i : chunks) {
            writeArrToFile("\nChunk #" + chunks.indexOf(i), i);
        }

        arr = Mass.merge(chunks);

        writeArrToFile("\n\nResult", arr);
    }

    private static void writeArrToFile(String message, int[] arr) {
        StringBuffer logger = new StringBuffer();
        logger.setLength(0);
        FileIO.writeToFile(message + "\n");
        for(int i = 0; i < ((arr.length <= 50) ? arr.length : 50); i++) {
            logger.append(arr[i] + ((i == 49) ? " ..." : " "));
        }
        FileIO.writeToFile(logger + "\n");
        logger.setLength(0);
    }

    private static void fillRandomArray(int[] arr) {
        Random rand = new Random();
        for(int i = 0; i < randLength; i++) {
            arr[i] = rand.nextInt(1_000);
        }
    }
}


