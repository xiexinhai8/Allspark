package com.xiexinhai8.base_alg.dynamic.combination;

import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 *
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 * @author xxh
 * Created on 2024-05-22
 */
public class CombinationSum4 {

    /**
     * 动态规划
     * 此问题求的是组合数且需要考虑顺序, 可以对比 518. 零钱兑换 II, 看看考虑组合顺序和不考虑组合顺序的区别
     * f(i) 代表的是target = i的组合数
     * f(i) = sum(f(i - nums[j])) 其中 j in (0, nums.length)
     * f(0) = 1
     */
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] f = new int[target + 1];
        f[0] = 1;

        for (int i = 1; i <= target; i++) {

            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    f[i] += f[i - nums[j]];
                } else {
                    break;
                }
            }
        }

        return f[target];
    }
}
