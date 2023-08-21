package com.xiexinhai8.base_alg.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 833. 字符串中的查找与替换
 *
 * 你会得到一个字符串 s (索引从 0 开始)，你必须对它执行 k 个替换操作。替换操作以三个长度均为 k 的并行数组给出：indices, sources,  targets。
 *
 * 要完成第 i 个替换操作:
 *
 * 检查 子字符串  sources[i] 是否出现在 原字符串 s 的索引 indices[i] 处。
 * 如果没有出现， 什么也不做 。
 * 如果出现，则用 targets[i] 替换 该子字符串。
 * 例如，如果 s = "abcd" ， indices[i] = 0 , sources[i] = "ab"， targets[i] = "eee" ，那么替换的结果将是 "eeecd" 。
 *
 * 所有替换操作必须 同时 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间不会重叠 。
 *
 * 例如，一个 s = "abc" ，  indices = [0,1] ， sources = ["ab"，"bc"] 的测试用例将不会生成，因为 "ab" 和 "bc" 替换重叠。
 * 在对 s 执行所有替换操作后返回 结果字符串 。
 *
 * 子字符串 是字符串中连续的字符序列。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-08-15
 */
public class FindReplaceString {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Integer[] index = new Integer[indices.length];
        for (int i= 0; i< indices.length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return indices[o1] - indices[o2];
            }
        });
        boolean[] replace = new boolean[indices.length];
        for (int i = 0; i < index.length; i++) {
            if (indices[index[i]] + sources[index[i]].length() > s.length()) {
                continue;
            }
            boolean match = true;
            for (int j = 0, curIndex = indices[index[i]]; j < sources[index[i]].length(); j++, curIndex++) {
                if (sources[index[i]].charAt(j) != s.charAt(curIndex)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                replace[index[i]] = true;
            }
        }

        for (int i = indices.length - 1; i >= 0; i--) {
            if (replace[index[i]]) {
                s = s.substring(0, indices[index[i]]) + targets[index[i]] + s.substring(indices[index[i]] + sources[index[i]].length(), s.length());
            }
        }
        return s;
    }
}
