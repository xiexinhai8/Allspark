package com.xiexinhai8.base_alg.two_pointers;

/**
 * 2337. 移动片段得到字符串
 * 给你两个字符串 start 和 target ，长度均为 n 。每个字符串 仅 由字符 'L'、'R' 和 '_' 组成，其中：
 *
 * 字符 'L' 和 'R' 表示片段，其中片段 'L' 只有在其左侧直接存在一个 空位 时才能向 左 移动，而片段 'R' 只有在其右侧直接存在一个 空位 时才能向 右 移动。
 * 字符 '_' 表示可以被 任意 'L' 或 'R' 片段占据的空位。
 * 如果在移动字符串 start 中的片段任意次之后可以得到字符串 target ，返回 true ；否则，返回 false 。
 * @author xxh
 * Created on 2023-08-22
 */
public class CanChange {

    public static void main(String[] args) {
        String source = "_L__R__R_";
        String target = "L______RR";
        new CanChange().canChange(source, target);
    }

    public boolean canChange(String start, String target) {
        /**
         */

        if (!start.replaceAll("_", "").equals(target.replaceAll("_", "")))
            return false;
        int sSize = start.length();
        int tSize = target.length();
        if (sSize != tSize) {
            return false;
        }
        int si = 0;
        int ti = 0;
        while (si < sSize) {
            char sChar = start.charAt(si);
            si++;
            if (sChar == '_') {
                continue;
            }
            while (ti < tSize && target.charAt(ti) == '_') {
                ti++;
            }
            char tChar = target.charAt(ti);
            ti++;
            if (sChar == 'L' && tChar == 'L') {
                if (ti > si) {
                    return false;
                }
            } else if (sChar == 'R' && tChar == 'R') {
                if (ti < si) {
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;
    }
}
