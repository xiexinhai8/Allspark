package com.xiexinhai8.base_alg.dynamic.state_compression;

import java.util.Arrays;

/**
 * 2741. 特别的排列
 *
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：
 *
 * 对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
 * 请你返回特别排列的总数目，由于答案可能很大，请将它对 109 + 7 取余 后返回。
 * @author xxh
 * Created on 2024-07-05
 */
public class SpecialPerm {

    /**
     * 状压DP
     * f(pre, S) = sum(f(k, s / k))
     * 其中 f(x, 0) = 1;
     */
    int MODEL = (int) 1e9 + 7;
    public int specialPerm(int[] nums) {
        int n = nums.length;
        int cn = (1 << n);
        int[][] f = new int[n][cn];
        for (int i = 0; i < n; i++) {
            f[i][0] = 1;
        }
        /**
         // pre
         for (int i = 0; i < nums.length; i++) {
         // count状压
         for (int j = 1; j < cn; j++) {
         if ((j & (1 << i)) > 0) {
         continue;
         }
         // 遍历待排列集合
         for (int k = 0; k < nums.length; k++) {
         if ((j & (1 << k)) > 0 && (nums[i] % nums[k] == 0 || nums[k] % nums[i] == 0)) {
         f[i][j] += f[k][j - (1 << k)];
         }
         }
         }
         }
         */
        for (int count = 1; count <= cn; count++) {

            for (int pre = 0; pre < nums.length; pre++) {
                if ((count & (1 << pre)) > 0) {
                    continue;
                }
                for (int i = 0; i < nums.length; i++) {
                    if ((count & (1 << i)) == 0 || (nums[pre] % nums[i] != 0 && nums[i] % nums[pre] != 0)) {
                        continue;
                    }
                    f[pre][count] += f[i][count - (1 << i)];
                    f[pre][count] %= MODEL;
                }
            }
        }

        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += f[i][cn - (1 << i) - 1];
            total %= MODEL;
        }

        return total;
    }

    /**
     * 回溯
     * total = sum{dfs(s / i, i)}
     */
    public int specialPerm_bt(int[] nums) {
        int[][] f = new int[nums.length][(1 << nums.length) - 1];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(f[i], -1);
        }
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += dfs(i, 1 << i, nums, f);
            total %= MODEL;
        }

        return total;
    }

    private int dfs(int pre, int count, int[] nums, int[][] f) {
        if (count >= ((1 << nums.length) - 1)) {
            return 1;
        }

        if (f[pre][count] != -1) {
            return f[pre][count];
        }

        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((count & (1 << i)) > 0
                    || (nums[pre] % nums[i] != 0 && nums[i] % nums[pre] != 0)) {
                continue;
            }
            total += dfs(i, count + (1 << i), nums, f);
            total %= MODEL;
        }

        f[pre][count] = total;
        return total;
    }
}
