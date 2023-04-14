package com.xiexinhai8.base_alg.two_pointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 1023. 驼峰式匹配
 *
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 *
 * 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-14
 */
public class CamelMatch {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean>  result = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            result.add(match(queries[i], pattern));
        }

        return result;
    }

    private boolean match(String query, String pattern) {
        int qI = 0;
        int pI = 0;

        while (qI < query.length()) {
            char qC = query.charAt(qI);
            char pC = '1';
            if (pI < pattern.length()) {
                pC = pattern.charAt(pI);
            }

            if (qC > 'Z') {
                if (pI < pattern.length() && pC == qC) {
                    pI++;
                }
                qI++;
            } else {
                if (pC == qC){
                    pI++;
                    qI++;
                } else {
                    return false;
                }
            }

        }
        return pI >= pattern.length() ? true : false;
        // query[qI] > 'Z' || (query[qI] <= 'Z' && pattern[pI] == query[qI])
    }

    private boolean matchV1(String query, String pattern) {
        int qI = 0;
        int pI = 0;

        while (qI < query.length()) {
            char qC = query.charAt(qI);
            char pC = '1';
            if (pI < pattern.length()) {
                pC = pattern.charAt(pI);
            }

            if (qC > 'Z') {
                if (pI < pattern.length() && pC == qC) {
                    pI++;
                }
                qI++;
            } else {
                // pattern 到底了 false
                if (pI >= pattern.length()) {
                    return false;
                } else if (pI < pattern.length() && pC != qC) {
                    return false;
                } else if (pC == qC){
                    pI++;
                    qI++;
                } else {
                    return false;
                }

                // pattern 没到底 但是匹配不上 false;

                // pattern 没到底 匹配上了
            }

        }
        return pI >= pattern.length() ? true : false;
        // query[qI] > 'Z' || (query[qI] <= 'Z' && pattern[pI] == query[qI])
    }
}
