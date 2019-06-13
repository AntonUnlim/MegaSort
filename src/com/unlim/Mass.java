package com.unlim;

import java.util.ArrayList;
import java.util.List;

public class Mass {
    private static int[] merge(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int i = 0, j = 0, k = 0;

        while (i < first.length && j < second.length)
            result[k++] = first[i] < second[j] ? first[i++] :  second[j++];

        while (i < first.length)
            result[k++] = first[i++];


        while (j < second.length)
            result[k++] = second[j++];

        return result;
    }

    public static List<int[]> divide(int[] bigArr) {
        List<int[]> resultList = new ArrayList<>();
        int arrLength = bigArr.length;
        if      (arrLength < 1000)      resultList.add(bigArr);
        else if (arrLength < 100_000)   resultList = splitArr(bigArr, 5);
        else if (arrLength < 500_000)   resultList = splitArr(bigArr, 10);
        else if (arrLength < 1_000_000) resultList = splitArr(bigArr, 15);
        else                            resultList = splitArr(bigArr, 20);
        return resultList;
    }

    private static List<int[]> splitArr(int[] bigArr, int numOfChunks) {
        List<int[]> res = new ArrayList<>();
        int arrLength = bigArr.length;
        int chunkLength = arrLength / numOfChunks + 1;
        int startPos = 0;
        for(int i = 0; i < numOfChunks; i++) {
            int lengthToCopy = (i == numOfChunks - 1) ? arrLength % (chunkLength*(numOfChunks-1)) : chunkLength;
            int[] tempArr = new int[lengthToCopy];
            System.arraycopy(bigArr, startPos, tempArr, 0, lengthToCopy);
            res.add(tempArr);
            startPos += chunkLength;
        }
        return res;
    }

    public static int[] merge(List<int[]> listOfArr) {
        int length = 0;
        for(int[] i : listOfArr) {
            length += i.length;
        }
        int[] result = listOfArr.get(0);

        for(int i = 1; i < listOfArr.size(); i++) {
            result = merge(result, listOfArr.get(i));
        }

        return result;
    }

}
