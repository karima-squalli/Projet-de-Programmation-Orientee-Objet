package logic;

public interface Factory {

     Generable generateGenerable();

    Generable generateGenerable(int id);

    Generable generateGenerable(int id, int number, int idOwner);
}
