/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koodu.service;

import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abiola.Adebanjo
 */
@Service
public class TimeService {

    public HashMap<String, String> getTimeStamp(String param) {
        HashMap<String, String> timestamp = new HashMap<String, String>();

        long unixTimestamp = 0;
        String dateString = null;

        if (param != null && !param.isEmpty()) {
            param = URLDecoder.decode(param);
            LocalDateTime passedDate = null;
            try {
                passedDate = LocalDateTime.parse(param, DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
                dateString = passedDate.toString();
                unixTimestamp = passedDate.toEpochSecond(ZoneOffset.UTC);
            } catch (DateTimeParseException ex) {
                long epoch = Long.parseLong(param);
                Instant instant = Instant.ofEpochSecond(epoch);
                passedDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
                dateString = passedDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
                unixTimestamp = passedDate.toEpochSecond(ZoneOffset.UTC);
            }
        } else {
            Instant currentTime = Instant.now();
            unixTimestamp = currentTime.getEpochSecond();

            LocalDate currentDate = LocalDate.now();
            dateString = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        }

        timestamp.put("unix", String.valueOf(unixTimestamp));
        timestamp.put("natural", dateString);

        return timestamp;
    }
}
