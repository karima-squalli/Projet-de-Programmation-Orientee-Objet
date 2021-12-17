package logic;

public class Dimensions implements Validable,Comparable {

    private String lengthString;
    private String widthString;
    private int length;
    private int width;
    private int initialLength;
    private int initialWidth;

    public Dimensions(String lengthString, String widthString) {
        this.lengthString = lengthString;
        this.widthString = widthString;
    }

    @Override
    public Boolean isValid() {

        if (!isDouble(lengthString) || !isDouble(widthString)) {
            return false;
        }

        length = (int)Double.parseDouble(lengthString);
        width = (int)Double.parseDouble(widthString);
        initialLength = length;
        initialWidth = width;

        if (length != Double.parseDouble(lengthString) || width != Double.parseDouble(widthString)) {
            return false;
        }
        if((!lengthString.substring(lengthString.length()-3).equals(".00")) || (!widthString.substring(widthString.length()-3).equals(".00"))){
            return false;
        }

        return length > 0 && width > 0 && length >= width;

    }
    public Boolean toCompare(Validable v) {
        Dimensions dimensions_to_compare = (Dimensions)v;
        return (dimensions_to_compare.getLength() >= length) && (dimensions_to_compare.getWidth() >= width);
    }

    String getInitialLengthString() {return String.format("%d.00", initialLength);}

    String getInitialWidthString() {return String.format("%d.00", initialWidth);}

    void setDimensions(int length, int width) {
        this.length = length;
        this.width = width;
        lengthString = String.format("%d.00",length);
        widthString = String.format("%d.00",width);
    }

    int getInitialLength() {
        return initialLength;
    }

    int getInitialWidth() {
        return initialWidth;
    }

    int getLength() {
        return length;
    }

    int getWidth() {
        return width;
    }

    String getLengthString() {
        return lengthString;
    }

    String getWidthString() {
        return widthString;
    }

}
