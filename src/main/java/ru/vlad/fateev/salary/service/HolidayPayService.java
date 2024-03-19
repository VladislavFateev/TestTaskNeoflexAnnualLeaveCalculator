package ru.vlad.fateev.salary.service;

import ru.vlad.fateev.salary.model.HolidayPayData;

import java.math.BigDecimal;

public interface HolidayPayService {
    BigDecimal calculateAverageSalary(HolidayPayData data);
}
