package jskj.com.naprioridetectclient.util;

public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str.equals("") || str == null) {
            return true;
        }
        return false;
    }
}
