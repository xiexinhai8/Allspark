package com.xiexinhai8.base_alg.dynamic.combination;

/**
 * 2560. 打家劫舍 IV
 *
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 *
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
 *
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 *
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 *
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
 *
 * 返回小偷的 最小 窃取能力。
 * @author xxh
 * Created on 2024-06-27
 */
public class MinCapability {

    /**
     * 动态规划
     * f(k, n) = min(f(k, n - 1), f(k - 1, n - 2) + nums[n])
     * 问题： 超时
     */
    public int minCapability(int[] nums, int k) {
        int[][] f = null;
        f = new int[2][nums.length];
        f[0][0] = nums[0];
        for (int j = 1; j < nums.length; j++) {
            f[0][j] = Math.min(f[0][j - 1], nums[j]);
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 2 * (i - 1); j < nums.length; j++) {
                if (j == 2 * (i - 1)) {
                    f[1][j] = Math.max(f[0][j - 2], nums[j]);
                } else {
                    f[1][j] = Math.min(f[1][j - 1], Math.max(f[0][j - 2], nums[j]));
                }
            }
            f[0] = f[1];
            f[1] = new int[nums.length];
        }



        return f[0][nums.length - 1];

    }
}
