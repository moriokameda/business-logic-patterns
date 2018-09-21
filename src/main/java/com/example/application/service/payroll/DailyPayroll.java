package com.example.application.service.payroll;

import com.example.domain.model.payroll.daily.DailyPay;
import com.example.domain.model.payroll.daily.HourlyWage;
import com.example.domain.model.payroll.daily.TimeRecord;
import com.example.domain.type.money.Amount;

public class DailyPayroll {

    // TODO テストクラスを用意する
    public Amount amountFor(TimeRecord timeRecord, HourlyWage hourlyWage) {
        DailyPay dailyPay = new DailyPay(timeRecord, hourlyWage);
        return dailyPay.amount();
    }

}
