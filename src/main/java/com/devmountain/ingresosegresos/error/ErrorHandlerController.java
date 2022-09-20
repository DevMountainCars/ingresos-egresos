package com.devmountain.ingresosegresos.error;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(DevMountainError.class)
    public String mostrarError(DevMountainError error, Model model) {
        model.addAttribute("message", error.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        model.addAttribute("timestamp", new Date());
        return "error/error";
    }
}
