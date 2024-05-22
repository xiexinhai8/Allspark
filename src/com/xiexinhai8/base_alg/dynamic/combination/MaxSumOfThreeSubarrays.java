package com.xiexinhai8.base_alg.dynamic.combination;

/**
 * 689. 三个无重叠子数组的最大和
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且全部数字和（3 * k 项）最大的子数组，并返回这三个子数组。
 *
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 * @author xxh
 * Created on 2024-05-22
 */
public class MaxSumOfThreeSubarrays {

    /**
     * 此问题,归根结底是一个组合问题; 后续汇总下组合问题解决方案
     * 如果是一个子数组, 则可以通过滑动窗口实现
     * 二个或两个以上的子数组, 无法通过前面方法实现, 因为选择单个最大的可能导致总体不一定最大
     */
    // f(i, j) = max(f(i, j - 1), f(i - 1, j -k))
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[][] f = new int[4][nums.length];
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        for (int i = 1; i <= 3; i++) {

            for (int j = i * k - 1; j < nums.length; j++) {
                if (j < i * k) {
                    f[i][j] = prefix[j];
                } else {
                    f[i][j] = Math.max(f[i - 1][j - k] + prefix[j] - prefix[j - k], f[i][j - 1]);
                }
            }
        }

        int level = 3;
        int[] result = new int[3];
        for (int i = nums.length - 1; i >= 1; i--) {
            if (f[level][i] == f[level][i - 1]) {
                continue;
            } else {
                result[level - 1] = i - k + 1;
                level--;
                i = i - k + 1;
                if (level <= 0) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 滑动窗口
     */
    public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
        int[] result = new int[3];
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
        int sum3 = 0, maxSum123 = 0;

        for (int i = 2 * k; i < nums.length; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];

            if (i >= 3 * k - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - 3 * k + 1;
                }

                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum12Idx1 = maxSum1Idx;
                    maxSum12Idx2 = i - 2 * k + 1;
                }

                if (maxSum12 + sum3 > maxSum123) {
                    maxSum123 = maxSum12 + sum3;
                    result[0] = maxSum12Idx1;
                    result[1] = maxSum12Idx2;
                    result[2] = i - k + 1;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return result;
    }
}
