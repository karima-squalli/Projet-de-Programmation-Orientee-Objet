package logic;
import logic.Comparable;
import logic.Validable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Decoupe {
    private String x;
    private String y;
    private String idPlanche;
    private String idPanneau;
    private int idClient;
    private int idFournisseur;

    public Decoupe(String x, String y, int idClient, String idPlanche, int idFournisseur, String idPanneau) {
        this.x = x;
        this.y = y;
        this.idClient = idClient;
        this.idPlanche = idPlanche;
        this.idFournisseur = idFournisseur;
        this.idPanneau = idPanneau;
    }

    public int getIdClient(){
        return idClient;
    }
    public int getIdFournisseur(){
        return idFournisseur;
    }
    public String getX(){
        return x;
    }
    public String getY(){
        return y;
    }
    public String getIdPlanche(){
        return idPlanche;
    }
    public String getIdPanneau(){
        return idPanneau;
    }





}
