package com.xiexinhai8.base_alg.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1156. 单字符重复子串的最大长度
 *
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 * @author xxh
 * Created on 2023-06-03
 */
public class MaxRepOpt1 {

    public static void main(String[] args) {
        String text = "aabba";
        System.out.println(new MaxRepOpt1().maxRepOpt1(text));
    }

    public int maxRepOpt1(String text) {
        /** 思路: 先统计各字符的区间, 再遍历各字符计算调整后的最长长度
         1、统计各字符区间 (注意最后需要扫尾）
         2、遍历字符, 尝试调整, 找出每个字符的最长调整长度
            2.1、将最长调整长度初始化为当前区间长度
            2.2、如果有下个区间 && 下个区间和当前区间间隔一个字符，最长调整长度 = 最长调整长度 + 下个区间长度
                2.2.1、 如果至少有三个区间 最长调整长度 = 最长调整长度 + 1
            2.3、如果下个区间无法接上 && 至少有两个区间 最长调整长度 = 最长调整长度 + 1
         3、返回最长长度
         */
        int maxRepLength = 0;
        Map<Character, List<int[]>> charRanges = new HashMap<>();
        char pre = text.charAt(0);
        char cur = pre;
        int counter = 0;
        for (int i = 0; i < text.length(); i++) {
            cur = text.charAt(i);
            if (cur == pre) {
                counter++;
            } else {
                charRanges.putIfAbsent(pre, new ArrayList<>());
                List<int[]> ranges = charRanges.get(pre);
                ranges.add(new int[]{i - counter, i - 1});

                pre = cur;
                counter = 1;
            }
        }
        charRanges.putIfAbsent(cur, new ArrayList<>());
        List<int[]> x = charRanges.get(cur);
        x.add(new int[]{text.length() - counter, text.length() - 1});

        for (List<int[]> ranges : charRanges.values()) {

            for (int i = 0; i < ranges.size(); i++) {
                int[] curRange = ranges.get(i);
                int maxTune = curRange[1] - curRange[0] + 1;

                int[] nextRange = null;
                if (i + 1 < ranges.size()) {
                    nextRange = ranges.get(i + 1);
                }
                if (nextRange != null && nextRange[0] - 2 == curRange[1]) {
                    maxTune = maxTune + (nextRange[1] - nextRange[0] + 1);
                    if (ranges.size() > 2) {
                        maxTune += 1;
                    }
                } else {
                    if (ranges.size() > 1) {
                        maxTune += 1;
                    }
                }
                if (maxTune > maxRepLength) {
                    maxRepLength = maxTune;
                }
            }
        }

        return maxRepLength;
    }
}
