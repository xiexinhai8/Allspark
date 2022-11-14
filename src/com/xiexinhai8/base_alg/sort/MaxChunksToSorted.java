package com.xiexinhai8.base_alg.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xxh
 * Created on 2022-08-15
 */
public class MaxChunksToSorted {

    public static void main(String[] args) {
        int[] x = {1,1,0,0,1};
        System.out.println(maxChunksToSorted(x));
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.toString();
    }

    public static int maxChunksToSorted(int[] arr) {
        int[] sortedArr = new int[arr.length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        //map.keySet().
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        Arrays.sort(sortedArr);

        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) != null) {
                continue;
            }
            for (int j = 0; j < arr.length; j++) {
                if (sortedArr[j] == arr[i]) {
                    if (map.get(arr[i]) == null) {
                        map.put(arr[i], new ArrayList<Integer>());
                    }
                    map.get(arr[i]).add(j);
                }
            }
        }

        int maxValue = Integer.MIN_VALUE;
        int maxIndex = 0;
        int chunkSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
                maxIndex = map.get(arr[i]).get(0);
            }
            if (i == maxIndex) {
                chunkSum++;
                List<Integer> tmp = map.get(maxValue);
                tmp.remove(0);
                map.put(arr[i], tmp);
                maxValue = Integer.MIN_VALUE;
            }
        }
        return chunkSum;
    }
}
