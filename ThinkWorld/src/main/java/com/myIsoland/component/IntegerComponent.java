package com.myIsoland.component;

import com.myIsoland.constant.ProjectConstant;
import org.springframework.stereotype.Component;

@Component
public class IntegerComponent {

    public static String generateUserKey(String key){
        return ProjectConstant.USERPREFIX + key;
    }

    public static String generateAppKey(String prefix,String userKey){
        int length = prefix.length();
        int index = userKey.indexOf(prefix);
        return userKey.substring(index+length);
    }
}
