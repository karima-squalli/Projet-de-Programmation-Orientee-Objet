package logic;

import java.util.ArrayList;

public class TestPrice {
    public static void main(String[] args) {
        ArrayList<Price> prices = new ArrayList<>();
        Price price1 = new Price("200.00");
        Price price2 = new Price("-200.00");
        Price price3 = new Price("200.50");
        Price price4 = new Price("bli");
        Price price5 = new Price("200,00");
        Price price6 = new Price("200");
        // System.out.printf("%s\n",(String)price1.charAt(priceString.length()-1))
        prices.add(price1);
        prices.add(price2);
        prices.add(price3);
        prices.add(price4);
        prices.add(price5);
        prices.add(price6);
        System.out.println("-------------------------Checking the validity of the prices----------------------------");
        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).isValid())
                System.out.println("The price " +"("+ prices.get(i).getPriceString() + ") is valid");
            else
                System.out.println("The price " + "("+prices.get(i).getPriceString() + ") is invalid");
        }


    }
}
