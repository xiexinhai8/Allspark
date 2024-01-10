package com.xiexinhai8.base_alg.dynamic;

/**
 * 583. 两个字符串的删除操作
 *
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 *
 * 每步 可以删除任意一个字符串中的一个字符。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-10
 */
public class DeleteOperationforTwoStrings {

    /**
     f(i, j)
     word1[i] == word2[j]  f(i, j) = f(i - 1, j - 1);
     word1[i] != word2[j]  f(i, j) = min{f[i - 1][j], f[i][j - 1]} + 1
     */
    public int minDistance(String word1, String word2) {
        int[][] f = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < f.length; i++) {
            f[i][0] = i;
        }
        for (int i = 0; i < f[0].length; i++) {
            f[0][i] = i;
        }

        for (int i = 0; i < word1.length(); i++) {

            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    f[i + 1][j + 1] = f[i][j];
                } else {
                    f[i + 1][j + 1] = Math.min(f[i][j + 1], f[i + 1][j]) + 1;
                }
            }
        }

        return f[word1.length()][word2.length()];
    }
}
