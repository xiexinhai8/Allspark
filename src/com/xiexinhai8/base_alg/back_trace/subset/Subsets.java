package com.xiexinhai8.base_alg.back_trace.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * @author xxh
 * Created on 2023-12-23
 */
public class Subsets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        List<Integer> path = new ArrayList<>();

        dfs(0, nums, path);

        return result;
    }

    private void dfs(int index, int[] nums, List<Integer> path) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        dfs(index + 1, nums, path);
        path.add(nums[index]);
        dfs(index + 1, nums, path);
        path.remove(path.size() - 1);
    }
}
