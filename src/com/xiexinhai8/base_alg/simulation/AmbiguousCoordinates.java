package com.xiexinhai8.base_alg.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 816. 模糊坐标
 *
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-07
 */
public class AmbiguousCoordinates {

    public static void main(String[] args) {
        new AmbiguousCoordinates().ambiguousCoordinates("1234");
    }

    public List<String> ambiguousCoordinates(String s) {

        List<String> ambiguousCoordinates = new ArrayList<>();
        /** 分出的字串不能全是0, 除非只有一个*/
        for (int j = 1; j < s.length(); j++) {
            String pre = s.substring(0, j);
            String post = s.substring(j, s.length());
            if (validate(pre) && validate(post)) {

                List<String> preList = generateList(pre);
                List<String> postList = generateList(post);

                combin(preList, postList, ambiguousCoordinates);
            }
        }

        return ambiguousCoordinates;
    }

    private void combin(List<String> preList, List<String> postList, List<String> ambiguousCoordinates) {
    }

    /** 不能全为0,除非只有一位 . 不能全为0
     1、首位非0且尾数非0小数点可以随便加
     2、首位是0小数点只能加在第一个0后
     */
    private List<String> generateList(String candiate) {
        List<String> result = new ArrayList<>();
        if (candiate.charAt(0) == '0') {
            result.add(candiate.substring(0, 1) + "." + candiate.substring(1, candiate.length()));
            return result;
        }
        /*for (int i = candiate.length(); i < candiate.length(); i++) {
            if () {

            }
        }*/
        return null;
    }

    /**
     * 1、分出的字串不能全是0, 除非只有一个
     * 2、如果首位为0， 末位不能为0*/
    private boolean validate(String candiate) {
        if (candiate.length() == 1) {
            return true;
        }

        if (candiate.charAt(0) == '0') {
            return candiate.charAt(candiate.length() - 1) != '0';

        }
        return true;
    }
}
