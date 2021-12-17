package logic;

import java.util.ArrayList;
import java.util.*;

public class TestDate {

    public static void main(String[] args) {
        ArrayList<Date> dates = new ArrayList<>();
        Date date1 = new Date("22.12.22");
        Date date2 = new Date("30.02.21");
        Date date3 = new Date("12.11.21");
        Date date4 = new Date("32.12.21");
        Date date5 = new Date("20.012.22");
        Date date6 = new Date("29.02.22");  //Leap year it should contain just 28 days
        Date date7 = new Date("2c.12.22");
        dates.add(date1);
        dates.add(date2);
        dates.add(date3);
        dates.add(date4);
        dates.add(date5);
        dates.add(date6);
        dates.add(date7);
        System.out.println("-------------------------Checking the validity of the dates----------------------------");
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).isValid())
                System.out.println("Date (" + dates.get(i).getDate() + ") is valid");
            else
                System.out.println("Date (" + dates.get(i).getDate() + ") is invalid");
        }

    }
}
