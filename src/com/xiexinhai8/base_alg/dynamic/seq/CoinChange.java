package com.xiexinhai8.base_alg.dynamic.seq;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 * @author xxh
 * Created on 2022-07-24
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(new CoinChange().coinChange_dp(coins, 11));
    }

    /**
     * 问题: 序列DP
     * 限制: coins数量不限, 凑成金额的最少硬币数
     * 假设 f(n) 为凑成金额n的最小硬币数, 则有 f(n) = min{f(n - coins[k])} + 1
     * 其中f(0) = 0;
     */
    public int coinChange_dp(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        Arrays.sort(coins);
        f[0] = 0;

        for (int i = coins[0]; i <= amount; i++) {

            for (int k = 0; k < coins.length; k++) {
                if (i >= coins[k]) {
                    f[i] = Math.min(f[i], f[i - coins[k]] + 1);
                }
            }
        }

        return f[amount] == Integer.MAX_VALUE / 2 ? -1 : f[amount];
    }

    /**
     * 问题: 记忆化搜索
     * dfs(amount) = min{dfs(amount - coins[i]) + 1}
     * dfs(0) = 0;
     */
    int DEFAULT_VALUE = Integer.MAX_VALUE / 2;

    public int coinChange_bt(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, -1);
        minCoins[0] = 0;
        return dfs(amount, minCoins, coins) == DEFAULT_VALUE ? -1 : minCoins[amount];
    }

    private int dfs(int amount, int[] minCoins, int[] coins) {
        if (amount < 0) {
            return DEFAULT_VALUE;
        }
        if (minCoins[amount] != -1) {
            return minCoins[amount];
        }
        int coinNum = DEFAULT_VALUE;
        for (int i = 0; i < coins.length; i++) {
            coinNum = Math.min(coinNum, dfs(amount - coins[i], minCoins, coins) + 1);
        }
        minCoins[amount] = coinNum;
        return minCoins[amount];
    }
}
