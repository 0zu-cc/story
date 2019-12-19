package com.storyxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 19:05
 */
@Controller
@RequestMapping
public class RouteController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/index")
    public String indexHtml(){
        return "index";
    }

    @GetMapping("/editor")
    public String editor(){
        return "editor";
    }

    @GetMapping("/management")
    public String management(){
        return "management";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/iu")
    public String iu(){
        return "iu";
    }

}
