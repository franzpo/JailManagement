package sample;

/*

    Ryan Lister
    InmateRoster Class

   This class represents the complete roster of inmates in the jail.
   It is static so that it can be accessed from anywhere.
 */
import java.util.HashSet;
public class InmateRoster
{
    private static HashSet<Inmate> inmateRoster = new HashSet<>();

    public static HashSet<Inmate> getRoster()
    {
        return inmateRoster;
    }

    public static boolean addInmate(Inmate inmate)
    {
        if (inmateRoster.size() < 500)
        {
            inmateRoster.add(inmate);
            return true;
        }
        else
            return false;
    }

    public static boolean removeInmate(Inmate inmate)
    {
        if (inmateRoster.contains(inmate))
        {
            inmateRoster.remove(inmate);
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getSize()
    {
        return inmateRoster.size();
    }

}
