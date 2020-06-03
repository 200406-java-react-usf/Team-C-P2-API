package com.travelapp.util;

import com.travelapp.models.User;

import java.util.List;

public class Validator {

    public static boolean isValidId(int id) {
        return (id > 0);
    }

    public static boolean isEmptyObj(Object obj) {
        return (obj == null);
    }

    public static boolean isEmptyList(List list) {
        return (list == null);
    }

    public static boolean isEmptyString(String string) {
        return (string.trim() == "");
    }

    public static boolean isValidUser(User user) {
        if (!isValidId(user.getId()) || isEmptyString(user.getFirstName()) || isEmptyString(user.getLastName()) || isEmptyString(user.getEmail())){
            return false;
        }
        else {
            return true;
        }

    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}
