/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koodu.controller;

import com.koodu.service.TimeService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abiola.Adebanjo
 */
@RestController
@RequestMapping(value = "/api/v1/timestamp")
public class TimeController {

    @Autowired
    TimeService timeService;

    @RequestMapping(method = RequestMethod.GET)
    public HashMap<String, String> getTimeStamp() {
        return timeService.getTimeStamp(null);
    }

    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    public HashMap<String, String> getTimeStamp(@PathVariable("param") String param) {
        return timeService.getTimeStamp(param);
    }
}
