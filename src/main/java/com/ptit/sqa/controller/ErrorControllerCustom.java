package com.ptit.sqa.controller;

import com.ptit.sqa.model.UserLogin;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class ErrorControllerCustom implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping("error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model){
        Integer status = response.getStatus();
        if(!Objects.isNull(status) && 403 == status.intValue()){
            UserLogin userLogin=new UserLogin();
            model.addAttribute("userLogin",userLogin);
            return "login";
        }
        model.addAttribute("status",status);
        return "error";
    }
}
