package logic;

public class Price implements Validable,Comparable{

    private final String priceString;
    private double price;

    public Price(String priceString) {
        this.priceString = priceString;
    }

    @Override
    public Boolean isValid() {

        if (!isDouble(priceString)) {
            return false;
        }
        price = Double.parseDouble(priceString);
        if (priceString.charAt(priceString.length() - 3) != '.')
            return false;
        return !(price <= 0);

    }

    public Boolean toCompare(Validable v) {
        Price price_to_compare = (Price)v;
        return !(price_to_compare.price > price);
    }

    protected String getPriceString(){
        return priceString;
    }
}
