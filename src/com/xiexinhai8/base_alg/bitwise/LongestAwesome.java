package com.xiexinhai8.base_alg.bitwise;

import java.util.Arrays;

/**
 * 1542. 找出最长的超赞子字符串
 *
 * 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
 *
 * 「超赞子字符串」需满足满足下述两个条件：
 *
 * 该字符串是 s 的一个非空子字符串
 * 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
 * @author xxh
 * Created on 2024-05-20
 */
public class LongestAwesome {
    /**
     * 前缀数组 + 状态压缩
     */
    int RANGE = 10;
    public int longestAwesome(String s) {
        int prefix = 0;

        int[] status = new int[1 << RANGE];
        Arrays.fill(status, s.length());
        status[0] = -1;

        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            prefix = prefix ^ (1 << (s.charAt(i) - '0'));

            int len = 1;
            for (int j = 0; j < RANGE; j++) {
                len = Math.max(len, i - status[prefix ^ (1 << j)]);
            }
            len = Math.max(len, i - status[prefix]);
            longest = Math.max(len, longest);
            if (status[prefix] == s.length()) {
                status[prefix] = i;
            }
        }

        return longest;
    }

    /**
     * 前缀数组, 时间复杂度0(n^2)
     */
    public int longestAwesome1(String s) {
        int[] prefix = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            prefix[i + 1] = prefix[i] ^ (1 << (s.charAt(i) - '0'));
        }

        int maxLen = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i + 1 <= maxLen) {
                break;
            }
            for (int j = 0; j <= i; j++) {
                if (i - j + 1 <= maxLen) {
                    break;
                }
                if (valid(prefix[i + 1] ^ prefix[j])) {
                    maxLen = i - j + 1;
                    break;
                }
            }
        }
        return maxLen;
    }

    private boolean valid(int x) {
        int count = 0;
        while (x > 0) {
            if (count > 0) {
                return false;
            }
            if ((x & 1) > 0) {
                count++;
            }
            x >>= 1;
        }
        return true;
    }
}
