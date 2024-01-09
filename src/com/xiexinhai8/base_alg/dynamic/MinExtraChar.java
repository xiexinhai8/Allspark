package com.xiexinhai8.base_alg.dynamic;

/**
 * 2707. 字符串中的额外字符
 *
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
 *
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-09
 */
public class MinExtraChar {

    public int minExtraChar(String s, String[] dictionary) {
        int[] f = new int[s.length() + 1];
        f[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            f[i + 1] = f[i] + 1;
            for (int j = 0; j < dictionary.length; j++) {
                if (postfix(s, i, dictionary[j])) {
                    f[i + 1] = Math.min(f[i + 1], f[i + 1 - dictionary[j].length()]);
                }
            }
        }

        return f[s.length()];
    }

    private boolean postfix(String s, int end, String target) {
        if (s.length() < target.length() || end + 1 < target.length()) {
            return false;
        }

        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != s.charAt(end - target.length() + 1 + i)) {
                return false;
            }
        }
        return true;
    }


}
