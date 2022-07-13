package com.example.springboot.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String say() {
        return "Hello T2009M1";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String goodbye() {
        return "Goodbye T2009M1";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String putGoodbye() {
        return "Put Goodbye T2009M1";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteGoodbye() {
        return "Delete Goodbye T2009M1";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hi")
    public String hi() {
        return "Hello T2009M1";
    }
}
