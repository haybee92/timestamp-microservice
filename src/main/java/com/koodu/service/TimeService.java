package com.koodu.service;

import com.koodu.exception.TimeException;
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

    public HashMap<String, String> getTimeStamp(String param) throws TimeException {
        HashMap<String, String> timestamp = new HashMap<String, String>();

        long unixTimestamp = 0;
        String dateString = null;

        if (param != null && !param.isEmpty()) {
            LocalDateTime passedDate = null;
            try {
                passedDate = LocalDate.parse(param, DateTimeFormatter.ofPattern("MMMM dd, yyyy")).atStartOfDay();
                dateString = passedDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
                unixTimestamp = passedDate.toEpochSecond(ZoneOffset.UTC);
            } catch (DateTimeParseException ex) {
                try {
                    long epoch = Long.parseLong(param);
                    Instant instant = Instant.ofEpochSecond(epoch);
                    passedDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
                    dateString = passedDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
                    unixTimestamp = passedDate.toEpochSecond(ZoneOffset.UTC);
                } catch (NumberFormatException nfe) {
                    throw new TimeException("02", "Parameter not in correct format");
                }

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
