package com.travelapp.util;

import com.travelapp.exceptions.*;
import com.travelapp.web.dtos.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Component
@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler
    public ErrorResponse handleTravelappException(TravelappException e, HttpServletResponse resp){
        ErrorResponse err = new ErrorResponse(e);

        if (e instanceof BadRequestException){
            resp.setStatus(400);
        }
        else if (e instanceof AuthenticationException){
            resp.setStatus(401);
        }
        else if (e instanceof AuthorizationException){
            resp.setStatus(403);
        }
        else if (e instanceof ResourceNotFoundException){
            resp.setStatus(404);
        }
        else if (e instanceof ResourcePersistenceException){
            resp.setStatus(409);
        }
        else if(e instanceof InternalServerErrorException){
            resp.setStatus(500);
        }

        return err;
    }
}
