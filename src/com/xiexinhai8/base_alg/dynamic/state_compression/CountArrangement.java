package com.xiexinhai8.base_alg.dynamic.state_compression;

import java.util.Arrays;

/**
 * 526. 优美的排列
 *
 * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
 *
 * perm[i] 能够被 i 整除
 * i 能够被 perm[i] 整除
 * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
 * @author xxh
 * Created on 2024-07-05
 */
public class CountArrangement {

    /**
     * 状压DP
     * dfs(n) = sum{dfs(n - 1)}
     */
    public int countArrangement(int n) {
        int cn = (1 << n);
        int[] f = new int[cn];
        f[0] = 1;

        for (int i = 1; i < cn; i++) {
            int pos = n - Integer.bitCount(i) + 1;
            for (int j = 1; j <= n; j++) {
                if ((i & (1 << (j - 1))) == 0 || (pos % j != 0 && j % pos != 0)) {
                    continue;
                }
                f[i] += f[i ^ (1 << (j - 1))];
            }
        }

        return f[(1 << n) - 1];
    }


    /**
     * 回溯
     */
    public int countArrangement_bt(int n) {
        int[] f = new int[(1 << n) - 1];
        Arrays.fill(f, -1);
        return dfs(0, n, f);
    }

    private int dfs(int count, int n, int[] f) {
        if (count >= ((1 << n) - 1)) {
            return 1;
        }

        if (f[count] != -1) {
            return f[count];
        }

        int curPos = Integer.bitCount(count) + 1;
        /**
         * int curPos = 1;
         * int tmp = count;
         * while (tmp > 0) {
         * if ((tmp & 1) > 0) {
         * curPos++;
         * }
         * tmp >>= 1;
         * }
         */
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if ((count & (1 << (i - 1))) > 0
                    || (i % curPos != 0 && curPos % i != 0)) {
                continue;
            }
            num += dfs(count + (1 << (i - 1)), n, f);
        }

        f[count] = num;
        return num;
    }
}
