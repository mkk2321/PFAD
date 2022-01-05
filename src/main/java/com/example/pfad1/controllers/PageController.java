package com.example.pfad1.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class PageController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String rootGet() {
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String homeGet() {
        return "index";
    }

    @RequestMapping(value = "/introduce", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String introduceGet() {
        return "user/introduce";
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String faqGet() {
        return "user/faq";
    }


}
