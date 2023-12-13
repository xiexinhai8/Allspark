package com.xiexinhai8.base_alg.two_pointers;

/**
 *
 * 2697. 字典序最小回文串
 *
 * 给你一个由 小写英文字母 组成的字符串 s ，你可以对其执行一些操作。在一步操作中，你可以用其他小写英文字母 替换  s 中的一个字符。
 *
 * 请你执行 尽可能少的操作 ，使 s 变成一个 回文串 。如果执行 最少 操作次数的方案不止一种，则只需选取 字典序最小 的方案。
 *
 * 对于两个长度相同的字符串 a 和 b ，在 a 和 b 出现不同的第一个位置，如果该位置上 a 中对应字母比 b 中对应字母在字母表中出现顺序更早，则认为 a 的字典序比 b 的字典序要小。
 *
 * 返回最终的回文字符串。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-13
 */
public class MakeSmallestPalindrome {

    public String makeSmallestPalindrome(String s) {
        int start = 0, end = s.length() - 1;

        char[] arr = s.toCharArray();
        while (start < end) {
            int flag = arr[end] - arr[start];
            if (flag > 0) {
                arr[end] = arr[start];
            } else {
                arr[start] = arr[end];
            }
            start++;
            end--;
        }

        return new String(arr);
    }
}
