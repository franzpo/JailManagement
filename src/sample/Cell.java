package sample;

import java.io.Serializable;

/**
 * Created by Ryan on 11/9/2015.
 */
public abstract class Cell implements Serializable
{
    protected String cellType;
    protected int cellNumber;
    public Cell() {}


    public String getCellType()
    {
        return cellType;
    }
    public  int getCellNumber()
    {
        return cellNumber;
    }
    public abstract boolean addInmate(Inmate inmate);
    public abstract boolean removeInmate(Inmate inmate);
    public abstract boolean isFull();

    @Override
    public boolean equals(Object otherCell)
    {

        return this.cellType.equals(((Cell)otherCell).cellType) && this.cellNumber == ((Cell)otherCell).cellNumber;

    }
}
