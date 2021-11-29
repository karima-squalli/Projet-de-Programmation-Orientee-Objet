package entreessorties;

//import logic.Client;
import logic.Factory;
//import logic.Fournisseur;
import logic.Generable;

import java.util.ArrayList;

public interface Reader {

    static Reader getReader(int code){
        switch(code){
            case 0:
                return new XMLReader();
        }
        return null;
    }

    ArrayList<Generable> readGenerable(String fileName, Factory factory);
//    ArrayList<Fournisseur> ReadFournisseurs(String fileNameFournisseur);
//
//    ArrayList<Client> ReadClients(String fileNameClient);
}
