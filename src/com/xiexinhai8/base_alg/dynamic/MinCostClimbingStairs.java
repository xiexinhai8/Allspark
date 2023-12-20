package com.xiexinhai8.base_alg.dynamic;

/**
 * 746. 使用最小花费爬楼梯
 *
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-20
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        return Math.min(minCostClimbingStairs(cost, 0), minCostClimbingStairs(cost, 1));
    }

    /**
     init: f[start + 1] = f[start + 2] = cost[start];
     do : f[i] = min{f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]}
     */
    private int minCostClimbingStairs(int[] cost, int start) {
        if (cost.length <= start + 2) {
            return cost[start];
        }
        int[] minCost = new int[cost.length + 1];

        minCost[start + 1] = minCost[start + 2] = cost[start];
        for (int i = start + 3; i <= cost.length; i++) {
            minCost[i] = Math.min(minCost[i - 1] +  cost[i - 1], minCost[i - 2] + cost[i - 2]);
        }

        return minCost[cost.length];
    }
}
