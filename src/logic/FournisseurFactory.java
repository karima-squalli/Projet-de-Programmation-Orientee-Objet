package logic;

public class FournisseurFactory implements Factory{
    @Override
    public Generable generateGenerable() {
        Fournisseur fournisseur= new Fournisseur();
        return fournisseur;
    }

    @Override
    public Generable generateGenerable(int id) {
        Fournisseur fournisseur= new Fournisseur(id);
        return fournisseur;
    }

    @Override
    public Generable generateGenerable(int id, int nombre) {
        return null;
    }
}
