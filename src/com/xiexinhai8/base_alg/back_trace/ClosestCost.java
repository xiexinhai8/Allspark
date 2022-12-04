package com.xiexinhai8.base_alg.back_trace;

/**
 * 1774. 最接近目标价格的甜点成本
 *
 * 你打算做甜点，现在需要购买配料。目前共有 n 种冰激凌基料和 m 种配料可供选购。而制作甜点需要遵循以下几条规则：
 *
 * 必须选择 一种 冰激凌基料。
 * 可以添加 一种或多种 配料，也可以不添加任何配料。
 * 每种类型的配料 最多两份 。
 * 给你以下三个输入：
 *
 * baseCosts ，一个长度为 n 的整数数组，其中每个 baseCosts[i] 表示第 i 种冰激凌基料的价格。
 * toppingCosts，一个长度为 m 的整数数组，其中每个 toppingCosts[i] 表示 一份 第 i 种冰激凌配料的价格。
 * target ，一个整数，表示你制作甜点的目标价格。
 * 你希望自己做的甜点总成本尽可能接近目标价格 target 。
 *
 * 返回最接近 target 的甜点成本。如果有多种方案，返回 成本相对较低 的一种。
 * @author xxh
 * Created on 2022-12-05
 */
public class ClosestCost {

    int cost = Integer.MAX_VALUE;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {

        for (int i = 0; i < baseCosts.length; i++) {
            bt(baseCosts[i], target, toppingCosts, 0);
        }

        return cost;
    }

    private void bt(int curCost, int target, int[] toppingCosts, int step) {
        if (Math.abs(curCost - target) < Math.abs(cost - target)) {
            cost = curCost;
        } else if (Math.abs(curCost - target) == Math.abs(cost - target) && curCost < cost) {
            cost = curCost;
        }
        if (curCost > target || step >= toppingCosts.length) {
            return;
        }

        bt(curCost + toppingCosts[step] * 2, target, toppingCosts, step + 1);
        bt(curCost + toppingCosts[step], target, toppingCosts, step + 1);
        bt(curCost, target, toppingCosts, step + 1);
    }
}
