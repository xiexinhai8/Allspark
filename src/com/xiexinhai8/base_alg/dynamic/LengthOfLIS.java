package com.xiexinhai8.base_alg.dynamic;

/**
 * 300. 最长递增子序列
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-25
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        int[] d = new int[nums.length];

        d[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int preMaxLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && d[j] > preMaxLen) {
                    preMaxLen = d[j];
                }
            }
            d[i] = preMaxLen + 1;
        }

        int max = d[0];
        for (int i = 1; i < nums.length; i++) {
            if (d[i] > max) {
                max = d[i];
            }
        }

        return max;
    }
}
