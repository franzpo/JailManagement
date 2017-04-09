package sample;
/*

    Ryan Lister
    Inmate Class

    This class represents someone that is incarcerated in our jail.
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Inmate implements Comparable<Inmate>, Serializable
{

    private String firstName;
    private String lastName;
    private String inmateID;
    private double weight;
    private String ethnicity;
    private LocalDate dateArrested;
    private LocalDate dateReleased;
    private Cell cellBlock;
    private int feet;
    private int inches;
    private ArrayList<LocalDate> courtDates;
    private String status;
    private int statusNumber;

    private static long idGenerator = 900000000;


    /*
        Constructor


     */
   public Inmate(String fName, String lName, String ID, double newWeight, String newEthnicity,
                 LocalDate dateArrested, Cell cell, int feet, int inches)
   {
       firstName = fName;
       lastName = lName;
       inmateID = ID +  "";
       weight = newWeight;
       ethnicity = newEthnicity;
       this.dateArrested = dateArrested;
       cellBlock = cell;
       this.feet = feet;
       this.inches = inches;
       courtDates = new ArrayList<>();
       status = "Normal";
       statusNumber = 0;





   }



    public Inmate(String fName, String lName, String ID, double newWeight, String newEthnicity,
                  LocalDate dateArrested, Cell cell, int feet, int inches, ArrayList<LocalDate> courtDates, String status, int statusNumber)
    {
        firstName = fName;
        lastName = lName;
        inmateID = ID +  "";
        weight = newWeight;
        ethnicity = newEthnicity;
        this.dateArrested = dateArrested;
        cellBlock = cell;
        this.feet = feet;
        this.inches = inches;
        courtDates = new ArrayList<>();

        this.courtDates = courtDates;
        this.status = status;
        this.statusNumber = statusNumber;


        idGenerator++;


    }

    public Inmate(String fName, String lName, double newWeight, String newEthnicity, LocalDate dateArrested, Cell cell,
                  int feet, int inches)
    {
        firstName = fName;
        lastName = lName;
        inmateID = idGenerator + "";
        weight = newWeight;
        ethnicity = newEthnicity;
        this.dateArrested = dateArrested;
        cellBlock = cell;
        this.feet = feet;
        this.inches = inches;
        courtDates = new ArrayList<>();
        status = "Normal";
        statusNumber = 0;

        idGenerator++;
    }

    public Inmate()
    {
        firstName = "Unoccupied";
        lastName = "";
        inmateID = "1";
    }

    /*
        Getters and setters
     */
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String name)
    {
        firstName = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String name)
    {
        lastName = name;
    }

    public String getID()
    {
        return inmateID;
    }

    public void setID(String id)
    {
        inmateID = id;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double newWeight)
    {
        weight = newWeight;
    }

    public String getEthnicity()
    {
        return ethnicity;
    }

    public void setEthnicity(String newEthnicity)
    {
        ethnicity = newEthnicity;
    }

    public LocalDate getDateArrested()
    {
        return dateArrested;
    }

    public void setDateArrested(LocalDate newDate)
    {
        dateArrested = newDate;
    }

    public LocalDate getDateReleased()
    {
        return dateReleased;
    }

    public void setDateReleased(LocalDate newDate)
    {
        dateReleased = newDate;
    }

    public int getFeet()
    {
        return feet;
    }

    public void setFeet(int newFeet)
    {
        feet = newFeet;
    }

    public int getInches()
    {
        return inches;
    }

    public void setInches(int newInches)
    {
        inches = newInches;
    }

    public Cell getCellBlock()
    {
        return cellBlock;
    }

    public void setCellBlock(Cell newCell)
    {
        cellBlock = newCell;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String newStatus)
    {
        status = newStatus;
    }

    public int getStatusNumber(){return statusNumber;}

    public void setStatusNumber(int statusNum){statusNumber = statusNum;}

    public ArrayList<LocalDate> getCourtDates()
    {
        return courtDates;
    }

    public void setCourtDates(ArrayList<LocalDate> courtDates)
    {
        this.courtDates = courtDates;
    }

    // End getters and setters

    /*
        Name: compareTo
        Args: inmate

        This method overrides the compareTo method in the comparable interface.
        Overriding this method was necessary for the methods in the collections interface
     */

    @Override
    public int compareTo(Inmate o)
    {
        if (inmateID.compareTo(o.inmateID) == 0)
        {
            return 0;
        }
        else if (inmateID.compareTo(o.inmateID) == 1)
        {
            return 1;
        }
        else
        {
            return -1;

        }

    }

    /*
        Name: toString
        Args: None

        This method overrides the toString in the object class.
        It is overriden for convenience.
     */
    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

    /*
        Name: equals
        Args: Object o

        This method was overriden because the inmate roster was allowing duplicate elements.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o != null)
            return this.inmateID.equals(((Inmate)o).getID());
        else
            return false;
    }

    /*
    Name: hashCode
    Args: Object o

    This method was overriden because the inmate roster was allowing duplicate elements. Once this was implemented,
    The roster no longer allowed duplicate elements to be added. It simply takes the inmate id and parses it to an integer.
 */
    @Override public int hashCode()
    {
        return Integer.parseInt(inmateID);
    }

    public static long getIDGenerator()
    {
        return idGenerator;
    }

    public static void setIDGenerator(long id)
    {
        idGenerator = id;
    }





}

