package com.wft.util;

public class TextUtil {

    /**
     * return true if str is empty or null
     * @param str string
     * @return return true if str is empty or null
     */
    private static boolean isEmpty(String str) {
        if (str == null){
            return true;
        }
        return str.length() == 0;
    }


    public static boolean empty(String ...strings){
        for(String str : strings){
            if (isEmpty(str)){
                return true;
            }
        }
        return false;
    }

}
