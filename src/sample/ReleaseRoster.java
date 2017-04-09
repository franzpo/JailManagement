package sample;

import java.util.ArrayList;

/**
 * Created by Ryan on 11/18/2015.
 */
public class ReleaseRoster
{
    private static ArrayList<Inmate> releaseRoster = new ArrayList<>();

    public static ArrayList<Inmate> getReleaseRoster()
    {
        return releaseRoster;
    }
}
