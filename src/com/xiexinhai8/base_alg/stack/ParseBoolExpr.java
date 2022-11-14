package com.xiexinhai8.base_alg.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author xxh
 * Created on 2022-11-06
 */
public class ParseBoolExpr {

    public static void main(String[] args) {
        new ParseBoolExpr().parseBoolExpr("&(t,t,t)");
    }
    /**
     创建一个stack来遍历expression
     1、遇到)时开始往外抛直到(出现, 抛出的字符需要保存到list中,并用(后的操作符操作
     2、操作符只有& | ! 其中 !比较特殊
     3、t是true、f是false
     */
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ')') {
                char stackChar = stack.pop();
                List<Character> tmpExpList = new ArrayList<>();
                while (stackChar != '(') {
                    tmpExpList.add(stackChar);
                    stackChar = stack.pop();
                }
                char opChar = stack.pop();
                //TODO impl
                boolean r = decode(tmpExpList.get(0));
                if (opChar == '!') {
                    //TODO 可以增加校验逻辑
                    r = !r;
                }
                for (int j = 1; j < tmpExpList.size(); j++) {
                    if (opChar == '&') {
                        r = r && decode(tmpExpList.get(j));
                    }
                    if (opChar == '|') {
                        r = r || decode(tmpExpList.get(j));
                    }
                }
                //TODO impl
                stack.push(encode(r));
            } else {
                stack.push(expression.charAt(i));
            }
        }
        return decode(stack.pop());
    }

    private char encode(boolean exp) {
        if (exp) {
            return 't';
        } else {
            return 'f';
        }
    }

    private boolean decode(char exp) {
        if ('t' == exp) {
            return true;
        } else {
            return false;
        }
    }
}
