package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Date implements Validable,Comparable {

    private String date;
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

        if (day == 29 && month==2){
            if(Math.floorMod(year,4)!=0)
                return false;
        }
        //To obtain the actual date
        LocalDate today = LocalDate.now();
        String current_date= today.format(DateTimeFormatter.ofPattern("dd-MM-yy"));

        int current_day = Integer.parseInt(current_date.substring(0,2));
        int current_month = Integer.parseInt(current_date.substring(3,5));
        int current_year = Integer.parseInt(current_date.substring(6,8));

        return year >= current_year && (month >= current_month || year != current_year) && (day >= current_day || month != current_month);
    }

    public Boolean toCompare(Validable v) {
        Date date_to_compare = (Date)v;

        int day_to_compare = date_to_compare.day;
        int month_to_compare = date_to_compare.month;
        int year_to_compare = date_to_compare.year;

        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,8));

        return (year_to_compare <= year) && (year_to_compare != year || month_to_compare <= month) && (year_to_compare != year || month_to_compare != month || day_to_compare <= day);
    }
    String getDate(){
        return date;
    }
}
