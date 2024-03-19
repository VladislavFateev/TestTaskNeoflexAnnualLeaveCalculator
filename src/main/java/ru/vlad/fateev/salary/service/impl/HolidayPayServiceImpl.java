package ru.vlad.fateev.salary.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vlad.fateev.salary.exception.DataIncorrectException;
import ru.vlad.fateev.salary.model.HolidayPayData;
import ru.vlad.fateev.salary.service.HolidayPayService;
import ru.vlad.fateev.salary.util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class HolidayPayServiceImpl implements HolidayPayService {

    @Value("${averageDaysMonth}")
    private static double workingDays;

    private final BigDecimal COUNT_MONTHS = new BigDecimal(workingDays);

    private final BigDecimal COUNT_AVERAGE_DAYS = new BigDecimal(workingDays);

    private final DateUtil dateUtil;

    @Override
    public BigDecimal calculateAverageSalary(HolidayPayData data) {
        if (data.getHolidayDays() != null) {
            return calculateAverageSalaryWithDays(data);
        }
        if (data.getStartVacation() != null && data.getEndVacation() != null) {
            return calculateAverageSalaryWithDate(data);
        }
        throw new DataIncorrectException();
    }

    public BigDecimal calculateAverageSalaryWithDays(HolidayPayData data) {
        BigDecimal leaveDays = new BigDecimal(data.getHolidayDays());
        BigDecimal averageSalary = BigDecimal.valueOf(data.getAverageSalary());
        return calculatePayment(averageSalary, leaveDays);
    }

    public BigDecimal calculateAverageSalaryWithDate(HolidayPayData data) {
        BigDecimal workingDays = new BigDecimal(dateUtil.getWorkingDays(data.getStartVacation(), data.getEndVacation()));
        BigDecimal averageSalary = BigDecimal.valueOf(data.getAverageSalary());
        return calculatePayment(averageSalary, workingDays);
    }

    public BigDecimal calculatePayment(BigDecimal averageSalary, BigDecimal days) {
        return averageSalary.divide(COUNT_MONTHS.multiply(COUNT_AVERAGE_DAYS), RoundingMode.HALF_UP).multiply(days);
    }
}
