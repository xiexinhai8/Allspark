package com.xiexinhai8.base_alg.bin_search;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 792. 匹配子序列的单词数
 *
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 *
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 *
 * 例如， “ace” 是 “abcde” 的子序列。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-17
 */
public class NumMatchingSubseq {

    public static void main(String[] args) {
        String s = "dsahjpjauf";
        String[] words = {"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"};

        String s1 = "abcde";
        String[] words1 = {"a","bb","acd","ace"};
        System.out.println(new NumMatchingSubseq().numMatchingSubseq(s1, words1));
    }

    public int numMatchingSubseq(String s, String[] words) {
        char[] sc = s.toCharArray();
        Map<Character, TreeSet<Integer>> indexMap = new HashMap<>();

        for (int i = 0; i < sc.length; i++) {
            indexMap.putIfAbsent(sc[i], new TreeSet<>());
            indexMap.get(sc[i]).add(i);
        }

        // System.out.println(indexMap);
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Integer cur = -1;
            System.out.println(word);
            for (int j = 0; j < word.length(); j++) {
                cur = findChar(indexMap, cur, word.charAt(j));
                if (cur == null) {
                    break;
                }
            }
            if (cur != null) {
                count++;
            }
        }

        return count;
    }

    private Integer findChar(Map<Character, TreeSet<Integer>> indexMap, Integer cur, char c) {
        TreeSet<Integer>  l = indexMap.get(c);
        if (l == null) {
            return null;
        }
        Integer next = l.ceiling(cur);
        System.out.println("c: " + c + " index: " + next + " l: " + l);
        // 由于treeset ceiling 返回的是>=的数据， 所以需要返回next + 1
        return next == null ? null : next + 1;
    }
}
