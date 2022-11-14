package com.xiexinhai8.base_alg.dynamic;

/**
 * @author xxh
 * Created on 2022-08-10
 */
public class LongestPalindromeSubseq {
    public static void main(String[] args) {
        String s = "abcabcabcabc";
        longestPalindromeSubseq(s);
    }

    public static int longestPalindromeSubseq(String s) {
        int sl = s.length();
        int ml = 1;
        int[][] d = new int[sl][sl];

        for (int subLen = 0; subLen < sl; subLen++) {

            for (int start = 0; start < sl - subLen; start++) {
                if (subLen == 0) {
                    d[start][start] = 1;
                    continue;
                }
                if (subLen == 1) {
                    if (s.charAt(start) == s.charAt(start + subLen)) {
                        d[start][start + subLen] = 2;
                        if (ml < 2) {
                            ml = 2;
                        }
                    }
                    continue;
                }
                if (s.charAt(start) == s.charAt(start + subLen)) {
                    d[start][start + subLen] = d[start + 1][start + subLen - 1] + 2;
                } else {
                    d[start][start + subLen] = Math.max(d[start + 1][start + subLen], d[start][start + subLen - 1]);
                }
                if (ml < d[start][start + subLen]) {
                    ml = d[start][start + subLen];
                }
            }
        }
        return ml;
    }
}
