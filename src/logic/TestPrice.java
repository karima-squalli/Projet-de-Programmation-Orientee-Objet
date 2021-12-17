package logic;

import java.util.ArrayList;

 class TestPrice {

    public static void main(String[] args) {

        ArrayList<Price> arrayPrices = new ArrayList<>();

        Price price1 = new Price("500.00");
        Price price2 = new Price("-300.00");
        Price price3 = new Price("200.50");
        Price price4 = new Price("bli");
        Price price5 = new Price("400,00");
        Price price6 = new Price("220");

        arrayPrices.add(price1);
        arrayPrices.add(price2);
        arrayPrices.add(price3);
        arrayPrices.add(price4);
        arrayPrices.add(price5);
        arrayPrices.add(price6);

        for (Price price : arrayPrices) {
            if (price.isValid())
                System.out.println("The price " + "(" + price.getPriceString() + ") is valid.");
            else
                System.out.println("The price " + "(" + price.getPriceString() + ") is invalid.");
        }
    }
}
