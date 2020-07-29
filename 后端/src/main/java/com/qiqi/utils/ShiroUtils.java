package com.qiqi.utils;

import com.qiqi.shiro.RealmProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {


    public static RealmProfile getProfile(){

        return (RealmProfile) SecurityUtils.getSubject().getPrincipal();
    }
}