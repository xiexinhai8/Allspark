package com.xiexinhai8.base_alg.dynamic;

import com.xiexinhai8.base_alg.entity.TreeNode;

/**
 *
 * TODO 968. 监控二叉树
 *
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 *
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 *
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-08
 */
public class MinCameraCover {

    public int minCameraCover(TreeNode root) {

        int[] cost = dfs(root);

        return Math.min(cost[0], cost[1]) > 0 ? Math.min(cost[0], cost[1]) : 1;

    }

    private int[] dfs(TreeNode node) {
        int[] cost = new int[2];
        if (node == null) {
            return cost;
        }

        int[] leftCost = dfs(node.left);
        int[] rightCost = dfs(node.right);

        cost[0] = leftCost[1] + rightCost[1];
        if (leftCost[1] != 0 && node.right != null && rightCost[0] != 0) {
            cost[0] = Math.min(cost[0], leftCost[1] + rightCost[0]);
        }
        if (rightCost[1] != 0 && node.left != null && leftCost[0] != 0) {
            cost[0] = Math.min(cost[0], leftCost[0] + rightCost[1]);
        }

        cost[1] = Math.min(leftCost[0], leftCost[1]) + Math.min(rightCost[0], rightCost[1]) + 1;

        System.out.println("xxx" + cost[0] + " - " + cost[1]);
        return cost;
    }
}
