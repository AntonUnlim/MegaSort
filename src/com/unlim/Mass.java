package com.unlim;

import java.util.ArrayList;
import java.util.List;

class Mass {
    private static int[] mergeSortedChunksToArray(int[] firstArray, int[] secondArray) {
        int[] resultArray = new int[firstArray.length + secondArray.length];
        int firstArrIndex = 0, secondArrIndex = 0, resultArrIndex = 0;

        while (firstArrIndex < firstArray.length && secondArrIndex < secondArray.length) {
            resultArray[resultArrIndex++] = (firstArray[firstArrIndex] < secondArray[secondArrIndex]) ?
                    firstArray[firstArrIndex++] : secondArray[secondArrIndex++];
        }
        while (firstArrIndex < firstArray.length) {
            resultArray[resultArrIndex++] = firstArray[firstArrIndex++];
        }
        while (secondArrIndex < secondArray.length) {
            resultArray[resultArrIndex++] = secondArray[secondArrIndex++];
        }
        return resultArray;
    }

    static List<int[]> divideArrayToChunks(int[] arrayToDivide) {
        List<int[]> resultListOfArrays = new ArrayList<>();
        int arrayLength = arrayToDivide.length;
        if (arrayLength < 1000) {
            resultListOfArrays.add(arrayToDivide);
        } else if (arrayLength < 100_000) {
            resultListOfArrays = splitArrayIntoChunks(arrayToDivide, 5);
        } else if (arrayLength < 500_000) {
            resultListOfArrays = splitArrayIntoChunks(arrayToDivide, 10);
        } else if (arrayLength < 1_000_000) {
            resultListOfArrays = splitArrayIntoChunks(arrayToDivide, 15);
        } else {
            resultListOfArrays = splitArrayIntoChunks(arrayToDivide, 20);
        }
        return resultListOfArrays;
    }

    private static List<int[]> splitArrayIntoChunks(int[] arrayToSplit, int amountOfChunks) {
        List<int[]> resultListOfArrays = new ArrayList<>();
        int arrayLength = arrayToSplit.length;
        int eachChunkLength = arrayLength / amountOfChunks + 1;
        int startPositionToSplit = 0;
        for(int i = 0; i < amountOfChunks; i++) {
            int lastChunkLength = arrayLength % (eachChunkLength * (amountOfChunks - 1));
            boolean isLastChunk = i == (amountOfChunks - 1);
            int lengthToCopy = isLastChunk ? lastChunkLength : eachChunkLength;
            int[] tempArray = new int[lengthToCopy];
            System.arraycopy(arrayToSplit, startPositionToSplit, tempArray, 0, lengthToCopy);
            resultListOfArrays.add(tempArray);
            startPositionToSplit += eachChunkLength;
        }
        return resultListOfArrays;
    }

    static int[] mergeSortedChunksToArray(List<int[]> listOfArrays) {
        int[] resultArray = listOfArrays.get(0);

        for(int i = 1; i < listOfArrays.size(); i++) {
            resultArray = mergeSortedChunksToArray(resultArray, listOfArrays.get(i));
        }
        return resultArray;
    }
}
