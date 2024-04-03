package com.xiexinhai8.base_alg.greedy;

/**
 * 134. 加油站
 *
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * @author xxh
 * Created on 2024-04-03
 */
public class CanCompleteCircuit {

    public static void main(String[] args) {
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        new CanCompleteCircuit().canCompleteCircuit(gas, cost);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {

        for (int i = 0; i < gas.length; i++) {
            gas[i] = gas[i] - cost[i];
        }

        if (gas.length == 1) {
            return gas[0] >= 0 ? 0 : -1;
        }

        int start = 0;
        int cur = 1;
        int sum = gas[0];
        while (start < gas.length) {
            while (sum >= 0) {
                if (cur == start) {
                    return start;
                }
                sum += gas[cur];
                cur = (cur + 1) % gas.length;

            }
            if (start >= cur) {
                break;
            }
            start = cur;
            sum = gas[cur];
            cur = (cur + 1) % gas.length;
        }

        return -1;

    }
}
