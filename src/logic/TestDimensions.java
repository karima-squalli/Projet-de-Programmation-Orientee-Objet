package logic;

import java.util.ArrayList;

public class TestDimensions {

    public static void main(String[] args) {

        ArrayList<Dimensions> arrayDimensions = new ArrayList<>();

        Dimensions dimensions1 = new Dimensions("300", "200");
        Dimensions dimensions2 = new Dimensions("200", "350");
        Dimensions dimensions3 = new Dimensions("100.00", "50.00");
        Dimensions dimensions4 = new Dimensions("100.01", "70.00");
        Dimensions dimensions5 = new Dimensions("200.00", "200.00");
        Dimensions dimensions6 = new Dimensions("bli", "10.00");
        Dimensions dimensions7 = new Dimensions("90.00", "bli");

        arrayDimensions.add(dimensions1);
        arrayDimensions.add(dimensions2);
        arrayDimensions.add(dimensions3);
        arrayDimensions.add(dimensions4);
        arrayDimensions.add(dimensions5);
        arrayDimensions.add(dimensions6);
        arrayDimensions.add(dimensions7);

        for (int i=0; i<arrayDimensions.size(); i++) {
            Dimensions dimensions = arrayDimensions.get(i);
            if (dimensions.isValid())
                System.out.println("The dimensions (" + dimensions.getLengthString() + "," + dimensions.getWidthString() + ") are valid.");
            else
                System.out.println("The dimensions (" + dimensions.getLengthString() + "," + dimensions.getWidthString() + ") are invalid.");
        }
    }
}
