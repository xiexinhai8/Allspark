package com.xiexinhai8.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2021-01-12
 */
public class A {
    public B b = new B(this);
    public String value = "A";

    public static void main(String[] args) {
        A a = new A();
        a.value = "A1";
        System.out.println(a.b.a.value);
        C c = new C();
        System.out.println(c.c);
        List<Integer> x = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(x.subList(1, 4));
    }

    public static void updateC(C c, int value) {
        c = new C();
        c.c = value;
    }
}
