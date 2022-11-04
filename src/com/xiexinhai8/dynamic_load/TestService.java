package com.xiexinhai8.dynamic_load;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-08-23
 */
public class TestService {

    public static void main(String[] args) throws Exception {
        test();
    }

    static String content="package cn.mmc;\n" +
            "\n" +
            "public class SayHello {\n" +
            "    \n" +
            "    public void say(){\n" +
            "        System.out.println(\"11111111111\");\n" +
            "    }\n" +
            "}";

    static String content2="package cn.mmc;\n" +
            "\n" +
            "public class SayHello {\n" +
            "    \n" +
            "    public void say(){\n" +
            "        System.out.println(\"22222222222222\");\n" +
            "    }\n" +
            "}";

    public static void test() throws Exception {
        JavaService javaService = new JavaService();
        Object sayHello = javaService.saveAndGetObject("cn.mmc", "SayHello", content);
        sayHello.getClass().getMethod("say").invoke(sayHello);

        Object sayHello2 = javaService.saveAndGetObject("cn.mmc", "SayHello", content2);
        sayHello2.getClass().getMethod("say").invoke(sayHello2);
    }
}
