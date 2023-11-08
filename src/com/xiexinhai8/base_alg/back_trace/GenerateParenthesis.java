package com.xiexinhai8.base_alg.back_trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-11-08
 */
public class GenerateParenthesis {

    List<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    /**
     * 通过构造输出来确定操作
     *
     * 限制：左括号数量要大于等于右括号数
     *
     * 输出：当总数 == 2n时输出
     *
     * 剪枝：如果左括号数 > n 或 右括号数 > 左括号数 停止操作
     */
    public List<String> generateParenthesis(int n) {
        bt(0, 0, n);

        return result;
    }

    private void bt(int left, int right, int limit) {
        if (left + right >= limit * 2) {
            result.add(sb.toString());
            return;
        }

        // 每次操作只指定当前位置的数据，所以操作完成后要及时将字符串删除
        if (left < limit) {
            sb.append("(");
            bt(left + 1, right, limit);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right < left) {
            sb.append(")");
            bt(left, right + 1, limit);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
