package com.eazybytes.demo.controller;

import com.eazybytes.demo.model.Person;
import com.eazybytes.demo.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @Value("${eazyschool.pageSize}")
    private String defaultPageSize;

    @Value("${eazyschool.contact.successMsg}")
    private String message;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInPerson", person);
        loggingMessage();
        return "dashboard.html";
    }

    private void loggingMessage(){
        log.info("logging info message");
        log.warn("logging warn message");
        log.error("logging error message");
        log.trace("logging trace message");

        log.error("defaultPageSize value:"+ defaultPageSize);
        log.error("message:"+ message);
    }

}
