package ru.vlad.fateev.salary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vlad.fateev.salary.util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class HolidayConfiguration {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value("${holidays}")
    private List<String> holidayDates;

    @Bean
    public DateUtil initDateUtil() {
        return new DateUtil(holidayDates.stream()
                .map(date -> LocalDate.parse(date, DATE_TIME_FORMAT))
                .collect(Collectors.toSet()));
    }
}
