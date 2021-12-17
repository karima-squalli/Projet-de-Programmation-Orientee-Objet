package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Date implements Validable,Comparable {

    private final String date;
    private int day;
    private int month;
    private int year;

    public Date(String date){
        this.date=date;
    }


    @Override
    public Boolean isValid() {

        if (date.charAt(2) != '.' || date.charAt(5) != '.') {
            return false;
        }
        if (!isInteger(date.substring(0, 2)) || !isInteger(date.substring(3, 5)) || !isInteger(date.substring(6, 8))) {
            return false;
        }
        day = Integer.parseInt(date.substring(0,2));
        month = Integer.parseInt(date.substring(3,5));
        year = Integer.parseInt(date.substring(6,8));


        if ((day<1 || day >31) || (month<1 || month>12) || year<21) {
            return false;
        }

        //To deal with the leap year case
        if (day == 29 && month==2){
            if(Math.floorMod(year,4)!=0)
                return false;
        }
        //To obtain the actual date
        LocalDate today = LocalDate.now();
        String currentDate= today.format(DateTimeFormatter.ofPattern("dd-MM-yy"));

        int currentDay = Integer.parseInt(currentDate.substring(0,2));
        int currentMonth = Integer.parseInt(currentDate.substring(3,5));
        int currentYear = Integer.parseInt(currentDate.substring(6,8));

        return year >= currentYear && (month >= currentMonth || year != currentYear) && (day >= currentDay || month != currentMonth);
    }

    public Boolean toCompare(Validable v) {
        Date dateToCompare = (Date)v;

        int dayToCompare = dateToCompare.day;
        int monthToCompare = dateToCompare.month;
        int yearToCompare = dateToCompare.year;

        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,8));

        return (yearToCompare <= year) && (yearToCompare != year || monthToCompare <= month) && (yearToCompare != year || monthToCompare != month || dayToCompare <= day);
    }
    protected String getDate(){
        return date;
    }
}
