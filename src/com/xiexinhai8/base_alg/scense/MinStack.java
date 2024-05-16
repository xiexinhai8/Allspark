package com.xiexinhai8.base_alg.scense;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. 最小栈
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 * @author xxh
 * Created on 2024-05-14
 */
/** 采用双栈的方式实现: 一个存放正常数据, 另一个存放使用单调栈存放最小值数据 */
class MinStack {
    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
