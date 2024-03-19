package ru.vlad.fateev.salary.util;

import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
public class DateUtil {

    private final Set<LocalDate> holidays;
    private final Set<DayOfWeek> workingDays = Set.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY);
    public long getWorkingDays(LocalDate startVacation, LocalDate endVacation) {
        return startVacation.datesUntil(endVacation)
                .filter(day -> workingDays.contains(day.getDayOfWeek()))
                .filter(day -> !holidays.contains(day))
                .count();
    }
}
