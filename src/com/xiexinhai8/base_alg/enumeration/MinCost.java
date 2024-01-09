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

    public long minCost(int[] nums, int x) {
        long minCost = -1;

        for (int i = 0; i < nums.length; i++) {
            long curCost = calCost(nums, i, x);
            if (minCost == -1 || minCost > curCost) {
                minCost = curCost;
            }
        }

        return minCost;
    }

    private long calCost(int[] nums, int optNum, int optCost) {
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
