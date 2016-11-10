package com.koodu.controller;

import com.koodu.exception.TimeException;
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
    public HashMap<String, String> getTimeStamp() throws TimeException {
        return timeService.getTimeStamp(null);
    }

    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    public HashMap<String, String> getTimeStamp(@PathVariable("param") String param) throws TimeException {
        return timeService.getTimeStamp(param);
    }
}
