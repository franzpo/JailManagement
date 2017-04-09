package sample;
/*

    Ryan Lister
    Sheriff Booking System
    11.09.15

    This program is used as a booking system for a sheriff's department. It gives the user
    the functionality to add/remove inmates from the jail, to display their status, to
    change their status, and to add visitors to the visitors log as well as display all visitors
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;


public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
            // Create the cells
        Jail.createJail();

            // Create the file reader to get the data
        FileReader reader = new FileReader();

            // Read the inmates into the program
        reader.readInmates();

            // Read the cells into the program
        reader.readCells();

            // Read the visitors into the program
        reader.readVisitors();

            // Read the released inmates into the program
        reader.readReleaseRoster();

            // Create the pane for the window
        FlowPane pane = new FlowPane(10, 10);

            // Create the menu bar
        JailMenuBar menu = new JailMenuBar();

            // Create the main window
        MainWindow window = new MainWindow();

            // Add them to the flow pane
        pane.getChildren().addAll(menu, window);

            // Create a scene with the flowpane
        Scene scene = new Scene(pane,550, 450);

            // Set the title, scene, and show the window
        primaryStage.setTitle("Gwinnett County Sheriff's Department Booking System");
        primaryStage.setScene(scene);
        primaryStage.show();

            // When the program is closed, save the changes
        primaryStage.setOnCloseRequest(x->{

                // Create a file writer object to write the data
            FileWriter writer = new FileWriter();

                // Write all data
            writer.writeInmates();
            writer.writeCells();
            writer.writeVisitors();
            writer.writeReleasedInmates();


        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}








