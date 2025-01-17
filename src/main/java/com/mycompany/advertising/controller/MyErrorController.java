package com.mycompany.advertising.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Amir on 5/7/2021.
 */
@Controller
public class MyErrorController implements ErrorController {
    @Value("${amir.error.folder}")
    String errorfolder;

    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("error_code", statusCode.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return errorfolder + "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return errorfolder + "error-500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return errorfolder + "error-403";
            }
        }
        return errorfolder + "error";
    }

    @Override
    public String getErrorPath() {
        //it set in application.properties server.error.path=/error
        return null;
    }
}
