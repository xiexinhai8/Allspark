package com.xiexinhai8.base_alg.array.prefix;

import java.util.HashSet;
import java.util.Set;

/**
 * 2559. 统计范围内的元音字符串数
 *
 * 给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
 *
 * 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
 *
 * 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
 *
 * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-06-02
 */
public class VowelStrings {

    public int[] vowelStrings0(String[] words, int[][] queries) {
        /** 思路: 实用动态规划词表辅助查询
         1、扫描words数组, 标记出是否以元音开头
         2、计算词表, 按步长开始计算
         3、遍历queries, 返回元音word数量
         返回 结果数组

         问题：内存耗费比较多, 可以采取前缀和的方式进行优化
         */
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        Set<Character> vowelSet = new HashSet();
        for (char vowel : vowels) {
            vowelSet.add(vowel);
        }
        boolean[] tags = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            if (vowelSet.contains(words[i].charAt(0)) && vowelSet.contains(words[i].charAt(words[i].length() - 1))) {
                tags[i] = true;
            }
            //System.out.print(tags[i] + ", ");
        }
        //System.out.println();

        int[][] d = new int[words.length][words.length];
        for (int i = 0; i < words.length; i++) {

            for (int j = 0; j < words.length; j++) {
                if (j >= i) {
                    if (j == i) {
                        if (tags[j]) {
                            d[i][j] = 1;
                        }
                    } else {
                        if (tags[j]) {
                            d[i][j] = d[i][j - 1] + 1;
                        } else {
                            d[i][j] = d[i][j - 1];
                        }
                    }
                }

                //System.out.print(d[i][j] + ", ");
            }
            //System.out.println();
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = d[queries[i][0]][queries[i][1]];
        }

        return result;
    }

    public int[] vowelStrings1(String[] words, int[][] queries) {
        /** 思路: 实用动态规划词表辅助查询
         1、扫描words数组, 标记出是否以元音开头
         2、计算词表, 按步长开始计算
         3、遍历queries, 返回元音word数量
         返回 结果数组
         */
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        Set<Character> vowelSet = new HashSet();
        for (char vowel : vowels) {
            vowelSet.add(vowel);
        }
        boolean[] tags = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            if (vowelSet.contains(words[i].charAt(0)) && vowelSet.contains(words[i].charAt(words[i].length() - 1))) {
                tags[i] = true;
            }
            //System.out.print(tags[i] + ", ");
        }
        //System.out.println();

        int[] d = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                if (tags[i]) {
                    d[i] = 1;
                }
            } else {
                if (tags[i]) {
                    d[i] = d[i - 1] + 1;
                } else {
                    d[i] = d[i - 1];
                }
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = d[queries[i][1]];
            if (queries[i][0] > 0) {
                result[i] -= d[queries[i][0] - 1];
            }
        }

        return result;
    }
}
