package com.oridway.oridcore.utils;

import java.util.HashMap;

/**
 * Created by lihao on 2017/8/5.
 */

public class ConfigUtil {

    private static HashMap<String, String> configMap;

    public static HashMap<String, String> getDefaultMap(){
        if (configMap == null){
            synchronized (ConfigUtil.class){
                if (configMap == null){
                    configMap = new HashMap<>();
                }
            }
        }
        return configMap;
    }
}
