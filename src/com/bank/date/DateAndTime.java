package com.bank.date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class DateAndTime {
    private int hour;
    private int minute;
    private int second;
    private int year;
    private Month month;
    private int monthPerYear;
    private int day;

    private int[] daysPerMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } ;

    public DateAndTime(int year, int month, int day) {
        checkIfMonthIsLesserThanZeroAndItGreaterThanTwelve(month);
        validateDaysInMonth(month, day);
        validateLeapYear(year, month, day);

        this.year = year;
        this.monthPerYear = month;
        this.day = day;
    }

    public DateAndTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        this.hour = time.getHour();
        this.minute = time.getMinute();
        this.second = time.getSecond();
        this.year = date.getYear();
        this.month = date.getMonth();
        this.day = date.getDayOfMonth();
    }

    public String dateOfBirthToString(){
        return String.format("%d/%d/%d", day, monthPerYear, year);
    }

    private void validateLeapYear(int year, int month, int day) {
        if(month == 2 && day > 28 && !(year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)){
            String errorMessage = String.format("Day (%d) out of range for the specified month and year");
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateDaysInMonth(int month, int day) {
        if(day <= 0 || day > daysPerMonth[month] && !(month == 2 && day > 28)){
            String errorMessage = String.format("Day (%d) out of range for the specified month and year");
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void checkIfMonthIsLesserThanZeroAndItGreaterThanTwelve(int month) {
        if(month <= 0 || month > 12){
            String errorMessage = String.format("Day (%d) out of range for the specified month and year");
            throw new IllegalArgumentException(errorMessage);
        }
    }


    public String timeToStringFormat(){
        return String.format("%d:%02d:%02d %s", twelveHourFormat(), minute, second, timeMeridian());
    }

    private int twelveHourFormat(){
        if(hour == 0 || hour == 12){
            hour = 12;
        }else {
            hour = hour % 12;
        }
        return hour;
    }

    private String timeMeridian(){
        if (hour < 12)
            return "am";
        else
            return "pm";
    }

    public String dateToStringFormat(){
        return String.format("%s %d, %d", month.toString().toLowerCase(), day, year);
    }
}
