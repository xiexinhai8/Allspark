package com.xiexinhai8.base_alg.dynamic;

/**
 * 134. 加油站
 *
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * @author xxh
 * Created on 2024-04-03
 */
public class IsInterleave {

    public static void main(String[] args) {
        String s1 = "cbcccbabbccbbcccbbbcabbbabcababbbbbbaccaccbabbaacbaabbbc";
        String s2 = "abcbbcaababccacbaaaccbabaabbaaabcbababbcccbbabbbcbbb";
        String s3 = "abcbcccbacbbbbccbcbcacacbbbbacabbbabbcacbcaabcbaaacbcbbbabbbaacacbbaaaabccbcbaabbbaaabbcccbcbabababbbcbbbcbb";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println(new IsInterleave().isInterleave_dp2(s1, s2, s3));
        }
        System.out.println("take time: " + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * s1 + s2 = s3
     * f(s1, s2) = f(s1 - 1, s2) || f(s1, s2 - 1)
     */
    public boolean isInterleave_dp2(String s1, String s2, String s3) {
        int s1l = s1.length();
        int s2l = s2.length();
        int s3l = s3.length();

        if (s3l != s1l + s2l) {
            return false;
        }
        boolean[][] f = new boolean[s1l + 1][s2l + 1];
        f[0][0] = true;

        for (int i = 0; i <= s1l; i++) {
            for (int j = 0; j <= s2l; j++) {
                if (i == 0 && j == 0) {
                    f[i][j] = true;
                    continue;
                }
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    f[i][j] = f[i - 1][j];
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    f[i][j] = f[i][j] || f[i][j - 1];
                }
            }
        }
        return f[s1l][s2l];

    }

    /**
     * s1 + s2 = s3
     * f(s3, s1, s3 - s1) = f(s3 - 1, s1 - 1, s3 - s1) || f(s3 - 1, s1, s3 - s1 - 1)
     */
    public boolean isInterleave_dp1(String s1, String s2, String s3) {
        int s1l = s1.length();
        int s2l = s2.length();
        int s3l = s3.length();

        if (s3l != s1l + s2l) {
            return false;
        }
        boolean[][][] f = new boolean[s3l + 1][s1l + 1][s2l + 1];
        f[0][0][0] = true;

        for (int i = 1; i <= s3.length(); i++) {
            for (int j = 0; j <= i && j <= s1l; j++) {
                if (j > 0 && i - j <= s2l && s1.charAt(j - 1) == s3.charAt(i - 1)) {
                    f[i][j][i - j] = f[i][j][i - j] || f[i - 1][j - 1][i - j];
                }
                if (i - j > 0 && i - j <= s2l && s2.charAt(i - j - 1) == s3.charAt(i - 1)) {
                    f[i][j][i - j] = f[i][j][i - j] || f[i - 1][j][i - j - 1];
                }
            }
        }
        return f[s3l][s1l][s2l];

    }

    // 采用回溯做法： 能通过测试， 但是计算量会很大
    public boolean isInterleave_bt(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return bt(s1, s2, s3);
    }
    private boolean bt(String s1, String s2, String s3) {
        if (s3.length() == 0) {
            return true;
        }
        boolean s1Result = false;
        boolean s2Result = false;

        if (s1.length() > 0 && s1.charAt(0) == s3.charAt(0)) {
            s1Result = bt(s1.substring(1, s1.length()), s2, s3.substring(1, s3.length()));
        }

        if (s2.length() > 0 && s2.charAt(0) == s3.charAt(0)) {
            s2Result = bt(s1, s2.substring(1, s2.length()), s3.substring(1, s3.length()));
        }

        return s1Result || s2Result;
    }

    // 这里没法采用贪心做法， 原因是因为使用贪心后可能错过正确解
    public boolean isInterleave_wrong(String s1, String s2, String s3) {
        if (s3.length() == 0) {
            return true;
        }
        int cur = 0;
        boolean s1Result = false;
        boolean s2Result = false;
        while (s1.charAt(cur) == s3.charAt(cur)) {
            cur++;
        }
        if (cur > 0) {
            s1Result = isInterleave_wrong(s1.substring(cur, s1.length()), s2, s3.substring(cur, s3.length()));
        }
        cur = 0;
        while (s2.charAt(cur) == s3.charAt(cur)) {
            cur++;
        }
        if (cur > 0) {
            s2Result = isInterleave_wrong(s2.substring(cur, s2.length()), s2, s3.substring(cur, s3.length()));
        }

        return s1Result || s2Result;
    }
}
