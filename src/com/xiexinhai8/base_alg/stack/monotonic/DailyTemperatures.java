package com.xiexinhai8.base_alg.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739. 每日温度
 *
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-12
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int dayTime = stack.pop();
                ans[dayTime] = i - dayTime;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = 0;
        }

        return ans;
    }
}
