package com.axonstech.PricingService.utils;

public class KeyMappingUtils {
    public static String concatString(String intermediary, String... str){
        StringBuilder concatString = new StringBuilder();
        for(int i = 0;i < str.length;i++){
            if(i != 0 && i+1 != str.length){
                concatString.append(intermediary);
            }
            concatString.append(str[i]);
        }
        return concatString.toString();
    }
}
