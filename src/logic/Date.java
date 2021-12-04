package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Date implements Validable {
    String date;

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
        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,8));


        if ((day<1 || day >31) || (month<1 || month>12) || year<21) {
            return false;
        }

        //Pour obtenir la date actuelle
        LocalDate today = LocalDate.now();
        String current_date= today.format(DateTimeFormatter.ofPattern("dd-MM-yy"));

        int current_day = Integer.parseInt(current_date.substring(0,2));
        int current_month = Integer.parseInt(current_date.substring(3,5));
        int current_year = Integer.parseInt(current_date.substring(6,8));

        if(year<current_year || (month<current_month && year==current_year) || (day<current_day && month==current_month)) {
            return false;
        }
        return true;
    }

    // dans XMLReader new Date client.addValidable(date) etc
    // entreesorties : interface Reader et XMLReader l implémente (readclientfrom(Readable) et .. et Readable aussi interface ou classe abtraite  - demander au prof  a quoi sert interface readable
}
