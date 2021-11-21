package interfaces;

import logic.Client;
import logic.Fournisseur;

import java.util.ArrayList;

public interface Reader {
    ArrayList<Fournisseur> ReadFournisseursFrom(String fileNameFournisseur);

    ArrayList<Client> ReadClientsFrom(String fileNameClient);
}
