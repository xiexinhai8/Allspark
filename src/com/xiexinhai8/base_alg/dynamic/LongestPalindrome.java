package com.xiexinhai8.base_alg.dynamic;

/**
 * @author xxh
 * Created on 2022-07-03
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "aaaaa";
        System.out.println(longestPalindromeDynamic(s));
    }

    public static String longestPalindrome(String s) {
        // char[] chars = s.toCharArray();
        int maxLength = 1;
        String longestPalindrome = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            int length = 1;
            int start = i;
            int end = i;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    length++;
                    end++;
                } else {
                    break;
                }
            }
            for(; end + 1 < s.length() && start - 1 >= 0;) {
                if (s.charAt(end + 1) == s.charAt(start - 1)) {
                    length += 2;
                    start = start - 1;
                    end = end + 1;
                } else {
                    break;
                }
            }
            if (length > maxLength) {
                maxLength = length;
                longestPalindrome = s.substring(start, end + 1);
            }
        }
        return longestPalindrome;
    }

    public static String longestPalindromeDynamic(String s) {

        boolean[][] d = new boolean[s.length()][s.length()];

        int length = 1;
        String longestPalindrome = s.substring(0,1);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                d[i][j] = false;
                if (i == j) {
                    d[i][j] = true;
                }
                if (j == i + 1 && s.charAt(i) == s.charAt(j)) {
                    d[i][j] = true;
                    length = 2;
                    longestPalindrome = s.substring(i, j+1);
                }
            }
        }

        for (int i = 0; i < s.length(); i++) {

            for (int j = i + 2; j < s.length(); j++) {
                d[i][j] = d[i+1][j-1] && (s.charAt(i) == s.charAt(j));
                if (d[i][j] && j - i + 1 > length) {
                    length = j - i + 1;
                    longestPalindrome = s.substring(i, j + 1);
                }
            }
        }
        return longestPalindrome;
    }
}
