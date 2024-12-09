package org.se06203.campusexpensemanagement.config;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.Instant;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ConvertToDate {
    public static Month convertInstantToDateMonth(Instant date) {
        ZonedDateTime zonedDateTime = date.atZone(ZoneId.systemDefault());

        return zonedDateTime.getMonth();
    }

    public static Instant lastDayOfMonth() {
        Instant instant = Instant.now();

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

        int lastDay = zonedDateTime.toLocalDate().lengthOfMonth();
        return zonedDateTime.withDayOfMonth(lastDay).toInstant();
    }

    public static Instant firstDayOfMonth(){
         Instant instant = Instant.now();

         ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

         return zonedDateTime.withDayOfMonth(1).toInstant();
    }
}
