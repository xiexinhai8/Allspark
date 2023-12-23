package com.xiexinhai8.base_alg.back_trace.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 *
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * @author xxh
 * Created on 2023-12-23
 */
public class CombinationSum3 {

    int[] eles = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int maxLevel;
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        maxLevel = k;

        bt(0, 0, n);

        return result;
    }

    private void bt(int level, int total, int target) {


        if (path.size() == maxLevel && total == target) {
            result.add(new ArrayList(path));
            return;
        }

        if (level >= eles.length || path.size() >= maxLevel || total > target) {
            return;
        }

        bt(level + 1, total, target);
        path.add(eles[level]);
        bt(level + 1, total + eles[level], target);
        path.remove(path.size() - 1);
    }}
