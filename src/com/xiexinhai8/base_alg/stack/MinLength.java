package com.xiexinhai8.base_alg.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2696. 删除子串后的字符串最小长度
 *
 * 给你一个仅由 大写 英文字符组成的字符串 s 。
 *
 * 你可以对此字符串执行一些操作，在每一步操作中，你可以从 s 中删除 任一个 "AB" 或 "CD" 子字符串。
 *
 * 通过执行操作，删除所有 "AB" 和 "CD" 子串，返回可获得的最终字符串的 最小 可能长度。
 *
 * 注意，删除子串后，重新连接出的字符串可能会产生新的 "AB" 或 "CD" 子串。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-10
 */
public class MinLength {

    public int minLength(String s) {
        int curIndex = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        while (curIndex < s.length()) {

            while(!stack.isEmpty() && curIndex < s.length() && valid(stack.peek(), curIndex, s)) {
                stack.pop();
                curIndex++;
            }
            if (curIndex < s.length()) {
                stack.push(curIndex);
                curIndex++;
            }

        }

        //System.out.println(stack);
        return stack.size();
    }

    private boolean valid(int first, int second, String s) {
        char fc = s.charAt(first);
        char sc = s.charAt(second);

        if ((fc == 'A' && sc == 'B') || (fc == 'C' && sc == 'D')) {
            return true;
        }
        return false;
    }
}
