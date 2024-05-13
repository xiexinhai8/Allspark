package com.xiexinhai8.base_alg.dynamic;

/**
 * 1553. 吃掉 N 个橘子的最少天数
 *
 * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
 *
 * 吃掉一个橘子。
 * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
 * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
 * 每天你只能从以上 3 种方案中选择一种方案。
 *
 * 请你返回吃掉所有 n 个橘子的最少天数。
 * @author xxh
 * Created on 2024-05-13
 */
public class MinDays {


    /**
     * 动态规划
     * 方法: f(i) 代表吃掉i个橘子需要的最少天数; f(1) = 1
     * 其中 f(i) = min{f(i - 1), f(i / 2) + i % 2, f(i / 3) + i % 3} + 1
     *
     * 问题: 由于橘子数量太大 n <= 2*10^9; 导致超出内存限制
     */
    public int minDays(int n) {
        int[] f = new int[n + 1];
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + 1;
            if (i % 2 == 0) {
                f[i] = Math.min(f[i], f[i / 2] + 1);
            }
            if (i % 3 == 0) {
                f[i] = Math.min(f[i], f[i / 3] + 1);
            }
        }

        return f[n];
    }
}
