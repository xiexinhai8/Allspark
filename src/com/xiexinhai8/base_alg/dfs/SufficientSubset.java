package com.xiexinhai8.base_alg.dfs;

import com.xiexinhai8.base_alg.entity.TreeNode;

/**
 * 1080. 根到叶路径上的不足节点
 *
 * 给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
 *
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
 *
 * 叶子节点，就是没有子节点的节点。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-22
 */
public class SufficientSubset {

    public TreeNode sufficientSubset1(TreeNode root, int limit) {


        int nodeTotal = dfs(root, 0, limit);

        return nodeTotal < limit ? null : root;
    }

    private int dfs(TreeNode root, int cur, int limit) {
        if (root == null) {
            return cur;
        }

        cur += root.val;

        if (root.left != null && root.right != null) {
            // 取最大的一路
            int nodeTotal = dfs(root.left, cur, limit);
            if (nodeTotal < limit) {
                root.left = null;
            }

            int curTotal = dfs(root.right, cur, limit);
            if (curTotal > nodeTotal) {
                nodeTotal = curTotal;
            }
            if (curTotal < limit) {
                root.right = null;
            }
            return nodeTotal;
        } else if (root.left == null) {
            int nodeTotal = dfs(root.right, cur, limit);
            if (nodeTotal < limit) {
                root.right = null;
            }
            return nodeTotal;
        } else {
            int nodeTotal = dfs(root.left, cur, limit);
            if (nodeTotal < limit) {
                root.left = null;
            }
            return nodeTotal;
        }

    }


    public TreeNode sufficientSubset2(TreeNode root, int limit) {
        boolean haveSufficient = checkSufficientLeaf(root, 0, limit);
        return haveSufficient ? root : null;
    }

    public boolean checkSufficientLeaf(TreeNode node, int sum, int limit) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return node.val + sum >= limit;
        }
        boolean haveSufficientLeft = checkSufficientLeaf(node.left, sum + node.val, limit);
        boolean haveSufficientRight = checkSufficientLeaf(node.right, sum + node.val, limit);
        if (!haveSufficientLeft) {
            node.left = null;
        }
        if (!haveSufficientRight) {
            node.right = null;
        }
        return haveSufficientLeft || haveSufficientRight;
    }

    /**
     *
     * @param root
     * @param limit
     * @return
     */
    public TreeNode sufficientSubset3(TreeNode root, int limit) {

        int nodeTotal = dfs3(root, 0, limit);

        return nodeTotal < limit ? null : root;
    }

    private int dfs3(TreeNode root, int cur, int limit) {
        if (root.left == null && root.right == null) {
            return cur + root.val;
        }

        cur += root.val;

        int leftMaxPath = Integer.MIN_VALUE;
        int rightMaxPath = Integer.MIN_VALUE;
        if (root.left != null) {
            leftMaxPath = dfs(root.left, cur, limit);
            if (leftMaxPath < limit) {
                root.left = null;
            }
        }

        if (root.right != null) {
            rightMaxPath = dfs(root.right, cur, limit);
            if (rightMaxPath < limit) {
                root.right = null;
            }
        }

        int maxPath = Math.max(leftMaxPath, rightMaxPath);

        return maxPath;
    }

}
