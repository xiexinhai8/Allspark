package com.xiexinhai8.base_alg.back_trace;

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
}
