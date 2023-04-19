package com.xiexinhai8.base_alg.dynamic;

/**
 * 1043. 分隔数组以得到最大和
 *
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 *
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-19
 */
public class MaxSumAfterPartitioning {

    public int maxSumAfterPartitioning1(int[] arr, int k) {
        int n = arr.length;
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int maxValue = arr[i - 1];
            for (int j = i - 1; j >= 0 && j >= i - k; j--) {
                d[i] = Math.max(d[i], d[j] + maxValue * (i - j));
                if (j > 0) {
                    maxValue = Math.max(maxValue, arr[j - 1]);
                }
            }
        }
        return d[n];
    }


    public int maxSumAfterPartitioningSelf(int[] arr, int k) {

        int[] d = new int[arr.length + 1];
        d[0] = 0;
        d[1] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int maxVal = arr[i];
            for (int j = i; j > 0 && j > i - 4; j--) {
                if (maxVal < arr[j]) {
                    maxVal = arr[j];
                }
                d[i] = Math.max(d[i], d[j] + maxVal * (i - j + 1));
            }
        }

        return d[arr.length];

    }

    public int maxSumAfterPartitioning(int[] arr, int k) {

        return maxSumAfterPartitioning(arr, k, 0);

    }

    private int maxSumAfterPartitioning(int[] arr, int k, int start) {
        if (start >= arr.length) {
            return 0;
        }
        int maxVal = 0;
        int tmpMax = arr[start];
        int end = Math.min(arr.length, start + k);
        for (int i = start; i < end; i++) {
            if (arr[i] > tmpMax) {
                tmpMax = arr[i];
            }
            maxVal = Math.max(maxVal, maxSumAfterPartitioning(arr, k, i + 1) + tmpMax * (i - start + 1));
        }
        return maxVal;
    }
}
