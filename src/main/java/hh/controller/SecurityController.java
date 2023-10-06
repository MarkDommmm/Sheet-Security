package hh.controller;


import hh.exception.CustomsException;
import hh.model.domain.GooglePojo;
import hh.model.domain.UserPrinciple;
import hh.model.dto.request.EmployeeRequest;
import hh.model.dto.request.SignInForm;

import hh.security.GoogleUtils;

import hh.service.iml.IEmployeeServiceIml;
import lombok.AllArgsConstructor;

import org.apache.http.HttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@AllArgsConstructor
@CrossOrigin("*")
//@RequestMapping("/public")
public class SecurityController {
    private final IEmployeeServiceIml employeeRepo;

    private final GoogleUtils googleUtils;
    private final AuthenticationManager authenticationManager;

    @RequestMapping("/public/login")
    public ModelAndView login() {
        return new ModelAndView("/pages/login", "login", new SignInForm());
    }

    @PostMapping("/public/login")
    public String signInEmployee(@ModelAttribute("login") SignInForm signInForm, HttpServletResponse response) throws LoginException {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
            );
            // tạo đối tượng authentication để xác thực thông qua username và password

        } catch (AuthenticationException e) {
            throw new LoginException("Username or password is incorrect!");
        }

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        // Lưu thông tin userPrinciple vào cookie
        Cookie userCookie = new Cookie("userPrinciple", userPrinciple.getUsername());
        userCookie.setMaxAge(60 * 60);
        response.addCookie(userCookie);
        return "redirect:/user";
    }

    @RequestMapping("/public/sign-up")
    public ModelAndView signUp() {
        return new ModelAndView("/pages/signUp", "signup", new EmployeeRequest());
    }

    @PostMapping("/public/sign-up")
    public String signUpEmployee(@ModelAttribute("signup") EmployeeRequest employeeRequest) throws CustomsException {
        employeeRepo.save(employeeRequest);
        return "redirect:/public/login";
    }


    @GetMapping("/login-google")
    public String loginGoogle(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/login?google=error";
        }
        String accessToken = googleUtils.getToken(code);
        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/user";
    }

    @RequestMapping("/public/home")
    public String home() {
        return "/pages/home";
    }

    @RequestMapping("/user")
    public String user() {
        return "/pages/user";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/pages/admin";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "/pages/403";
    }

}
