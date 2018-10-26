package com.example.application.service.payroll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.domain.model.payroll.daily.HourlyWage;
import com.example.domain.model.payroll.daily.MinuteUnit;
import com.example.domain.model.payroll.daily.TimeRecord;
import com.example.domain.type.hour.HourTime;
import com.example.domain.type.hour.Minute;
import com.example.domain.type.money.Amount;

class DailyPayrollTest extends DailyPayroll {

    @Test
    @DisplayName("給与計算時間 60分単位 切捨て")
    void case60MinutesTruncated() {
        HourTime start = new HourTime(9, 0);
        HourTime end = new HourTime(18, 30);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(60), false);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * 60 * 8)).isEqualTo(actual));
    }

    @Test
    @DisplayName("給与計算時間 60分単位 四捨五入切捨て")
    void case60MinutesNotRounded() {
        HourTime start = new HourTime(9, 0);
        HourTime end = new HourTime(18, 29);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(60), true);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * 60 * 8)).isEqualTo(actual));
    }

    @Test
    @DisplayName("給与計算時間 60分単位 四捨五入切上げ")
    void case60MinutesRounded() {
        HourTime start = new HourTime(9, 0);
        HourTime end = new HourTime(18, 30);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(60), true);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * 60 * 9)).isEqualTo(actual));
    }

    @Test
    @DisplayName("給与計算時間 5分単位 切捨て")
    void case5MinutesTruncated() {
        HourTime start = new HourTime(9, 0);
        HourTime end = new HourTime(18, 4);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(5), false);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * 60 * 8)).isEqualTo(actual));
    }

    @Test
    @DisplayName("給与計算時間 5分単位 四捨五入切捨て")
    void case5MinutesNotRounded() {
        HourTime start = new HourTime(9, 0);
        HourTime end = new HourTime(18, 2);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(5), true);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * 60 * 8)).isEqualTo(actual));
    }

    @Test
    @DisplayName("給与計算時間 5分単位 四捨五入切上げ")
    void case5MinutesRounded() {
        HourTime start = new HourTime(9, 0);
        HourTime end = new HourTime(18, 3);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(5), true);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * (60 * 8 + 5))).isEqualTo(actual));

    }

    @Test
    @DisplayName("給与計算時間 1分単位 切捨て")
    void case1MinutesTruncated() {
        HourTime start = new HourTime(8, 50);
        HourTime end = new HourTime(18, 0);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(1), false);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * (60 * 8 + 10))).isEqualTo(actual));
    }

    @Test
    @DisplayName("給与計算時間 1分単位 四捨五入")
    void case1MinutesRounded() {
        HourTime start = new HourTime(8, 50);
        HourTime end = new HourTime(18, 0);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        MinuteUnit minuteUnit = new MinuteUnit(new Minute(1), true);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, minuteUnit, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * (60 * 8 + 10))).isEqualTo(actual));
    }

}
