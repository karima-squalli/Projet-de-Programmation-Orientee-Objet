package logic;

public class Cut {

    private final String x;
    private final String y;
    private final String idBoard;
    private final String idPanel;
    private final int idClient;
    private final int idSupplier;
    private final String length;
    private final String width;
    private final String x1;
    private final String y1;

    protected Cut(String x1, String y1, String length, String width, String x, String y, int idClient, String idBoard, int idSupplier, String idPanel) {
        this.x = x;
        this.y = y;
        this.idClient = idClient;
        this.idBoard = idBoard;
        this.idSupplier = idSupplier;
        this.idPanel = idPanel;
        this.length = length;
        this.width = width;
        this.x1 = x1;
        this.y1 = y1;
    }

    protected int getIdClient(){
        return idClient;
    }
    public int getIdSupplier(){
        return idSupplier;
    }
    public String getX(){
        return x;
    }
    public String getY(){
        return y;
    }
    protected String getIdBoard(){
        return idBoard;
    }
    public String getIdPanel(){
        return idPanel;
    }


    public String getLength() {
        return length;
    }

    public String getWidth() {
        return width;
    }

    public String getX1() {
        return x1;
    }

    public String getY1() {
        return y1;
    }
}
