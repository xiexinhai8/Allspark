package com.xiexinhai8.base_alg.dynamic;

/**
 * @author xxh
 * Created on 2022-07-24
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        coinChange(coins, 11);
    }

    public static int coinChange(int[] coins, int amount) {
        int[] d = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            d[i] = -1;
            for (int j = 0; j < coins.length; j++) {
                int r = calMethods(1 - coins[j], d);
                if (r >= 0 && (d[i] > r + 1 || d[i] == -1)) {
                    d[i] = r + 1;
                }
            }
        }
        return d[amount];
    }

    private static int calMethods(int amount, int[] d) {
        if (amount < 0) {
            return -1;
        }
        return d[amount];
    }
}
