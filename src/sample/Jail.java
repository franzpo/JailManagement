package sample;

/*

    Ryan Lister
    Jail Class

    This class represents the jail. It holds four array lists. One for minimum,
    one for maximum, one for isolation, and the last for hospital. When the object is created,
    it populates the array lists based on the number of cells each one is supposed to have.
    The arraylists are static so that they can be called wherever they may need to be called without
    passing the jail as an argument.


 */
import java.util.ArrayList;
public class Jail
{
    private static ArrayList<NormalCell> minimum;
    private static ArrayList<NormalCell> maximum;
    private static ArrayList<SpecialCell> isolation;
    private static ArrayList<SpecialCell> hospital;

    private static int numMin;
    private static int numMax;
    private static int numIso;
    private static int numHospital;

    public Jail()
    {

    }

    public static void createJail()
    {
        minimum = new ArrayList<>();
        maximum = new ArrayList<>();
        isolation = new ArrayList<>();
        hospital = new ArrayList<>();

        numMin = 200;
        numMax = 50;
        numIso = 10;
        numHospital = 50;

        for (int i = 0; i < numMin;i++)
        {
            minimum.add(new NormalCell("Minimum", (i + 1)));
        }

        for (int i = 0; i < numMax; i++)
        {
            maximum.add(new NormalCell("Maximum", (i + 1)));
        }

        for (int i = 0; i < numIso; i++)
        {
            isolation.add(new SpecialCell("Isolation", (i + 1)));
        }

        for (int i = 0; i < numHospital; i++)
        {
            hospital.add(new SpecialCell("Hospital", (i + 1)));
        }
    }

    public static ArrayList<NormalCell> getMinimum()
    {
        return minimum;
    }

    public static ArrayList<NormalCell> getMaximum()
    {
        return maximum;
    }

    public static ArrayList<SpecialCell> getIsolation()
    {
        return isolation;
    }

    public static ArrayList<SpecialCell> getHospital()
    {
        return hospital;
    }

    public static int getNumMax()
    {
        return numMax;
    }

    public static int getNumMin()
    {
        return numMin;
    }

    public static int getNumIso()
    {
        return numIso;
    }

    public static int getNumHospital() {return numHospital;}

}
