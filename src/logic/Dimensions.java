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
        if((!lengthString.endsWith(".00")) || (!widthString.endsWith(".00"))){
            return false;
        }

        return length > 0 && width > 0 && length >= width;

    }

    @Override
    public Boolean toCompare(Validable v) {
        Dimensions dimensionsToCompare = (Dimensions)v;
        return (dimensionsToCompare.getLength() >= length) && (dimensionsToCompare.getWidth() >= width);
    }

    protected String getInitialLengthString() {return String.format("%d.00", initialLength);}

    protected String getInitialWidthString() {return String.format("%d.00", initialWidth);}

    void setDimensions(int length, int width) {
        this.length = length;
        this.width = width;
        lengthString = String.format("%d.00",length);
        widthString = String.format("%d.00",width);
    }

    protected int getInitialLength() {
        return initialLength;
    }

    protected int getInitialWidth() {
        return initialWidth;
    }

    protected int getLength() {
        return length;
    }

    protected int getWidth() {
        return width;
    }

    protected String getLengthString() {
        return lengthString;
    }

    protected String getWidthString() {
        return widthString;
    }

}
