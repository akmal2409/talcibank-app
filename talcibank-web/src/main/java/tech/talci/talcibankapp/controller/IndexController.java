package tech.talci.talcibankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Column;

@Controller
public class IndexController {

    @GetMapping
    @RequestMapping({"/", "/index", "/index.html"})
    public String getIndexPage(){
        return "index";
    }
}
