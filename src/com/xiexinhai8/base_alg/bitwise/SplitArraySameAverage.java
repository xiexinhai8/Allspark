package com.xiexinhai8.base_alg.bitwise;

/**
 * 805. 数组的均值分割
 *
 * 给定你一个整数数组 nums
 * 我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。
 * 如果可以完成则返回true ， 否则返回 false  。
 * 注意：对于数组 arr ,  average(arr) 是 arr 的所有元素的和除以 arr 长度。
 * @author xxh
 * Created on 2022-11-15
 */
public class SplitArraySameAverage {
    int total = 0;

    public static void main(String[] args) {
        System.out.println(new SplitArraySameAverage().splitArraySameAverage(new int[] {60,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30}));
    }

    /**
     * 通过计算子数组的平均值和总的平均值大小来确定是否能够平均分组
     * 1、计算出总的平均值（由于存在小数点京精度问题， 可以使用sum / n = sum·/ m
     * 2、通过dfs找出sum· / m = sum / n的子数组
     * @param nums
     * @return
     */
    public boolean splitArraySameAverage(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        return dfs(nums, 0, 0, 0);
    }

    private boolean dfs(int[] nums, int curIndex, int sum, int size) {
        if (size >= nums.length || curIndex >= nums.length) {
            return false;
        }
        if (size > 0 && sum * nums.length == total * size) {
            return true;
        }

        if (dfs(nums, curIndex + 1, sum, size)) {
            return true;
        }

        return dfs(nums, curIndex + 1, sum + nums[curIndex], size + 1);
    }
}
