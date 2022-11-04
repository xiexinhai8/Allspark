package com.xiexinhai8.base_alg.search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-10-19
 */
public class DigitSearch {
    boolean diffTag = false;
    int len = 0;
    int sr = -1;

    public int search(int[] digits, int target) {
        /**
         * 0、digits 排序， 方便找出最小值
         *
         * 1、target 按位入栈
         * 2、从高到低对比target数据
         *  2.1 相等直接插入
         *  2.2 不相等 次小存在取次小， 次小不存在回溯到上一位取次小； 并将tag置true
         * 3、如果tag是true，直接取digit最大值
         */

        Deque<Integer> stack = new ArrayDeque<>();
        while (target > 0) {
            stack.push(target % 10);
            target /= 10;
        }
        len = stack.size();

        Deque<Integer> path = new ArrayDeque<>();

        // dfs(0, stack, path, digits);
        return 0;

    }

/*    private void dfs(int pos, Deque<Integer> stack, Deque<Integer> path, int[] digits) {
        if (sr > 0) {
            return;
        }
        if (pos >= len) {
            sr = pathToInt(path);
            return;
        }

        if (diffTag) {
            path.push(getMaxVal(digits));
        } else {
            int cur = stack.pop();
            if (contains(digits, cur)) {
                path.push(cur);
            } else if (containLastMin(digits, cur)) {
                path.push(getLastMin(digits, cur));
                diffTag = true;
            } else {
                // 返回上层回溯
            }
        }
        dfs(pos + 1, stack, path, digits);

    }*/
}
