package com.xiexinhai8.base_alg.stack;

/**
 * 1003. 检查替换后的词是否有效
 *
 * 给你一个字符串 s ，请你判断它是否 有效 。
 * 字符串 s 有效 需要满足：假设开始有一个空字符串 t = "" ，你可以执行 任意次 下述操作将 t 转换为 s ：
 *
 * 将字符串 "abc" 插入到 t 中的任意位置。形式上，t 变为 tleft + "abc" + tright，其中 t == tleft + tright 。注意，tleft 和 tright 可能为 空 。
 * 如果字符串 s 有效，则返回 true；否则，返回 false。
 * @author xxh
 * Created on 2023-05-03
 */
public class IsValid {

    public boolean isValid(String s) {
        // 思路: 遍历s, 每次将abc匹配串删除, 如果什么时候s不减少了 或 ==0了停止
        int length = s.length() + 1;

        StringBuilder sb = new StringBuilder();
        while (s.length() < length && s.length() > 0) {
            // 匹配abc模式太慢了 : 使用StringBuilder的方式代替栈的功能
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                if (sb.length() >= 3 && "abc".equals(sb.substring(sb.length() - 3))) {
                    sb.delete(sb.length() - 3, sb.length());
                }
            }

            s = sb.toString();
            length = s.length();
        }

        return s.length() == 0 ? true : false;
    }
}
