package entreessorties;

import logic.Factory;
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

    ArrayList<Generable> readGenerable(ArrayList<String> data, String fileName, Factory f1, Factory f2);
}
