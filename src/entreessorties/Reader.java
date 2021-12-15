package entreessorties;

import logic.Factory;
import logic.Generable;

import java.util.ArrayList;

public interface Reader {

    static Reader getReader(int code){
        return switch (code) {
            case 0 -> new XMLReader();
            default -> null;
        };
    }

    ArrayList<Generable> readGenerable(ArrayList<String> data, String fileName, Factory f1, Factory f2);
}
