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

    public static boolean isEmptyString(String string) {
        return (string.trim().equals(""));
    }

    public static boolean isValidUser(User user) {
        if (isEmptyString(user.getFirstName()) || isEmptyString(user.getLastName()) || isEmptyString(user.getEmail()) || isEmptyString(user.getUsername()) || isEmptyString(user.getPassword())){
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
