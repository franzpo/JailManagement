package sample;

/**
 * Created by Ryan on 11/24/2015.
 */
import java.io.*;
import javafx.scene.control.Alert;
public class FileWriter
{
    public FileWriter(){}

    public void writeInmates()
    {

        long idNumber = Inmate.getIDGenerator();

        File outFile = new File("inmates.dat");



        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));

            out.writeLong(idNumber);

            for(Inmate inmate: InmateRoster.getRoster())
            {
                out.writeObject(inmate);
            }

            out.close();

        }
        catch(IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error while writing to the file...There may be problems with your data.");
            alertBox.show();
        }


    }

    public void writeCells()
    {
        File outFile = new File("cells.dat");

        int numMin = Jail.getNumMin();
        int numMax = Jail.getNumMax();
        int numIso = Jail.getNumIso();
        int numHospital = Jail.getNumHospital();

        try
        {
                // Create object output stream
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));

                /*
                    Run through each of the cells and write them to the file
                 */
            for (int i = 0; i < Jail.getNumMin(); i++)
            {
                out.writeObject(Jail.getMinimum().get(i));
            }

            for (int i = 0; i < Jail.getNumMax(); i++)
            {
                out.writeObject(Jail.getMaximum().get(i));
            }

            for (int i = 0; i < Jail.getNumIso();i++)
            {
                out.writeObject(Jail.getIsolation().get(i));
            }

            for (int i = 0; i < Jail.getNumHospital(); i++)
            {
                out.writeObject(Jail.getHospital().get(i));
            }

            out.close();

        }
        catch(IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error while writing to the file...There may be problems with your data.");
            alertBox.show();
        }
    }

    public void writeVisitors()
    {
        File outFile = new File("visitors.dat");

        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));

            for (int i = 0; i < VisitorsLog.getVisitorsLog().size(); i++)
            {
                out.writeObject(VisitorsLog.getVisitorsLog().get(i));
            }

            out.close();
        }
        catch(IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error while writing to the visitors file...There may be problems with your data.");
            alertBox.show();
        }
    }

    public void writeReleasedInmates()
    {
        File outputFile = new File("release.dat");

        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFile));

            for (int i = 0; i < ReleaseRoster.getReleaseRoster().size(); i++)
            {
                out.writeObject(ReleaseRoster.getReleaseRoster().get(i));
            }
        }
        catch(IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error while writing to the release roster... There may be problems with your data.");
            alertBox.show();
        }

    }


}
