package com.xiexinhai8.base_alg.dynamic;

/**
 * 2645. 构造有效字符串的最少插入数
 *
 * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
 *
 * 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-11
 */
public class AddMinimum {

    /**
     a[i] > a[i - 1]: f(i) = f(i - 1) - 1
     a[i]<= a[i - 1]: f(i) = f(i - 1) + 2
     */
    public int addMinimum(String word) {
        int[] f = new int[word.length()];
        f[0] = 2;

        for (int i = 1; i < word.length(); i++) {
            char pre = word.charAt(i - 1);
            char cur = word.charAt(i);
            if (cur - pre > 0) {
                f[i] = f[i - 1] - 1;
            } else {
                f[i] = f[i - 1] + 2;
            }
        }
        return f[word.length() - 1];
    }
}
