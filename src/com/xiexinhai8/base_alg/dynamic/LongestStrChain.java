package com.xiexinhai8.base_alg.dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1048. 最长字符串链
 *
 * 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
 *
 * 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
 *
 * 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
 *
 * 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-27
 */
public class LongestStrChain {

    public int longestStrChain(String[] words) {
        /** 方案一: 不可行, 最长链可能在中间位置
         1、通过单词长度从小到大分组
         2、根据分组进行遍历, 找出链最长的

         f
         */
        int longest = 1;
        // 先排序
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());

        Map<String, Integer> chainFun = new HashMap<>();
        int start = 0, minSize = words[0].length();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > minSize && start == 0) {
                start = i;
            }
            chainFun.put(words[i], 1);
        }
        // 确定转移方程: f(word) = max{f(word_i`)} + 1
        for (int i = start; i < words.length; i++) {

            for (int j = 0; j < words[i].length(); j++) {
                int tmpLongest = Math.max(chainFun.get(words[i]), chainFun.getOrDefault(getFront(words[i], j), 0) + 1);
                chainFun.put(words[i], tmpLongest);
                if (tmpLongest > longest) {
                    longest = tmpLongest;
                }
            }
        }

        // 返回 longest
        return longest;
    }

    private String getFront(String word, int split) {
        return word.substring(0, split) + word.substring(split + 1, word.length());
    }
}
