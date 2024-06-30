package com.xiexinhai8.base_alg.back_trace;

import java.util.Arrays;

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
     * 采用回溯的方式计算
     * f(0, target) = f(1, target + nums[i]) + f(1, target - nums[i]);
     */
    public int findTargetSumWays(int[] nums, int target) {

        return bt(0, nums, target);
    }

    private int bt(int start, int[] nums, int target) {
        if (start >= nums.length) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }

        return bt(start + 1, nums, target + nums[start]) + bt(start + 1, nums, target - nums[start]);
    }

    /**
     * 采用回溯的方式计算
     * f(n, p) = f(n - 1, p) + f(n - 1, p - nums[n])
     *  n < 0 or p < 0 f(n, p) = 0
     *  p = 0 f(n, p) = 1
     */

    int[][] cache;
    public int findTargetSumWays_2(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        sum += target;
        // 必须增加sum < 0, 原因是target最小值为全选负号, sum最少也是0
        if (sum < 0 || sum % 2 != 0) {
            return 0;
        }
        target = sum / 2;

        cache = new int[nums.length][target + 1];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
        return bt(nums.length - 1, target, nums);
    }

    private int bt(int n, int target, int[] nums) {

        if (n < 0 || target < 0) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        if (cache[n][target] != -1) {
            return cache[n][target];
        }

        int method = bt(n - 1, target, nums) + bt(n - 1, target - nums[n], nums);
        cache[n][target] = method;
        return method;
    }
}
