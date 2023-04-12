package com.xiexinhai8.base_alg.dynamic;

/**
 * 1147. 段式回文
 *
 * 你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足:
 *
 * subtexti 是 非空 字符串
 * 所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text )
 * 对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立
 * 返回k可能最大值。
 *
 * 我使用的是动态规划
 * 参考： https://leetcode.cn/problems/longest-chunked-palindrome-decomposition/solutions/2219964/duan-shi-hui-wen-by-leetcode-solution-vanl/
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-12
 */
public class LongestDecomposition {

    public int longestDecomposition(String text) {

        return longest(text, 0, text.length() - 1);

    }

    public int longest(String text, int start, int end) {
        if (start > end) {
            return 0;
        }
        for (int step = 0; step <= (end - start + 1) / 2 && (start + step) < end - step; step++) {
            if (text.substring(start, start + step + 1).equals(text.substring(end - step, end + 1))) {
                // System.out.println(text.substring(start, start + step + 1) + " start = " + start + " end = " + (start + step + 1));
                return longest(text, start + step + 1, end  - step - 1) + 2;
            }
        }

        return 1;
    }
}
