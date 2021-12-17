package logic;

public interface Validable {

    Boolean isValid();

    default Boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }catch (NumberFormatException ignored) {

        }
        return false;
    }
    default Boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }catch (NumberFormatException ignored) {

        }
        return false;
    }
}
