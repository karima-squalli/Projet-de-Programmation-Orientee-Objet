package logic;

public class ClientFactory implements Factory{
    @Override
    public Generable generateGenerable() {
        Client client = new Client();
        return client;
    }

    @Override
    public Generable generateGenerable(int id) {
        Client client = new Client(id);
        return client;
    }

    @Override
    public Generable generateGenerable(int parseInt, int nombre) {
        return null;
    }

}
