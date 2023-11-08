package com.xiexinhai8.base_alg.back_trace.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-11-08
 */
class Combine {
    List<List<Integer>> result = new ArrayList<>();
    int[] candidate;
    /**
     * 以答案（输出）为基准， 构造组合对， 每次选择一个元素进行输出构造
     */
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        candidate = new int[n];
        for (int i = 1; i <= n; i++) {
            candidate[i - 1] = i;
        }
        bt(0, k, 0, path);

        return result;
    }

    private void bt(int level, int target, int start, List<Integer> path) {
        if (level >= target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidate.length; i++) {
            path.add(candidate[i]);
            bt(level + 1, target, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 以输入数据为基准， 每次操作考虑是否选择这个输入为输出
     */
    public List<List<Integer>> combine1(int n, int k) {
        List<Integer> path = new ArrayList<>();
        candidate = new int[n];
        for (int i = 1; i <= n; i++) {
            candidate[i - 1] = i;
        }

        bt(0, k, path);

        return result;
    }

    /**
     *
     * @param curIndex 当前候选元素
     * @param target 结果个数
     * @param path 临时结果数
     */
    private void bt(int curIndex, int target, List<Integer> path) {
        if (path.size() == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (path.size() + candidate.length - curIndex < target) {
            return;
        }

        bt(curIndex + 1, target, path);
        path.add(candidate[curIndex]);
        bt(curIndex + 1, target, path);
        path.remove(path.size() - 1);
    }
}
