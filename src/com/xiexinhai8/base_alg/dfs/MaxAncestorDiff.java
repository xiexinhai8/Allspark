package com.xiexinhai8.base_alg.dfs;

import com.xiexinhai8.base_alg.entity.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 *
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 *
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-18
 */
public class MaxAncestorDiff {

    int maxDiff = Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {

        calMaxDiff(root);

        return maxDiff;
    }

    // 返回子树的最大和最小值
    private int[] calMaxDiff(TreeNode root) {
        if (root == null) {
            return null;
        }
        int rootValue = root.val;
        int min = rootValue;
        int max = rootValue;

        int[] pair = calMaxDiff(root.left);
        if (pair != null) {
            min = Math.min(min, pair[0]);
            max = Math.max(max, pair[1]);
            if (Math.abs(pair[0] - rootValue) > maxDiff) {
                maxDiff = Math.abs(pair[0] - rootValue);
            }
            if (Math.abs(pair[1] - rootValue) > maxDiff) {
                maxDiff = Math.abs(pair[1] - rootValue);
            }
        }

        pair = null;
        pair = calMaxDiff(root.right);
        if (pair != null) {
            min = Math.min(min, pair[0]);
            max = Math.max(max, pair[1]);
            if (Math.abs(pair[0] - rootValue) > maxDiff) {
                maxDiff = Math.abs(pair[0] - rootValue);
            }
            if (Math.abs(pair[1] - rootValue) > maxDiff) {
                maxDiff = Math.abs(pair[1] - rootValue);
            }
        }
        return new int[]{min, max};
    }
}
