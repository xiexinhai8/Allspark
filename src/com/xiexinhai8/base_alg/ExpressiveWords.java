package com.xiexinhai8.base_alg;

import java.util.ArrayList;
import java.util.List;

/**
 * 809. 情感丰富的文字
 *
 * 有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
 *
 * 对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母 c ），然后往其中添加相同的字母 c
 * 使其长度达到 3 或以上。
 *
 * 例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于 3。此外，我们可以进行另一种扩张 "ll" -> "lllll"
 * 以获得 "helllllooo"。如果 s = "helllllooo"，那么查询词 "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" ->
 * "helllllooo" = s。
 *
 * 输入一组查询单词，输出其中可扩张的单词数量。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-26
 */
public class ExpressiveWords {

    static class Pair {
        char c;
        int fre;
        public Pair(char c, int fre) {
            this.c = c;
            this.fre = fre;
        }

        public String toString() {
            return "" + c + ": " + fre;
        }
    }
    public int expressiveWords(String s, String[] words) {
        List<Pair> charFres = new ArrayList<>();
        Character pre = null;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (pre == null) {
                pre = cur;
                count = 1;
            } else if (cur == pre) {
                count++;
            } else {
                charFres.add(new Pair(pre, count));
                pre = cur;
                count = 1;
            }
        }
        charFres.add(new Pair(pre, count));
        // System.out.println(charFres);

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            String curStr = words[i];

            pre = curStr.charAt(0);
            count = 1;
            //TODO 双指针最好放一起判断条件
            for (int j = 1, index = 0; j < curStr.length() && index < charFres.size(); j++) {
                Character cur = curStr.charAt(j);

                if (cur != pre) {
                    Pair p = charFres.get(index);
                    if (p.c != pre || p.fre < count || (p.fre > count && p.fre < 3)) {
                        break;
                    }
                    pre = cur;
                    count = 1;
                    index++;
                    /**
                     * 使用这个判断会让本来正常停止的状况落入异常停止中
                     if (index >= charFres.size()) {
                     break;
                     } */
                } else {
                    count++;
                }

                // 只有进入这个逻辑中才是正常停止
                if (j == curStr.length() - 1) {
                    Pair p = charFres.get(index++);
                    if (p.c != cur || p.fre < count || (p.fre > count && p.fre < 3) || index < charFres.size()) {
                        break;
                    }
                    result++;
                }
            }

        }
        return result;
    }
}
