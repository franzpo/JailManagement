package sample;

/*

    Ryan Lister
    NormalCell class

    This class extends from the abstract class cell. It implements the methods for cell, and
    adds getters/setters for top bunk and bottom bunk.
 */
public class NormalCell extends Cell
{
    private Inmate topBunk;
    private Inmate bottomBunk;

    public NormalCell(String cellType, int cellNumber)
    {
        this.cellType = cellType;
        this.cellNumber = cellNumber;
        topBunk = null;
        bottomBunk = null;
    }

    /*
        Name: addInmate
        Args: Inmate inmate

        This method attempts to add an inmate to the cell. If the cell is full,
        it returns false to indicate that the inmate was not added.
     */
    @Override
    public boolean addInmate(Inmate inmate)
    {
        if (isFull())
        {
            return false;
        }
        else
        {
            if (bottomBunk == null)
            {
                bottomBunk = inmate;
                return true;
            }
            else
            {
                topBunk = inmate;
                return true;
            }

        }
    }

    /*
        Name: removeInmate
        Args: Inmate inmate

        This method attempts to remove an inmate to the cell. If there was some sort of error,
        the method returns false to indicate that the inmate was not removed.
        */
    @Override
    public boolean removeInmate(Inmate inmate)
    {
        if (bottomBunk.compareTo(inmate) == 0)
        {
            bottomBunk = null;
            return true;
        }
        else if (topBunk.compareTo(inmate) == 0)
        {
            topBunk = null;
            return true;

        }
        else
            return false;
    }

    /*
        Name: isFull
        Args: None

        This method checks the status of the cell. If the cell is full, it
        returns true. Otherwise, it returns false.

     */
    @Override
    public boolean isFull()
    {
        if (topBunk != null && bottomBunk != null)
        {
            return true;
        }
        else
            return false;
    }



    public Inmate getTopBunk()
    {
        if (topBunk != null)
            return topBunk;
        else
            return new Inmate();

    }


    public void setTopBunk(Inmate inmate)
    {
        topBunk = inmate;
    }

    public Inmate getBottomBunk()
    {
        if (bottomBunk != null)
            return bottomBunk;
        else
            return new Inmate();
    }

    public void setBottomBunk(Inmate inmate)
    {
        bottomBunk = inmate;
    }



}
