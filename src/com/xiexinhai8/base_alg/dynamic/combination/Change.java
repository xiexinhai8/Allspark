package com.xiexinhai8.base_alg.dynamic.combination;

/**
 * 518. 零钱兑换 II
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 * @author xxh
 * Created on 2024-05-22
 */
public class Change {

    /**
     * 动态规划
     * 此问题求的是组合数无需考虑顺序, 可以对比 377. 组合总和 Ⅳ, 看看考虑组合顺序和不考虑组合顺序的区别
     *
     * 在这里我们使用的方法是假设我们只有一种硬币时, 凑齐各个金额有多少种组合; 这里毫无疑问, 如果只有一种硬币, 无论金额为多少, 要么有一种组合方式,
     * 要么为0种
     *
     * 逐步增加硬币的种类, 我们把能够当前增加的硬币能够带来的组合方案加入之前已有的组合方案, 就能得到最终的组合方案数
     */
    public int change(int amount, int[] coins) {
        int[] f = new int[amount + 1];
        f[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            // 每次开始的位置应该是当前硬币大小处(因为只有当前金额大于等于当前硬币面额才能增加组合方案); 可以通过这个特征进行剪枝
            for (int j = coins[i]; j <= amount; j++) {
                f[j] += f[j - coins[i]];
            }
        }

        return f[amount];

    }
}
