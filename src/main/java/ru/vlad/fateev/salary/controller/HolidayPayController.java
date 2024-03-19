package ru.vlad.fateev.salary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlad.fateev.salary.model.HolidayPayData;
import ru.vlad.fateev.salary.service.HolidayPayService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holiday-pay")
public class HolidayPayController {

    private final HolidayPayService holidayPayService;

    @GetMapping("/calculate")
    public ResponseEntity<BigDecimal> calculateHolidayPay(@RequestBody HolidayPayData data) {
        return ResponseEntity.ok(holidayPayService.calculateAverageSalary(data));
    }
}
