package com.huihe.demo.util;

public class StringUtil {
    public static  boolean isEmpty(String text){
        return text == null || text.trim().equals("");
    }
}
