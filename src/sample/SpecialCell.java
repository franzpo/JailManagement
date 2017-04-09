package sample;

/*

    Ryan Lister
    SpecialCell Class

    This class represents the isolation/hospital cells. It extends Cell in order to have
    access to the Cell member methods/variables.
 */
public class SpecialCell extends Cell
{
    private Inmate occupant;

    public SpecialCell(String cellType, int cellNumber)
    {
        this.cellType = cellType;
        this.cellNumber = cellNumber;
        occupant = null;
    }

    public Inmate getOccupant()
    {
        if (occupant != null)
            return occupant;
        else
            return new Inmate();
    }

    @Override
    public boolean isFull()
    {
        if (occupant != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean addInmate(Inmate inmate)
    {
        if (occupant == null)
        {
            occupant = inmate;
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean removeInmate(Inmate inmate)
    {
        if (occupant.compareTo(inmate) == 0)
        {
            occupant = null;
            return true;
        }
        else
            return false;
    }


}
