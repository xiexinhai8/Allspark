package com.xiexinhai8.base_alg.enumeration;

/**
 * 2735. 收集巧克力
 *
 * 给你一个长度为 n、下标从 0 开始的整数数组 nums，nums[i] 表示收集位于下标 i 处的巧克力成本。每个巧克力都对应一个不同的类型，最初，位于下标 i 的巧克力就对应第 i 个类型。
 *
 * 在一步操作中，你可以用成本 x 执行下述行为：
 *
 * 同时修改所有巧克力的类型，将巧克力的类型 ith 修改为类型 ((i + 1) mod n)th。
 * 假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-09
 */
public class MinCost {
    /**
     * 由于最后一次移动n - 1次的操作和前面移动的操作有重复操作的问题， 可以采取空间换时间的方式来计算每次移动的成本
     *
     * 1、定义一个操作成本记录数组
     * 2、每次操作往对应数组中增加操作成本
     */
    public long minCost(int[] nums, int x) {
        long[] cost = new long[nums.length];

        for (int optNum = 0; optNum < nums.length; optNum++) {
            cost[optNum] += (1L * optNum * x);
        }

        for (int i = 0; i < nums.length; i++) {
            int curCost = nums[i];
            cost[0] += curCost;
            for (int optNum = 1; optNum < nums.length; optNum++) {
                curCost = Math.min(curCost, nums[(i + optNum) % nums.length]);
                cost[optNum] += curCost;
            }
        }

        long minCost = cost[0];
        for (int i = 1; i < nums.length; i++) {
            minCost = Math.min(minCost, cost[i]);
        }
        return minCost;
    }

    /**
     * 采取枚举移动次数的方式确定最小的收集成本， 时间复杂度0(n^3)
     */
    public long minCost1(int[] nums, int x) {
        long minCost = -1;

        for (int i = 0; i < nums.length; i++) {
            long curCost = calCost(nums, i, x);
            if (minCost == -1 || minCost > curCost) {
                minCost = curCost;
            }
        }

        return minCost;
    }

    /**
     * 计算固定移动次数的消费
     * @param nums
     * @param optNum 操作次数， 必须是long， 否则容易溢出
     * @param optCost
     * @return
     */
    private long calCost(int[] nums, long optNum, int optCost) {
        long cost = optNum * optCost;

        for (int i = 0; i < nums.length; i++) {
            long curCost = nums[i];
            for (int j = 1; j <= optNum; j++) {
                curCost = Math.min(curCost, nums[(i + j) % nums.length]);
            }
            cost += curCost;
        }

        return cost;
    }
}
