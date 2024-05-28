package com.xiexinhai8.base_alg.dynamic.knapsack;

/**
 * 494. 目标和
 *
 * 给你一个非负整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * @author xxh
 * Created on 2024-05-28
 */
public class FindTargetSumWays {

    /**
     * 转换为背包问题, 从中找出正数的目标和为p的方法数
     * 正数总和为p, 负数总和为 sum - p; p - (sum - p) = target => 2p = target + sum => p = (target + sum) / 2
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum + target) < 0 || (sum + target) % 2 != 0) {
            return 0;
        }
        int pSum = (target + sum) / 2;

        return findPlusSum(nums, pSum);
    }

    // f(n, psum) = f(n - 1, psum - nums[n]) + f(n - 1, psum);
    private int findPlusSum(int[] nums, int pSum) {
        int[][] f = new int[pSum + 1][nums.length + 1];
        // 如果候选集中没有0元素，则可以使用下面的初始化
        // Arrays.fill(f[0], 1);
        f[0][0] = 1;
        for (int i = 0; i <= pSum; i++) {
            for (int j = 0; j < nums.length; j++) {
                f[i][j + 1] = f[i][j];
                if (i - nums[j] >= 0) {
                    f[i][j + 1] += f[i - nums[j]][j];
                }
            }
        }
        return f[pSum][nums.length];
    }
}
