package com.xiexinhai8.base_alg.dynamic.knapsack;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-11
 */
public class CoinChange {

    /** 定义动态转移方程
     f(x) = min(f(x - coins[0]),……, f(x - coins[n]))
     其中f(x) 表示凑足amount时需要的最少硬币数 (其中x>=0) */

    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        // 排序便于剪枝运算
        Arrays.sort(coins);

        for (int i = 1; i <= amount; i++) {
            f[i] = -1;

            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] < 0) {
                    break;
                }
                if (f[i - coins[j]] == -1) {
                    continue;
                }
                if (f[i] == -1) {
                    f[i] = f[i - coins[j]] + 1;
                } else {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                }
            }

        }

        return f[amount];
    }
}
