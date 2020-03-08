package com.myIsoland.common.util;

public class CaculateUtils {

    public static String translateId(String prefix,String no){
        if(!StringUtils.isEmpty(no)){
            int length = prefix.length();
            return no.substring(length);
        }
        return no;
    }
}
