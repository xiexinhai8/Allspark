package com.xiexinhai8.base_alg.bitwise;

import java.util.HashSet;
import java.util.Set;

/**
 * 1684. 统计一致字符串的数目
 *
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
 * 请你返回 words 数组中 一致字符串 的数目。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-08
 */
public class CountConsistentStrings {

    /**
     * 使用位运算
     * @param allowed
     * @param words
     * @return
     */
    public int countConsistentStrings(String allowed, String[] words) {
        int mask = 0;
        for (int i = 0; i < allowed.length(); i++) {
            char c = allowed.charAt(i);
            mask |= 1 << (c - 'a');
        }
        int res = 0;
        for (String word : words) {
            int mask1 = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                mask1 |= 1 << (c - 'a');
            }
            if ((mask1 | mask) == mask) {
                res++;
            }
        }
        return res;
    }


    public int countConsistentStrings1(String allowed, String[] words) {
        Set<Character> allowedSet = new HashSet<>();

        for (int i = 0; i < allowed.length(); i++) {
            allowedSet.add(allowed.charAt(i));
        }

        int consistentNum = 0;
        for (int i = 0; i < words.length; i++) {
            boolean consistent = true;
            for (int j = 0; j < words[i].length(); j++) {
                if (!allowedSet.contains(words[i].charAt(j))) {
                    consistent = false;
                }
            }
            if (consistent) {
                consistentNum++;
            }
        }
        return consistentNum;
    }
}
