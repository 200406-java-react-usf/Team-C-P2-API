package com.travelapp.util;

public class Validator {

    public static boolean isValidId(int id) {
        return (id > 0);
    }

    public static boolean isEmptyObj(Object obj) {
        return (obj != null);
    }

}
