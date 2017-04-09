package sample;

/*

    Ryan Lister
    Visitors Class

    This class represents the visitors log. It holds an array list of
    visitors that can be added to.
 */
import java.util.ArrayList;
public class VisitorsLog
{
    private static ArrayList<Visitor> visitorsLog = new ArrayList<>();

    public static ArrayList<Visitor> getVisitorsLog()
    {
        return visitorsLog;
    }
}
