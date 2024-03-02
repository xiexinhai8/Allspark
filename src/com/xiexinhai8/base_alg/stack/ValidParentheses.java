package com.xiexinhai8.base_alg.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-10
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        int curIndex = 0;
        while (curIndex < s.length()) {
            char curChar = s.charAt(curIndex);

            if (map.get(curChar) == null) {
                stack.push(curChar);
                curIndex++;
                continue;
            }

            if (!stack.isEmpty() && map.get(curChar) == stack.peek()) {
                stack.pop();
                curIndex++;
            } else {
                return false;
            }

        }

        return stack.isEmpty() ? true : false;
    }
}
