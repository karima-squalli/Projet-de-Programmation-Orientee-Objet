package logic;

import java.util.ArrayList;

abstract class Person implements Generable, Validable{

    private final int id;
    private int woodNumber;
    private int currentLength = 0;
    private int maximumLength = 10;
    private Wood[] wood = new Wood[maximumLength];

    Person(int id) {
        this.id = id;
    }


    public void add(Wood element) {

        if (currentLength == maximumLength) {
            maximumLength = maximumLength * 2;
            Wood[] tmp = wood.clone();
            wood = new Wood[maximumLength];
            if (currentLength >= 0) System.arraycopy(tmp, 0, wood, 0, currentLength);
        }
        wood[currentLength] = element;
        currentLength++;
        woodNumber = currentLength;
    }

    public void removeP(Wood elementToBeDeleted) {
        Wood[] newWood = null;
        for (int i = 0; i < wood.length - 1; i++) {
            if (wood[i] == elementToBeDeleted) {

                newWood = new Wood[wood.length - 1];
                System.arraycopy(wood, 0, newWood, 0, i);
                if (wood.length - 1 - i >= 0) System.arraycopy(wood, i + 1, newWood, i, wood.length - 1 - i);

                break;
            }
        }

        this.wood = newWood;
        currentLength--;
    }

    public int getId() {
        return id;
    }

    public int getWoodNumber() {
        return woodNumber;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public Wood getWood(int i) {
        return wood[i];
    }

    public Boolean isValid() {

        for (int l = 0; l < currentLength; l++) {

            Wood bois = this.wood[l];

            if (!bois.checkAllValidable()) {
                removeP(bois);
                l--;
            }
        }
        return currentLength != 0;
    }

    public int getIdOwner() {
        return 0;
    }

    @Override
    public void updateGenerable(ArrayList<Validable> v) {

    }

    @Override
    public Boolean checkAllValidable() {
        return null;
    }
}
