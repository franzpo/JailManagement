package sample;
/*
    Ryan Lister
    MainWindow class

    This class creates the greeting for the user, and also provides
    a search bar to query the database
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainWindow extends VBox
{
    public MainWindow()
    {
        createPane();
    }

    public MainWindow createPane()
    {
            // Cretae a label
        Label mainLabel = new Label("Gwinnett County Sheriff's Department");

            // Create a pane for the label
        HBox mainLabelBox = new HBox(10);


            // Set the alignment
        mainLabelBox.setAlignment(Pos.CENTER);

            // Add the label
        mainLabelBox.getChildren().add(mainLabel);

            // Set the font for the main label
        mainLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

            // Create a pane for the greeting
        HBox greeting = new HBox(10);

            // Set padding for the greeting pane
        greeting.setPadding(new Insets(10,10,10,10));

            // Create a label that describes the program
        Label greetLabel = new Label("Welcome to the Gwinnett County Sheriff's Department Booking System!" +
        "\nUse the menu bar at the top of the screen to navigate to different menus. If you would like to" +
        "\nsearch for an inmate, enter their name to see if they are in the Jail.");

            // Add the greeting label to the greeting pane
        greeting.getChildren().add(greetLabel);

            // Set the alignment
        greeting.setAlignment(Pos.CENTER);

            // Create a text field to get input from the user
        TextField query = new TextField();

            // Set the prompt text in the query box
        query.setPromptText("Enter your query here...");

            // Create a pane for the query bar
        HBox searchBox = new HBox(10);

            // Set the alignment
        searchBox.setAlignment(Pos.CENTER);

            // Set padding for the pane
        searchBox.setPadding(new Insets(5,5,5,5));

            // Create a button for the search bar
        Button search = new Button("Search");

            // Add the serach button and the text field to the pane
        searchBox.getChildren().addAll(new Label("Search query"), query, search);

            // Define the search button actions
        search.setOnAction(e -> {

                // Create a new stage
            Stage newStage = new Stage();

                // Create a pane for the window
            VBox frame = new VBox(10);

                // Create an observable list for our matched inmates
            ObservableList<Inmate> inmateList = FXCollections.observableArrayList();

                // For loop to step through the hash set
            for (Inmate inmate : InmateRoster.getRoster())
            {
                    // if the inmates first name matches the query
                    // This uses to lower case and contains to return anything with a partial match
                if (inmate.getFirstName().toLowerCase().contains(query.getText().toLowerCase()))
                {
                    inmateList.add(inmate);
                }
                else if (inmate.getLastName().toLowerCase().contains(query.getText().toLowerCase()))
                {
                    inmateList.add(inmate);
                }
            }

                // Create a list view to hold the matched inmates
            ListView<Inmate> matchedInmates = new ListView<>(inmateList);

                // Create a label for the new window
            Label searchLabel = new Label("Matching results");

                // Set the font for the header label
            searchLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

                // Set the alignment for the label
            searchLabel.setAlignment(Pos.CENTER);

                // Set the alignment for the main pane
            frame.setAlignment(Pos.CENTER);

                // Set the padding for the main pane
            frame.setPadding(new Insets(5, 5, 5, 5));

                // Create a pane for the buttons
            HBox btnBox = new HBox(10);

                // Set the padding on the button box
            btnBox.setPadding(new Insets(5, 5, 5, 5));

                // Create an OK button
            Button btnOk = new Button("OK");

                // Create a cancel button
            Button btnCancel = new Button("Cancel");

                // Set the action for cancel
            btnCancel.setOnAction(o->newStage.close());

                // Define the ok button action. It does the same thing as the view inmate action in JailMenuBar
            btnOk.setOnAction(y -> {

                // Create an inmate so we can easily access the information of the selcted inmate
                Inmate tempInmate = matchedInmates.getSelectionModel().getSelectedItem();

                // Display the inmate info in a different stage
                Stage thirdStage = new Stage();

                // Create a grid pane to hold the view
                GridPane inmateInfo = new GridPane();

                // Set some formatting for the gridpane
                inmateInfo.setVgap(10);
                inmateInfo.setHgap(10);
                inmateInfo.setPadding(new Insets(5, 5, 5, 5));

                // Label to hold the name
                Label nameLabel = new Label(tempInmate.getFirstName() + " " + tempInmate.getLastName());

                // Add name label and another label describing the field
                inmateInfo.addRow(0, new Label("Name"), nameLabel);

                // Create the ID label to hold the inmates id
                Label idLabel = new Label(tempInmate.getID());

                // Create new label describing the information
                inmateInfo.addRow(1, new Label("Inmate ID"), idLabel);

                // Create new label for the weight
                Label weightLabel = new Label(String.format("%4s lbs", tempInmate.getWeight()));

                // Create new label for the height
                Label heightLabel = new Label(String.format("%4s'%4s''", tempInmate.getFeet(), tempInmate.getInches()));

                // Add weight to the pane
                inmateInfo.addRow(2, new Label("Weight"), weightLabel);

                // Create cell label for the inmates cell
                Label cellLabel = new Label(tempInmate.getCellBlock().getCellType());

                // Create number label
                Label numberLabel = new Label(String.format("%4d", tempInmate.getCellBlock().getCellNumber()));

                // Add the height to the pane
                inmateInfo.addRow(3, new Label("Height"), heightLabel);

                // Add the cell information to the pane
                inmateInfo.addRow(4, new Label("Cell Block"), cellLabel, new Label("Cell Number"), numberLabel);

                // Add a label for the status of the inmate
                Label statusLabel = new Label(tempInmate.getStatus());

                // Create a button to update the inmates status
                Button btnUpdateStatus = new Button("Update Status");

                // Add the label and button to the pane
                inmateInfo.addRow(5, new Label("Status"), statusLabel, btnUpdateStatus);

                // Create a label that holds the inmates ethnicity
                Label ethnicityLabel = new Label(tempInmate.getEthnicity());

                // Add the label and create a new label
                inmateInfo.addRow(6, new Label("Ethnicity"), ethnicityLabel);

                // Create a label for the date arrested
                Label dateArrestedLabel = new Label(tempInmate.getDateArrested().toString());

                // Add the label to the grid pane
                inmateInfo.addRow(7, new Label("Date Arrested"), dateArrestedLabel);

                // Create a text area for the court dates
                TextArea courtAppointments = new TextArea();

                // Set the court dates section to not be edited
                courtAppointments.setEditable(false);

                // Set a preferred column count
                courtAppointments.setPrefColumnCount(12);

                // Add a label describing the court dates to the grid pane
                inmateInfo.addRow(8, new Label("Court Dates"));

                // Add the text area to the grid pane
                inmateInfo.addRow(9, courtAppointments);

                // If the inmate has court dates, print them, if not, display that no dates are scheduled
                if (tempInmate.getCourtDates().size() > 0)
                {
                    for (int i = 0; i < tempInmate.getCourtDates().size(); i++)
                        courtAppointments.appendText(tempInmate.getCourtDates().get(i).toString() + "\n");
                }
                else
                    courtAppointments.setText("No court dates scheduled");

                // Create a button to add a court date
                Button btnAddCourtDate = new Button("Add Court Date");


                // Define the update status action
                btnUpdateStatus.setOnAction(ev->{

                    // Create a stage to display the update status menu
                    Stage fourthStage = new Stage();

                    // HBox to hold the buttons
                    HBox statusBox = new HBox(10);

                    // Create buttons representing status
                    Button btnHospital = new Button("Hospital");
                    Button btnIsolation = new Button("Isolation");
                    Button btnNormal = new Button("Normal");

                    // Define the hospital button action
                    btnHospital.setOnAction(h-> {

                        if (tempInmate.getStatus().equals("Isolation"))
                            Jail.getIsolation().get((tempInmate.getStatusNumber() - 1)).removeInmate(tempInmate);

                        // Loop through the isolation cells til we find an empty one
                        for (int i = 0; i < Jail.getNumHospital();i++)
                        {
                            // If the cell is not full, add the inmate to it
                            if(!Jail.getHospital().get(i).isFull())
                            {
                                // Set the inmates status number to the available cell number
                                tempInmate.setStatusNumber(Jail.getHospital().get(i).getCellNumber());

                                // Add the inmate to the isolation cell
                                Jail.getHospital().get(i).addInmate(tempInmate);

                                tempInmate.setStatus("Hospital");



                                // Adjust the inmates status label
                                statusLabel.setText("Hospital");

                                // Close the stage since we have updated status
                                fourthStage.close();
                                break;
                            }
                        }


                        // Consume the event
                        h.consume();
                    });

                    // Define the isolation status action
                    btnIsolation.setOnAction(h-> {

                        if (tempInmate.getStatus().equals("Hospital"))
                            Jail.getHospital().get((tempInmate.getStatusNumber() - 1)).removeInmate(tempInmate);


                        // Loop through the isolation cells til we find an empty one
                        for (int i = 0; i < Jail.getNumIso();i++)
                        {
                            // If the cell is not full, add the inmate to it
                            if(!Jail.getIsolation().get(i).isFull())
                            {
                                // Set the inmates status number to the available cell number
                                tempInmate.setStatusNumber(Jail.getIsolation().get(i).getCellNumber());

                                // Add the inmate to the isolation cell
                                Jail.getIsolation().get(i).addInmate(tempInmate);

                                // Adjust the inmates status label
                                statusLabel.setText("Isolation");

                                tempInmate.setStatus("Isolation");



                                // Close the stage since we have updated status
                                fourthStage.close();
                                break;
                            }
                        }


                        // Consume the event
                        h.consume();

                    });

                    // Define the normal status button action
                    btnNormal.setOnAction(even->{

                        if (tempInmate.getStatus().compareTo("Hospital") == 0)
                        {
                            Jail.getHospital().get((tempInmate.getStatusNumber() - 1)).removeInmate(tempInmate);
                        }
                        else if (tempInmate.getStatus().compareTo("Isolation") == 0)
                        {
                            Jail.getIsolation().get((tempInmate.getStatusNumber() - 1)).removeInmate(tempInmate);
                        }

                        // Set the status to normal
                        tempInmate.setStatus("Normal");

                        // Set the status label to normal
                        statusLabel.setText("Normal");

                        // Since the inmates normal cell never changes, we just set their
                        // status number to zero
                        tempInmate.setStatusNumber(0);

                        // Close the stage
                        fourthStage.close();

                        // Consume the event
                        even.consume();
                    });

                    // Label that serves as instructions for the user
                    Label statusGreeting = new Label("Select Inmate's new status by clicking on the button.");

                    // VBox to hold everything
                    VBox masterBox = new VBox(10);

                    // Set the alignment
                    masterBox.setAlignment(Pos.CENTER);

                    // Set the padding
                    masterBox.setPadding(new Insets(5,5,5,5));

                    // Add everything to the VBox
                    masterBox.getChildren().addAll(statusGreeting, statusBox);

                    // Add the buttons to the status box
                    statusBox.getChildren().addAll(btnHospital, btnIsolation, btnNormal);

                    // Adjust the alignment
                    statusBox.setAlignment(Pos.CENTER);

                    // Set padding for the buttons
                    statusBox.setPadding(new Insets(5,5,5,5));

                    // Create a new scene
                    Scene newScene = new Scene(masterBox, 350, 250);

                    // Set the scene in the new stage and show it
                    fourthStage.setScene(newScene);
                    fourthStage.setTitle("Update Status");
                    fourthStage.show();

                    // Consume the event
                    ev.consume();

                });


                // Define the add court date buttons action
                btnAddCourtDate.setOnAction(p->{

                    // Create a new stage
                    Stage courtStage = new Stage();

                    // Create a date picker to select a court date
                    DatePicker datePicker = new DatePicker();

                    // Set the default value of the date picker to today's date
                    datePicker.setValue(LocalDate.now());

                    // Create a vbox to hold the date picker
                    VBox courtDateBox = new VBox(10);

                    // Hbox to hold the buttons that control the add court date menu
                    HBox courtDateBtnBox = new HBox(10);

                    // Create ok button
                    Button btnCourtOk = new Button("OK");

                    // Create cancel button
                    Button btnCourtCancel = new Button("Cancel");

                    // Add the buttons to the hbox
                    courtDateBtnBox.getChildren().addAll(btnCourtOk, btnCourtCancel);

                    // Set the alignment for the date picker
                    courtDateBox.setAlignment(Pos.CENTER);

                    // Set the alignment for the buttons
                    courtDateBtnBox.setAlignment(Pos.BOTTOM_RIGHT);

                    // Create some padding for both boxes
                    courtDateBox.setPadding(new Insets(5,5,5,5));
                    courtDateBtnBox.setPadding(new Insets(5,5,5,5));

                    // Add everything to the court date box
                    courtDateBox.getChildren().addAll(new Label("Select a Court Date for the Inmate"), datePicker, courtDateBtnBox);


                    // Define the ok buttons action
                    btnCourtOk.setOnAction(z-> {

                        // Add the court date to the inmates court date schedule
                        tempInmate.getCourtDates().add(datePicker.getValue());

                        // Close the stages
                        courtStage.close();
                        thirdStage.close();

                        // Notify user that the court date was added successfully
                        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Court Date Successfully added");
                        alertBox.show();
                    });

                    // Define the cancel button's action
                    btnCourtCancel.setOnAction(q->courtStage.close());

                    // Set the title of the stage and display it
                    courtStage.setTitle("Add Court Date");
                    Scene newScene = new Scene(courtDateBox, 250, 250);
                    courtStage.setScene(newScene);
                    courtStage.show();


                });

                // Add the button
                inmateInfo.addRow(10, btnAddCourtDate);

                // Create ok button
                Button btnSearchOk = new Button("OK");

                // Create edit button
                Button btnSearchEdit = new Button("Edit");

                // Create release button
                Button btnSearchRelease = new Button("Release");

                HBox controlBox = new HBox(10);

                controlBox.getChildren().addAll(btnSearchOk, btnSearchEdit, btnSearchRelease);

                VBox masterBox = new VBox(10);
                masterBox.getChildren().addAll(inmateInfo, controlBox);

                controlBox.setAlignment(Pos.BOTTOM_RIGHT);

                masterBox.setPadding(new Insets(5,5,5,5));

                btnSearchEdit.setOnAction(c->{

                    Stage anotherStage = new Stage();


                    // VBox to hold everything
                    VBox masterPane = new VBox(10);

                    // HBox to hold buttons
                    HBox box = new HBox(10);

                    // Grid pane to hold data
                    GridPane editPane = new GridPane();

                    // Create text field and populate it with the inmates name
                    TextField tfFirstName = new TextField();
                    tfFirstName.setText(tempInmate.getFirstName());

                    // Create text field and populate it with the inmates name
                    TextField tfLastName = new TextField();
                    tfLastName.setText(tempInmate.getLastName());

                    // Create text field for the inmates weight
                    TextField tfWeight = new TextField();
                    tfWeight.setText(String.format("%4.1f", tempInmate.getWeight()));

                    // Create a combo box for the inmates height
                    ComboBox<Integer> comboFeet = new ComboBox<>();

                    // Populate the combo box
                    for (int i = 1; i < 9; i++)
                    {
                        comboFeet.getItems().add(i);
                    }

                    // Set the value to the inmates height
                    comboFeet.setValue(tempInmate.getFeet());

                    // Create a combo box for the height of the inmate in inches
                    ComboBox<Integer> comboInches = new ComboBox<>();

                    // Populate the inmates inches box
                    for (int i = 1; i < 12; i++)
                    {
                        comboInches.getItems().add(i);
                    }

                    // Set the value
                    comboInches.setValue(tempInmate.getInches());

                    // Create a combo box for ethnicity
                    ComboBox<String> comboEthnicity = new ComboBox<>();

                    // Add ethnicities
                    comboEthnicity.getItems().addAll("American Indian or Alaska Native", "Asian", "African-American", "Hispanic",
                            "Native Hawaiian/Pacific Islander", "White", "Other");

                    // Set the inmates ethnicity to the one that they have stored
                    comboEthnicity.setValue(tempInmate.getEthnicity());

                    // Create date picker for date arrested
                    DatePicker newDatePicker = new DatePicker();

                    // Set the date to the date that they were arrested
                    newDatePicker.setValue(tempInmate.getDateArrested());

                    // Create combobox for the cell type
                    ComboBox<String> cellType = new ComboBox<>();

                    // Populate the cell type combo box
                    cellType.getItems().addAll("Minimum", "Maximum");

                    // Set the value of the cell block combo box
                    cellType.setValue(tempInmate.getCellBlock().getCellType());

                    // Create a combobox for the cell numbers
                    ComboBox<Integer> cellNumber = new ComboBox<>();

                    // Populate the combo box based on the cell block
                    // Loop through the cells and populate the combo box
                    // with only available cells
                    if(cellType.getSelectionModel().getSelectedItem().equals("Minimum"))
                    {
                        for(int i = 0; i < Jail.getNumMin(); i++)
                        {
                            if(!Jail.getMinimum().get(i).isFull())
                            {
                                cellNumber.getItems().add(i + 1);
                            }

                        }
                    }
                    else if(cellType.getSelectionModel().getSelectedItem().equals("Maximum"))
                    {
                        for (int i = 0; i < Jail.getNumMax(); i++)
                        {
                            if (!Jail.getMaximum().get(i).isFull())
                            {
                                cellNumber.getItems().add(i + 1);
                            }
                        }
                    }



                    // Set the cell number
                    cellNumber.setValue(tempInmate.getCellBlock().getCellNumber());

                    // If the user selects a different cell block, populate the cell number
                    // combo box with available cells
                    cellType.setOnAction(s->{

                        if (cellType.getSelectionModel().getSelectedItem().equals("Minimum"))
                        {
                            cellNumber.getItems().clear();

                            for (int i = 0; i < Jail.getNumMin(); i++)
                            {
                                if(!Jail.getMinimum().get(i).isFull())
                                    cellNumber.getItems().add(i + 1);

                            }


                        }
                        else if (cellType.getSelectionModel().getSelectedItem().equals("Maximum"))
                        {
                            cellNumber.getItems().clear();

                            for (int i = 0; i < Jail.getNumMax(); i++)
                            {
                                if(!Jail.getMaximum().get(i).isFull())
                                    cellNumber.getItems().add(i + 1);
                            }

                        }

                        s.consume();
                    });

                    // Add the textfields to the pane
                    editPane.addRow(0, new Label("First Name"), tfFirstName);
                    editPane.addRow(1, new Label("Last Name"), tfLastName);
                    editPane.addRow(2, new Label("Weight"), tfWeight);
                    editPane.addRow(3, new Label("Feet"), comboFeet);
                    editPane.addRow(4, new Label("Inches"), comboInches);
                    editPane.addRow(5, new Label("Ethnicity"), comboEthnicity);
                    editPane.addRow(6, new Label("Date Arrested"), newDatePicker);
                    editPane.addRow(7, new Label("Cell Block"), cellType);
                    editPane.addRow(8, new Label("Cell Number"), cellNumber);



                    // Set some padding
                    editPane.setPadding(new Insets(5,5,5,5));
                    editPane.setHgap(10);
                    editPane.setVgap(10);

                    // Add everything to the main pane
                    masterPane.getChildren().addAll(editPane,box);

                    // Create some buttons
                    Button btnEditOk = new Button("OK");
                    Button btnEditCancel = new Button("Cancel");

                    // Define the cancel button
                    btnEditCancel.setOnAction(t-> newStage.close());

                    // Add the buttons to the box
                    box.getChildren().addAll(btnEditOk, btnEditCancel);

                    // Set the alignment for the buttons
                    box.setAlignment(Pos.BOTTOM_RIGHT);

                    // Set some padding
                    box.setPadding(new Insets(5,5,5,5));

                    // Define the ok button
                    btnEditOk.setOnAction(s->{

                        /*
                            Place in try block to ensure that all fields are valid, mainly the weight
                         */
                        try
                        {

                            tempInmate.setFirstName(tfFirstName.getText());

                            tempInmate.setLastName(tfLastName.getText());

                            tempInmate.setWeight(Double.parseDouble(tfWeight.getText()));

                            tempInmate.setFeet(comboFeet.getValue());

                            tempInmate.setInches(comboInches.getValue());

                            tempInmate.setEthnicity(comboEthnicity.getValue());

                            tempInmate.setDateArrested(newDatePicker.getValue());

                            // Create a cell to see if the inmate was moved
                            Cell checkCell = new NormalCell(cellType.getSelectionModel().getSelectedItem(), cellNumber.getSelectionModel().getSelectedItem());

                            if (!tempInmate.getCellBlock().equals(checkCell))
                            {
                                if(tempInmate.getCellBlock().getCellType().equals("Minimum"))
                                {
                                    Jail.getMinimum().get(tempInmate.getCellBlock().getCellNumber() - 1).removeInmate(tempInmate);
                                }
                                else
                                {
                                    Jail.getMaximum().get(tempInmate.getCellBlock().getCellNumber() - 1).removeInmate(tempInmate);
                                }

                                // Set the inmates cell block to the new cell
                                tempInmate.setCellBlock(checkCell);

                                if(checkCell.getCellType().equals("Minimum"))
                                {
                                    Jail.getMinimum().get(checkCell.getCellNumber() - 1).addInmate(tempInmate);
                                }
                                else
                                {
                                    Jail.getMaximum().get(checkCell.getCellNumber() - 1).addInmate(tempInmate);
                                }
                            }
                            // Notify the user that the inmates info was edited
                            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Inmate information changed successfully!");
                            alertBox.show();

                            // Close the stages
                            newStage.close();

                            thirdStage.close();


                        }
                        catch(NumberFormatException ex)
                        {
                            Alert alertBox = new Alert(Alert.AlertType.ERROR, "The weight must be a number.");
                            alertBox.show();
                        }


                    });

                    Scene newScene = new Scene(masterPane, 450,450);

                    anotherStage.setScene(newScene);
                    anotherStage.setTitle("Edit Inmate");
                    anotherStage.show();

                });



                // Define release button action
                btnSearchRelease.setOnAction(p->{

                    // Set the inmates date released field
                    tempInmate.setDateReleased(LocalDate.now());

                    // Determine where the inmate was in the jail, and remove them from that cell
                    if(tempInmate.getCellBlock().getCellType().equals("Minimum"))
                    {
                        Jail.getMinimum().get(tempInmate.getCellBlock().getCellNumber() - 1).removeInmate(tempInmate);
                    }
                    else
                    {
                        Jail.getMaximum().get(tempInmate.getCellBlock().getCellNumber() - 1).removeInmate(tempInmate);
                    }

                    // Set the inmates cell to null
                    tempInmate.setCellBlock(null);

                    // Remove the inmate from the inmate roster
                    InmateRoster.getRoster().remove(tempInmate);

                    // Add the inmate to the release roster
                    ReleaseRoster.getReleaseRoster().add(tempInmate);

                    // Display an alert to notify the user that the inmate was successfully released
                    Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Inmate successfully released!");
                    alertBox.show();

                    // Close the stages
                    thirdStage.close();
                    newStage.close();


                    // Consume the event
                    p.consume();
                });

                btnSearchOk.setOnAction(t->thirdStage.close());


                thirdStage.setTitle("Inmate info");
                thirdStage.setScene(new Scene(masterBox, 550, 450));
                thirdStage.show();

            });

            btnBox.getChildren().addAll(btnOk, btnCancel);
            btnBox.setAlignment(Pos.BOTTOM_RIGHT);

            frame.getChildren().addAll(searchLabel, matchedInmates, btnBox);

            Scene newScene = new Scene(frame, 350, 350);

            newStage.setTitle("Matched Inmates");
            newStage.setScene(newScene);
            newStage.show();


        });

        this.getChildren().addAll(mainLabelBox, greeting, searchBox);


        return this;
    }
}
