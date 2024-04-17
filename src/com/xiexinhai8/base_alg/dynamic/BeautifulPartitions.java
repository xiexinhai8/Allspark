package com.xiexinhai8.base_alg.dynamic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2478. 完美分割的方案数
 *
 * 给你一个字符串 s ，每个字符是数字 '1' 到 '9' ，再给你两个整数 k 和 minLength 。
 *
 * 如果对 s 的分割满足以下条件，那么我们认为它是一个 完美 分割：
 *
 * s 被分成 k 段互不相交的子字符串。
 * 每个子字符串长度都 至少 为 minLength 。
 * 每个子字符串的第一个字符都是一个 质数 数字，最后一个字符都是一个 非质数 数字。质数数字为 '2' ，'3' ，'5' 和 '7' ，剩下的都是非质数数字。
 * 请你返回 s 的 完美 分割数目。由于答案可能很大，请返回答案对 109 + 7 取余 后的结果。
 *
 * 一个 子字符串 是字符串中一段连续字符串序列。
 * @author xxh
 * Created on 2024-04-10
 */
public class BeautifulPartitions {
    int result = 0;
    int sLen;
    public static void main(String[] args) {
        String s = "7753639519183359148598823162755335682921461647796985255166979917649578972791819356618496239687361868933775339936875893219782348459522657159781118588765368954599285197845124455559747963186111993765269";
        int k = 24;
        int minLength = 2;

        new BeautifulPartitions().beautifulPartitions(s, k, minLength);
    }
    int MOD = (int) 1e9 + 7;

    /**
     * f(i, j) 代表s[0:j - 1]有多少种分割成i段的方法
     *
     * f(i, j) = sum(f(i - 1, j`)) 其中j` < j 且j - j` >= minLength, s[j` - 1]
     * 非质数/s[j`]是质数
     */
    public int beautifulPartitions(String s, int k, int minLength) {
        char[] arr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        set.add('2');
        set.add('3');
        set.add('5');
        set.add('7');
        int sLen = s.length();

        if (!set.contains(arr[0]) || set.contains(arr[sLen - 1])) {
            return 0;
        }

        int[][] f = new int[k + 1][sLen + 1];
        f[0][0] = 1;

        for (int i = 1; i <= k; i++) {
            for (int j = minLength * i; j + (k - i) * minLength <= sLen; j++) {
                if (j != sLen && !(!set.contains(arr[j - 1]) && set.contains(arr[j]))) {
                    continue;
                }
                for (int x = j - minLength; x >= (i - 1) * minLength; x--) {
                    if (x == 0 || (!set.contains(arr[x - 1]) && set.contains(arr[x]))) {
                        f[i][j] += f[i - 1][x];
                        f[i][j] %= MOD;
                    }

                }
                // System.out.print(f[i][j] + ", ");
            }
            // System.out.println();
        }

        return f[k][sLen];
    }

    // 采用回溯的方式进行探索， 时间复杂度恒高，最高高达2^n
    public int beautifulPartitions_bt(String s, int k, int minLength) {
        Set<Character> set = new HashSet<>();
        set.add('2');
        set.add('3');
        set.add('5');
        set.add('7');
        sLen = s.length();

        if (!set.contains(s.charAt(0)) || set.contains(s.charAt(s.length() - 1))) {
            return 0;
        }
        List<Integer> table = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (set.contains(s.charAt(i)) && set.contains(s.charAt(i - 1))) {
                continue;
            }
            if (set.contains(s.charAt(i))) {
                table.add(i);
            }
        }

        if (table.size() < k - 1) {
            return 0;
        }
        System.out.println(table);
        bt(0, 0, table, minLength, k - 1);

        return result;
    }

    private void bt(int startPos, int curIndex, List<Integer> table, int limit, int k) {
        if (k == 0 && sLen - startPos >= limit) {
            result++;
            return;
        }
        if (k <= 0 || curIndex >= table.size()) {
            return;
        }
        bt(startPos, curIndex + 1, table, limit, k);
        if (table.get(curIndex) - startPos >= limit) {
            bt(table.get(curIndex), curIndex + 1, table, limit, k - 1);
        }
    }
}
