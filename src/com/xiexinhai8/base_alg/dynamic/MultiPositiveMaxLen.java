package com.xiexinhai8.base_alg.dynamic;

/**
 * 1567. 乘积为正数的最长子数组长度
 * @author xxh
 * Created on 2022-07-31
 */
public class MultiPositiveMaxLen {

    public static void main(String[] args) {
        int[] nums = {1, -2, -3, 4};
        getMaxLen(nums);
    }

    public static int getMaxLen(int[] nums) {
        int[] dMax = new int[nums.length];
        int[] dMin = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] > 0) {
                    dMax[i] = 1;
                    dMin[i] = 0;
                } else if (nums[i] < 0) {
                    dMax[i] = 0;
                    dMin[i] = 1;
                }
            } else {
                if (nums[i] > 0) {
                    dMax[i] = dMax[i - 1] + 1;
                    if (dMin[i - 1] > 0) {
                        dMin[i] = dMin[i - 1] + 1;
                    }
                } else if (nums[i] < 0) {
                    if (dMin[i - 1] > 0) {
                        dMax[i] = dMin[i - 1] + 1;
                    }
                    dMin[i] = dMax[i - 1] + 1;
                }
            }
        }
        int ml = dMax[0];
        for (int i = 1; i < nums.length; i++) {
            if (ml < dMax[i]) {
                ml = dMax[i];
            }
        }
        return ml;
    }
}
