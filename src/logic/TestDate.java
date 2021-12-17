package logic;

import java.util.ArrayList;

class TestDate {

    public static void main(String[] args) {

        ArrayList<Date> arrayDates = new ArrayList<>();
        Date date1 = new Date("22.12.22");
        Date date2 = new Date("30.02.21");
        Date date3 = new Date("12.11.21");
        Date date4 = new Date("32.12.21");
        Date date5 = new Date("20.012.22");
        Date date6 = new Date("29.02.22");
        Date date7 = new Date("2c.12.22");
        Date date8 = new Date("12/12/23");

        arrayDates.add(date1);
        arrayDates.add(date2);
        arrayDates.add(date3);
        arrayDates.add(date4);
        arrayDates.add(date5);
        arrayDates.add(date6);
        arrayDates.add(date7);
        arrayDates.add(date8);

        for (Date date : arrayDates) {
            if (date.isValid())
                System.out.println("Date (" + date.getDate() + ") is valid.");
            else
                System.out.println("Date (" + date.getDate() + ") is invalid.");
        }

    }
}
