package com.ptit.sqa.controller;

import com.ptit.sqa.config.jwt.JwtTokenProvider;
import com.ptit.sqa.entity.CustomUserDetails;
import com.ptit.sqa.model.UserLogin;
import com.ptit.sqa.service.impl.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Iterator;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Inject
    DemoService service;
    @GetMapping(path = {"login"})
    public String login(Model model) {
        System.out.println( service.getNumber());
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin", userLogin);
        return "login";
    }

    @GetMapping(path = "hi")
    public String home() {
        return "index";
    }

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping(path = {"login"})
    public String loginSub(Model model, HttpSession session, UserLogin loginRequest, HttpServletResponse response, ModelMap modelMap) {
        log.info("postLogin()");
        ModelAndView modelAndView;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            Cookie cookie = new Cookie("Authorization", jwt);
            response.addCookie(cookie);
            return "redirect:/";

        } catch (Exception e) {
            model.addAttribute("message", " Sai tài khoản hoặc mật khẩu");
            loginRequest.setPassword("");
            modelMap.addAttribute("userLogin", loginRequest)
            ;
            return "login";
        }

    }

    @PostMapping(path = {"logout"})
    public String loginFailure(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Iterator<Cookie> iterator = Arrays.stream(cookies).iterator();
        while (iterator.hasNext()) {
            Cookie cookie = iterator.next();
            String nameToken = cookie.getName();
            if ("Authorization".equals(nameToken)) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/login";
    }


}
