package com.xiexinhai8.dynamic_load;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-08-23
 */
public class JavaService {

    public Object saveAndGetObject(String packageName,String className,String javaContent) throws Exception {
        Object object = JdkCompiler.compile(packageName, className, javaContent);
        return object;
    }

}
