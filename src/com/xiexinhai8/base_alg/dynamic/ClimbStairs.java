package com.xiexinhai8.base_alg.dynamic;

/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-20
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f[n];
    }
}
