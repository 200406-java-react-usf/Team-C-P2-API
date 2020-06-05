package com.travelapp.util;

import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.web.dtos.TicketDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        if (isEmptyString(user.getFirstName()) || isEmptyString(user.getLastName()) || isEmptyString(user.getEmail()) ||
                isEmptyString(user.getUsername()) || isEmptyString(user.getPassword())){
            return false;
        }
        else {
            return true;
        }

    }

    public static boolean isValidUpdatedUser(User user) {
        if (isEmptyString(user.getFirstName()) || isEmptyString(user.getLastName()) || isEmptyString(user.getEmail()) || isEmptyString(user.getUsername())) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean isValidNumber(double number) {
        return (number > 0);

    }

    public static boolean isValidUpdateTicket(Ticket ticket) {
        if(!isValidId(ticket.getId()) || !isValidNumber(ticket.getCost()) || isEmptyString(ticket.getOrigin()) ||
                isEmptyString(ticket.getDestination()) || isEmptyObj(ticket.getDepartureTime()) ||
                isEmptyObj(ticket.getArrivalTime()) || !isValidUser(ticket.getAuthor()))
        {
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isValidTicket(TicketDto ticket) {
        if(!isValidNumber(ticket.getCost()) || isEmptyString(ticket.getOrigin()) ||
                isEmptyString(ticket.getDestination()) || isEmptyObj(ticket.getDepartureTime()) || isEmptyObj(ticket.getArrivalTime()) || !isValidId(ticket.getAuthor_id()))
        {
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
