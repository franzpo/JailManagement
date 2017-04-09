package sample;

/**
 * Created by Ryan on 11/24/2015.
 */
import javafx.scene.control.Alert;

import java.io.*;
public class FileReader {

    public FileReader() {
    }

    public void readInmates() throws ClassNotFoundException {

        File inputFile = new File("inmates.dat");

        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(inputFile));

            Inmate.setIDGenerator(in.readLong());

            try
            {
                while (true)
                {
                    Inmate inmate = (Inmate) in.readObject();


                    InmateRoster.addInmate(inmate);

                }
            } catch (EOFException ex) {
                System.out.println("All inmates have been read...");
            }

            in.close();


        } catch (IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error reading the inmates...");
            alertBox.show();
        }

    }

    public void readCells() throws ClassNotFoundException {
        File inFile = new File("cells.dat");

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(inFile));

            for (int i = 0; i < Jail.getNumMin(); i++) {
                NormalCell cell = (NormalCell) (in.readObject());
                Jail.getMinimum().set(i, cell);
            }

            for (int i = 0; i < Jail.getNumMax(); i++) {
                NormalCell cell = (NormalCell) (in.readObject());
                Jail.getMaximum().set(i, cell);
            }

            for (int i = 0; i < Jail.getNumIso(); i++) {
                SpecialCell cell = (SpecialCell) (in.readObject());
                Jail.getIsolation().set(i, cell);
            }

            for (int i = 0; i < Jail.getNumHospital(); i++) {
                SpecialCell cell = (SpecialCell) (in.readObject());
                Jail.getHospital().set(i, cell);
            }

            in.close();


        }
        catch (EOFException ex)
        {
            System.out.println("All cells have been read...");

        }
        catch (IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error reading the cells...");
            alertBox.show();

        }
        finally
        {
            System.out.println("All cells have been read...");
        }
    }

    public void readVisitors() throws ClassNotFoundException
    {
        File inFile = new File("visitors.dat");

        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(inFile));

            try
            {
                while (true)
                {
                    Visitor visitor = (Visitor)(in.readObject());

                    VisitorsLog.getVisitorsLog().add(visitor);
                }
            }
            catch(EOFException ex)
            {
                System.out.println("All visitors have been read...");
            }

            in.close();

        }
        catch(IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error reading the visitors...");
            alertBox.show();
        }



    }

    public void readReleaseRoster() throws ClassNotFoundException
    {
        File inFile = new File("release.dat");

        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(inFile));

            try
            {
                while (true)
                {
                    Inmate releasedInmate = (Inmate)(in.readObject());

                    ReleaseRoster.getReleaseRoster().add(releasedInmate);
                }
            }
            catch(EOFException ex)
            {
                System.out.println("All released inmates have been read...");
            }

            in.close();

        }
        catch(IOException ex)
        {
            Alert alertBox = new Alert(Alert.AlertType.ERROR, "There was an error reading the released inmates...");
            alertBox.show();
        }
    }
}
