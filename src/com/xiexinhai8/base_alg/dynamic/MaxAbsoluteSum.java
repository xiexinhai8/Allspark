package com.xiexinhai8.base_alg.dynamic;

/**
 * 1749. 任意子数组和的绝对值的最大值
 *
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 *
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 *
 * abs(x) 定义如下：
 *
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-25
 */
public class MaxAbsoluteSum {

    public int maxAbsoluteSum(int[] nums) {
        int size = nums.length;
        int[] f = new int[size];
        f[0] = nums[0];

        int maxSum = nums[0], minSum = nums[0];
        for (int i = 1; i < size; i++) {
            f[i] = Math.max(0, f[i - 1]) + nums[i];
            if (f[i] > maxSum) {
                maxSum = f[i];
            }
        }

        for (int i = 1; i < size; i++) {
            f[i] = Math.min(0, f[i - 1]) + nums[i];
            if (f[i] < minSum) {
                minSum = f[i];
            }
        }

        return maxSum + minSum > 0 ? maxSum : -minSum;
    }
}
