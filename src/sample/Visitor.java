package sample;

/*

    Ryan Lister
    Visitor Class

    This class is used to keep track of the visitors that inmates have
 */
import java.io.Serializable;
import java.time.LocalDate;

public class Visitor implements Serializable
{
    private String firstName;
    private String lastName;
    private String SSN;
    private LocalDate dateVisited;
    private Inmate inmateVisited;

    public Visitor(){}

    public Visitor(String fName, String lName, String ssn, LocalDate dateVisited, Inmate inmate)
    {
        firstName = fName;
        lastName = lName;
        SSN = ssn;
        this.dateVisited = dateVisited;
        inmateVisited = inmate;
    }

    public void setFirstName(String fName)
    {
        firstName = fName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setLastName(String lName)
    {
        lastName = lName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setSSN(String ssn)
    {
        SSN = ssn;
    }

    public String getSSN()
    {
        return SSN;
    }

    public LocalDate getDateVisited()
    {
        return dateVisited;
    }

    public void setDateVisited(LocalDate dateVisited)
    {
        this.dateVisited = dateVisited;
    }

    public Inmate getInmateVisited()
    {
        return inmateVisited;
    }

    public void setInmateVisited(Inmate inmate)
    {
        inmateVisited = inmate;
    }
}
