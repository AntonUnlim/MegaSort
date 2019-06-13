package com.unlim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        int randLength = rand.nextInt(4_000);
        int[] arr = new int[randLength];
        StringBuffer logger = new StringBuffer();

        FileIO.clear();

        for(int i = 0; i < randLength; i++) {
            arr[i] = rand.nextInt(2_000_000);
        }

        // write main array to log
        FileIO.writeToFile("Main unsorted array\n");
        for(int i = 0; i < arr.length; i++) {
            logger.append(arr[i] + " ");
        }
        FileIO.writeToFile(logger + "\n");
        logger.setLength(0);

        List<int[]> chunks = Mass.divide(arr);

        for(int[] i : chunks) {
            FileIO.writeToFile("Unsorted Chunks\nChunk #" + chunks.indexOf(i) + "\n");
            for(int j = 0; j < i.length; j++) {
                logger.append(i[j] + " ");
            }
            FileIO.writeToFile(logger + "\n");
            logger.setLength(0);
        }
        logger.setLength(0);

        List<Sort> threads = new ArrayList<>();
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

        for(int[] i : chunks) {
            FileIO.writeToFile("Sorted chunks\nChunk #" + chunks.indexOf(i) + "\n");
            for(int j = 0; j < i.length; j++) {
                logger.append(i[j] + " ");
            }
            FileIO.writeToFile(logger + "\n");
            logger.setLength(0);
        }
        logger.setLength(0);
        logger.append("\n\nResult\n");
        arr = Mass.merge(chunks);


        for(int i = 0; i < arr.length; i++) {
            logger.append(arr[i] + " ");
        }
        FileIO.writeToFile(logger + "");
        logger.setLength(0);
    }
}


