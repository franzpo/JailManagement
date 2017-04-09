package sample;

/*

    Ryan Lister
    JailMenuBar Class

    This class creates the menu bar for the program. It
    defines all the layout panes and the actions for the menus
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.time.LocalDate;


public class JailMenuBar extends MenuBar
{
    public JailMenuBar()
    {

        createMenuBar();
    }

    /*
        Name: createMenuBar
        Args: None

        This method is invoked in the constructor and returns a jail menu bar.
        It defines all the actions of the menu bar. It is kept private in order to prevent
        anybody from using this method outside of creating a new menu bar.
     */
    private JailMenuBar createMenuBar()
    {


            // Create the menus
        Menu inmateMenu = new Menu("Inmate");
        Menu visitorMenu = new Menu("Visitor");
        Menu courtDatesMenu = new Menu("Court Dates");

            // Create the menu items that will be in the menus
        MenuItem addInmate = new MenuItem("Add Inmate");
        MenuItem viewInmate = new MenuItem("View All Inmates");
        MenuItem viewCells = new MenuItem("View Inmates by Cell");
        MenuItem viewCourtDates = new MenuItem("View Court Dates");
        MenuItem viewVisitors = new MenuItem("View Visitors");
        MenuItem addVisitor = new MenuItem("Add Visitor");

            // Add the inmate menu items to the inmate menu
        inmateMenu.getItems().addAll(addInmate, viewInmate, viewCells);

            // Add the visitor menu item to the visitor menu
        visitorMenu.getItems().addAll(viewVisitors, addVisitor);

            // Add the court date menu item to the court date menu
        courtDatesMenu.getItems().add(viewCourtDates);

            // Add everything to the menu bar
        this.getMenus().addAll(inmateMenu, visitorMenu, courtDatesMenu);

            // Define the action for the add inmate button
        addInmate.setOnAction(e -> {



                // The add inmate menu button will open up a secondary stage to display the add pane.
            Stage secondaryStage = new Stage();

                // Create the grid pane to hold the textfields
            GridPane addInmatePane = new GridPane();


                // Formatting for the pane
            addInmatePane.setPadding(new Insets(5, 5, 5, 5));
            addInmatePane.setVgap(5);
            addInmatePane.setHgap(15);


                // Create the textfields and set up their prompt text
            TextField tfFirstName = new TextField();
            tfFirstName.setPromptText("Enter First Name here...");

            TextField tfLastName = new TextField();
            tfLastName.setPromptText("Enter Last Name here...");

            TextField tfWeight = new TextField();
            tfWeight.setPromptText("Enter Weight here...");

                // Create a combo box for selecting the height of the inmate
            ComboBox<Integer> comboFeet = new ComboBox<>();

                // Add the items to the combo box
            comboFeet.getItems().addAll(new Integer("1"), new Integer("2"), new Integer("3"), new Integer("4"), new Integer("5"),
                    new Integer("6"), new Integer("7"), new Integer("8"));

                // Create another combo box for the inches
            ComboBox<Integer> comboInches = new ComboBox<>();

                // Add the items to the combo box
            comboInches.getItems().addAll(new Integer("1"), new Integer("2"), new Integer("3"), new Integer("4"), new Integer("5"),
                    new Integer("6"), new Integer("7"), new Integer("8"), new Integer("9"), new Integer("10"), new Integer("11"));

                // Create a combo box for ethnicity
            ComboBox<String> comboEthnicity = new ComboBox<>();

                // Add ethnicities
            comboEthnicity.getItems().addAll("American Indian or Alaska Native", "Asian", "African-American", "Hispanic",
                    "Native Hawaiian/Pacific Islander", "White", "Other");

                // Let the user select where the inmate is going
            ComboBox<String> comboCell = new ComboBox<>();

                // Since our jail only allows inmates to be admitted to min/max when booked, only give the user two options
            comboCell.getItems().addAll("Minimum", "Maximum");

                // Add the text fields and labels to the pane
            addInmatePane.addRow(0, new Label("First Name"), tfFirstName, new Label("Last Name"), tfLastName);

            addInmatePane.addRow(1, new Label("Weight"), tfWeight, new Label("Feet"), comboFeet, new Label("Inches"), comboInches);

            addInmatePane.addRow(2, new Label("Ethnicity"), comboEthnicity, new Label("Cell Block"), comboCell);

                // Create a datepicker
            DatePicker datePicker = new DatePicker();

                // Set the value of the date picker to the current day
            datePicker.setValue(LocalDate.now());

            addInmatePane.addRow(3, new Label("Date Arrested"), datePicker);

                // Ok button to add inmate
            Button btnOk = new Button("OK");

                // Cancel button
            Button btnCancel = new Button("Cancel");

                // Add the buttons
            addInmatePane.add(btnOk, 4, 3);

            addInmatePane.add(btnCancel, 5, 3);

                // Define action when button is selcted
            btnOk.setOnAction(event -> {


                    // Here we test each of the fields to make sure we have valid data
                if (tfFirstName.getText().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter a First Name.");
                    alertBox.show();
                } else if (tfLastName.getText().isEmpty())
                {
                    Alert alertBox =  new Alert(Alert.AlertType.ERROR, "Please enter a Last Name.");
                    alertBox.show();
                }
                else if (tfWeight.getText().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter a weight");
                    alertBox.show();
                }
                else if (comboCell.getSelectionModel().isEmpty()) {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter an ethnicity.");
                    alertBox.show();
                }
                else if(comboFeet.getSelectionModel().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter a measurement for feet.");
                    alertBox.show();
                }
                else if (comboInches.getSelectionModel().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter a measurement for inches");
                    alertBox.show();
                }
                else if(comboCell.getSelectionModel().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please select a cell block for the inmate.");
                    alertBox.show();
                }
                else if(datePicker.getValue() == null)
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please select the date of the arrest.");
                    alertBox.show();
                }
                else {

                        // Next, after all fields have been had the presence of data verified,
                        // place everything in a  try block to make sure that the weight is
                        // actually a number. The reason it is all placed in a try block is so
                        // the inmate is not created unless all data is valid
                    try
                    {

                        String fName = tfFirstName.getText();
                        String lName = tfLastName.getText();
                        String ethnicity = comboEthnicity.getSelectionModel().getSelectedItem();
                        LocalDate dateArrested = datePicker.getValue();
                        int feet = comboFeet.getValue();
                        int inches = comboInches.getValue();

                        double weight = Double.parseDouble(tfWeight.getText());
                        // The system will automatically assign the inmate to the next available cell
                        // The system will check the value of the cell combo box and assign them a cell based on the selected cell type
                        if (comboCell.getValue().equals("Maximum"))
                        {
                            // Loop to go through the cells
                            for (int i = 0; i < Jail.getNumMax(); i++)
                            {
                                // When we find a cell that is not full, add the inmate to it
                                if (!Jail.getMaximum().get(i).isFull())
                                {
                                    // Create a cell so that we can add the inmate to it
                                    Cell assignCell = Jail.getMaximum().get(i);

                                    // Create the inmate
                                    Inmate newInmate = new Inmate(fName, lName, weight, ethnicity, dateArrested, assignCell, feet, inches);

                                    // Add the inmate to the cell
                                    if(Jail.getMaximum().get(i).addInmate(newInmate))
                                    {
                                            // Notify user that inmate was added
                                        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Inmate successfully added!");
                                        alertBox.show();
                                    }
                                    else
                                    {
                                            // Notify user that inmate was not added
                                        Alert alertBox = new Alert(Alert.AlertType.ERROR, "The inmate was not added successfully... Check occupancy of prison.");
                                        alertBox.show();
                                    }
                                    // Add the inmate to the roster
                                    InmateRoster.addInmate(newInmate);


                                    // Exit the loop since we're done
                                    break;

                                }
                            }
                        }
                        else
                        {
                            // Loop through the cells
                            for (int i = 0; i < Jail.getNumMin(); i++)
                            {
                                // once we find a cell that is not full, add the inmate to it
                                if (!Jail.getMinimum().get(i).isFull())
                                {
                                    // Create cell that is to be the assigned cell
                                    Cell assignCell = Jail.getMinimum().get(i);

                                    // Create new inmate object with specified cell
                                    Inmate newInmate = new Inmate(fName, lName, weight, ethnicity, dateArrested, assignCell, feet, inches);

                                    // Add inmate to the jail
                                    if(Jail.getMinimum().get(i).addInmate(newInmate))
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Inmate successfully added!");
                                        alertBox.show();
                                    }
                                    else
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.ERROR, "The inmate was not added successfully... Check occupancy of prison.");
                                        alertBox.show();
                                    }

                                    // Add inmate to the roster
                                    InmateRoster.addInmate(newInmate);


                                    break;

                                }
                            }
                        }

                        // Once the inmate has been added, close the pane
                        secondaryStage.close();

                    }catch(NumberFormatException ex)
                    {
                        Alert alertBox = new Alert(Alert.AlertType.ERROR, "The weight must be a number.");
                        alertBox.show();
                    }


                }

            });

                // Define the cancel button action
            btnCancel.setOnAction(ev -> secondaryStage.close());

                // Create the scene
            Scene secondScene = new Scene(addInmatePane, 750, 150);

            // Set the scene and show it
            secondaryStage.setScene(secondScene);
            secondaryStage.setTitle("Add Inmate");
            secondaryStage.show();

        });
            // Define the view inmate menu button
        viewInmate.setOnAction(e->{

                // Create the stage that we will display the view inmate table
            Stage secondStage = new Stage();

                // Create a pane to hold the tableview
            VBox tablePane = new VBox(10);

                // Create the table view
            TableView<Inmate> inmateList = new TableView<>();

                // Create the observable list that will be held by the table view
            ObservableList<Inmate> inmates = FXCollections.observableArrayList(InmateRoster.getRoster());

                // Set the tableview to hold the list of inmates
            inmateList.setItems(inmates);

                // Create the first name column and set its value
            TableColumn<Inmate, String> fNameColumn = new TableColumn("First Name");
            fNameColumn.setCellValueFactory(new PropertyValueFactory<Inmate, String>("firstName"));
            fNameColumn.setPrefWidth(200);

                // Create the last name column
            TableColumn<Inmate, String> lNameColumn = new TableColumn("Last Name");
            lNameColumn.setCellValueFactory(new PropertyValueFactory<Inmate, String>("lastName"));
            lNameColumn.setPrefWidth(200);

                // Create the ID column... had to use "ID" in the setCell method because inmateID was not working
            TableColumn<Inmate, String> IDColumn = new TableColumn("Inmate ID");
            IDColumn.setCellValueFactory(new PropertyValueFactory<Inmate, String>("ID"));
            IDColumn.setPrefWidth(200);

                // Add the columns to the tableview
            inmateList.getColumns().addAll(fNameColumn, lNameColumn,IDColumn);


                // Add the tableview to the pane
            tablePane.getChildren().add(inmateList);

                // Create two buttons to either view inmate info or cancel
            Button btnView = new Button("View Inmate");
            Button btnCancel = new Button("Cancel");

                // Hbox to hold the buttons
            HBox btnBox = new HBox(10);

                // Set the padding for the hbox
            btnBox.setPadding(new Insets(5,5,5,5));

                // Set the alignment for the hbox
            btnBox.setAlignment(Pos.BOTTOM_RIGHT);

                // Add the buttons to the hbox
            btnBox.getChildren().addAll(btnView, btnCancel);

                // Define the view button action
            btnView.setOnAction(eve->{

                    // Create an inmate so we can easily access the information of the selcted inmate
                Inmate tempInmate = inmateList.getSelectionModel().getSelectedItem();

                    // Display the inmate info in a different stage
                Stage thirdStage = new Stage();

                    // Create a grid pane to hold the view
                GridPane inmateInfo = new GridPane();

                    // Set some formatting for the gridpane
                inmateInfo.setVgap(10);
                inmateInfo.setHgap(10);
                inmateInfo.setPadding(new Insets(5,5,5,5));

                    // Label to hold the name
                Label nameLabel = new Label(tempInmate.getFirstName() + " " + tempInmate.getLastName());

                    // Add name label and another label describing the field
                inmateInfo.addRow(0, new Label("Name"), nameLabel);

                    // Create the ID label to hold the inmates id
                Label idLabel =  new Label(tempInmate.getID());

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
                Label numberLabel = new Label(String.format("%4d",tempInmate.getCellBlock().getCellNumber()));

                    // Add the height to the pane
                inmateInfo.addRow(3,  new Label("Height"), heightLabel);

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
                inmateInfo.addRow(6, new Label("Ethnicity"),ethnicityLabel);

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

                    // Add the button
                inmateInfo.addRow(10, btnAddCourtDate);

                    // Define the add court date buttons action
                btnAddCourtDate.setOnAction(p->{

                        // Create a new stage
                    Stage newStage = new Stage();

                        // Create a date picker to select a court date
                    DatePicker datePicker = new DatePicker();

                        // Set the default value of the date picker to today's date
                    datePicker.setValue(LocalDate.now());

                        // Create a vbox to hold the date picker
                    VBox courtDateBox = new VBox(10);

                            // Hbox to hold the buttons that control the add court date menu
                    HBox courtDateBtnBox = new HBox(10);

                        // Create ok button
                    Button btnOk = new Button("OK");

                        // Create cancel button
                    Button btnCourtCancel = new Button("Cancel");

                        // Add the buttons to the hbox
                    courtDateBtnBox.getChildren().addAll(btnOk, btnCourtCancel);

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
                    btnOk.setOnAction(z-> {

                            // Add the court date to the inmates court date schedule
                        tempInmate.getCourtDates().add(datePicker.getValue());

                            // Close the stages
                        newStage.close();
                        thirdStage.close();

                            // Notify user that the court date was added successfully
                        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Court Date Successfully added");
                        alertBox.show();
                    });

                        // Define the cancel button's action
                    btnCourtCancel.setOnAction(q->newStage.close());

                        // Set the title of the stage and display it
                    newStage.setTitle("Add Court Date");
                    Scene newScene = new Scene(courtDateBox, 250, 250);
                    newStage.setScene(newScene);
                    newStage.show();


                });

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

                    // Create vbox to hold the edit inmate info
                VBox masterBox = new VBox(10);

                    // Create hbox for buttons
                HBox controlBox = new HBox(10);

                    // Create ok button
                Button btnOk = new Button("OK");

                    // Create edit button
                Button btnEdit = new Button("Edit");

                    // Create release button
                Button btnRelease = new Button("Release");

                    // Define release button action
                btnRelease.setOnAction(y->{

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
                    secondStage.close();
                    thirdStage.close();

                        // Consume the event
                    y.consume();
                });

                    // Register the action for the ok button
                btnOk.setOnAction(s->thirdStage.close());

                    // Add the buttons to the control box
                controlBox.getChildren().addAll(btnOk, btnEdit, btnRelease);

                    // Set the alignment for the button box
                controlBox.setAlignment(Pos.BOTTOM_RIGHT);

                    // Set the padding
                controlBox.setPadding(new Insets(5,5,5,5));

                    // Define the edit buttons action
                btnEdit.setOnAction(c->{

                    Stage newStage = new Stage();


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

                            secondStage.close();
                        }
                        catch(NumberFormatException ex)
                        {
                            Alert alertBox = new Alert(Alert.AlertType.ERROR, "The weight must be a number.");
                            alertBox.show();
                        }


                    });

                    Scene newScene = new Scene(masterPane, 450,450);
                    newStage.setScene(newScene);
                    newStage.setTitle("Edit Inmate");
                    newStage.show();


                });

                masterBox.getChildren().addAll(inmateInfo, controlBox);

                    // Create the scene to be displayed
                Scene newScene = new Scene(masterBox, 550, 450);

                    // Display it
                thirdStage.setScene(newScene);
                thirdStage.setTitle("View Inmate Info");
                thirdStage.show();

                    // Consume the event
                eve.consume();
            });

                // Define the cancel button
            btnCancel.setOnAction(eve-> secondStage.close());

                // Add everything to the pane
            tablePane.getChildren().add(btnBox);

                // Create the scene
            Scene newScene = new Scene(tablePane, 600,600);

                // Set the scene and show it
            secondStage.setScene(newScene);
            secondStage.setTitle("All Inmates");
            secondStage.show();

                // Consume the event
            e.consume();

        });

        addVisitor.setOnAction(e->{
                // Create new stage for the add visitor window
            Stage newStage = new Stage();

                // Create new pane for textfields and labels
            GridPane visitorInfo = new GridPane();

                // Vbox to hold all panes
            VBox masterBox = new VBox(10);
            masterBox.setPadding(new Insets(5,5,5,5));

                // Formatting for the gridpane
            visitorInfo.setVgap(10);
            visitorInfo.setHgap(10);
            visitorInfo.setPadding(new Insets(5,5,5,5));

                // Create a field for the first name and set the prompt text
            TextField tfFirstName = new TextField();
            tfFirstName.setPromptText("Enter First Name here...");

                // Create a field for the last name and set the prompt text
            TextField tfLastName = new TextField();
            tfLastName.setPromptText("Enter Last Name here...");

                // Textfield for the first part of the SSN
            TextField ssn1 = new TextField();
            ssn1.setPrefColumnCount(2);
            ssn1.setPromptText("123");

                // Textfield for the second part of the SSN
            TextField ssn2 = new TextField();
            ssn2.setPrefColumnCount(2);
            ssn2.setPromptText("45");

                // Textfield for the third part of the SSN
            TextField ssn3 = new TextField();
            ssn3.setPrefColumnCount(2);
            ssn3.setPromptText("6789");

                // Create a datepicker object to get the date of the visit
            DatePicker datePicker = new DatePicker();

                // Set the date of the datepicker to the current date
            datePicker.setValue(LocalDate.now());

                // Add everything to the visitor info grid pane
            visitorInfo.addRow(0, new Label("First Name"), tfFirstName, new Label("Last Name"), tfLastName);

            visitorInfo.addRow(1, new Label("Social Security Number"), ssn1, ssn2, ssn3);

            visitorInfo.addRow(2, new Label("Date of Visit"), datePicker);

                // Create a tableview for the user to select the inmate being visited
            TableView<Inmate> inmateList = new TableView<>();

                // Create the observable list that will be held by the table view
            ObservableList<Inmate> inmates = FXCollections.observableArrayList(InmateRoster.getRoster());

                // Set the tableview to hold the list of inmates
            inmateList.setItems(inmates);

                // Create the first name column and set its value
            TableColumn<Inmate, String> fNameColumn = new TableColumn("First Name");
            fNameColumn.setCellValueFactory(new PropertyValueFactory<Inmate, String>("firstName"));
            fNameColumn.setPrefWidth(200);

                // Create the last name column
            TableColumn<Inmate, String> lNameColumn = new TableColumn("Last Name");
            lNameColumn.setCellValueFactory(new PropertyValueFactory<Inmate, String>("lastName"));
            lNameColumn.setPrefWidth(200);

                // Create the ID column... had to use "ID" in the setCell method because inmateID was not working
            TableColumn<Inmate, String> IDColumn = new TableColumn("Inmate ID");
            IDColumn.setCellValueFactory(new PropertyValueFactory<Inmate, String>("ID"));
            IDColumn.setPrefWidth(200);

                // Add the columns to the tableview
            inmateList.getColumns().addAll(fNameColumn, lNameColumn,IDColumn);

                // Create a pane to hold the table
            VBox tablePane = new VBox(10);

                // Add the table view to the pane
            tablePane.getChildren().add(inmateList);

                // Define two buttons
            Button btnOk = new Button("OK");
            Button btnCancel = new Button("Cancel");

                // Create a box for the buttons
            HBox btnBox = new HBox(10);

                // Add the buttons to the btn box
            btnBox.getChildren().addAll(btnOk, btnCancel);

                //Format the btn box
            btnBox.setPadding(new Insets(5,5,5,5));
            btnBox.setAlignment(Pos.BOTTOM_RIGHT);

                // Add the panes to the master box
            masterBox.getChildren().addAll(visitorInfo,tablePane, btnBox);

                // Define the action for the ok button
            btnOk.setOnAction(event->{



                /*
                    This if block checks all the fields before they are processed, if there is an error
                    then the program does not create a visitor object, but it still gives the user a chance
                    to fix the input
                 */
                if (tfFirstName.getText().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter a first name.");
                    alertBox.show();
                }
                else if(tfLastName.getText().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter a last name.");
                    alertBox.show();
                }
                else if((ssn1.getText().length() != 3) || (ssn2.getText().length() != 2) || (ssn3.getText().length() != 4))
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please enter a valid Social Security Number.");
                    alertBox.show();
                }
                else if(datePicker.getValue() == null)
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please select the date of visit.");
                    alertBox.show();
                }
                else if(inmateList.getSelectionModel().isEmpty())
                {
                    Alert alertBox = new Alert(Alert.AlertType.ERROR, "Please select the inmate being visited.");
                    alertBox.show();
                }
                else
                {
                    String fName = tfFirstName.getText();
                    String lName = tfLastName.getText();
                    String ssn = ssn1.getText() + "-" + ssn2.getText() + "-"  + ssn3.getText();
                    LocalDate dateVisited = datePicker.getValue();
                    Inmate inmateVisited = inmateList.getSelectionModel().getSelectedItem();

                    Visitor visitor = new Visitor(fName, lName, ssn, dateVisited, inmateVisited);


                        // If the visitor is added successfully, display a message, otherwise, display the error
                    if(VisitorsLog.getVisitorsLog().add(visitor))
                    {
                        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Visitor successfully added!");
                        alertBox.show();
                    }
                    else
                    {
                        Alert alertBox = new Alert(Alert.AlertType.ERROR, "Visitor not added...");
                        alertBox.show();
                    }

                        // Close the stage once the visitor has been added
                    newStage.close();
                }


                    // Consume the event
                event.consume();

            });

                // Define the cancel button action
            btnCancel.setOnAction(ev-> newStage.close());

                // Create a scene and set it in the stage, and display it
            Scene newScene = new Scene(masterBox, 700, 350);
            newStage.setScene(newScene);
            newStage.setTitle("Add Visitor");
            newStage.show();

            e.consume();

        });

        viewVisitors.setOnAction(e -> {

            // Create a stage to display the visitors in
            Stage newStage = new Stage();

            // Create a table view to display the visitors
            TableView visitorTable = new TableView();

            // Create an observable list for the table view
            ObservableList<Visitor> visitors = FXCollections.observableArrayList(VisitorsLog.getVisitorsLog());

            // Set the table view to the visitors log
            visitorTable.setItems(visitors);


            // Create column for the first name
            TableColumn<Visitor, String> fNameColumn = new TableColumn("First Name");
            fNameColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("FirstName"));
            fNameColumn.setPrefWidth(200);

            // Create column for the last name
            TableColumn<Visitor, String> lNameColumn = new TableColumn("Last Name");
            lNameColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("LastName"));
            lNameColumn.setPrefWidth(200);

            // Create column for the SSN
            TableColumn<Visitor, String> SSNColumn = new TableColumn("Social Security Number");
            SSNColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("SSN"));
            SSNColumn.setPrefWidth(200);

            // Create column for date visited
            TableColumn<Visitor, String> dateVisitedColumn = new TableColumn("Date Visited");
            dateVisitedColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("dateVisited"));
            dateVisitedColumn.setPrefWidth(200);

            // Create column for inmate visited
            TableColumn<Visitor, Inmate> inmateVisistedColumn = new TableColumn("Inmate Visited");
            inmateVisistedColumn.setCellValueFactory(new PropertyValueFactory<Visitor, Inmate>("inmateVisited"));
            inmateVisistedColumn.setPrefWidth(200);

            visitorTable.getColumns().addAll(fNameColumn, lNameColumn, SSNColumn, dateVisitedColumn, inmateVisistedColumn);

            // Create VBox to hold everything
            VBox box = new VBox(10);

            // Add table view to the box
            box.getChildren().add(visitorTable);

            // Create new scene
            Scene newScene = new Scene(box, 1000, 450);

            // Set scene in stage and display it
            newStage.setScene(newScene);
            newStage.setTitle("View All Visitors");
            newStage.show();

            // Consume the event
            e.consume();
        });

        viewCells.setOnAction(e -> {

            // Create stage to display cell blocks
            Stage newStage = new Stage();

            // Create a vbox to hold the tables
            VBox tablePane = new VBox(10);

            // Create a table view to display the cell blocks
            TableView<NormalCell> normalTable = new TableView();

            // Create an observable list that consists of minimum and maximum
            ObservableList<NormalCell> normalList = FXCollections.observableArrayList(Jail.getMinimum());

            // Add maximum cells to the table view
            normalList.addAll(Jail.getMaximum());

            // Set the observable list in the table view
            normalTable.setItems(normalList);

            // Create a column for cell block
            TableColumn cellBlockColumn = new TableColumn("Cell Block");
            cellBlockColumn.setCellValueFactory(new PropertyValueFactory<Cell, String>("cellType"));
            cellBlockColumn.setPrefWidth(100);

            // Create a column for cell number
            TableColumn cellNumberColumn = new TableColumn("Cell Number");
            cellNumberColumn.setCellValueFactory(new PropertyValueFactory<Cell, Integer>("cellNumber"));
            cellNumberColumn.setPrefWidth(100);

            // Create a column for bottom bunk
            TableColumn bottomBunkColumn = new TableColumn("Bottom Bunk");
            bottomBunkColumn.setCellValueFactory(new PropertyValueFactory<Cell, Inmate>("bottomBunk"));
            bottomBunkColumn.setPrefWidth(200);

            // Create a column for top bunk
            TableColumn topBunkColumn = new TableColumn("Top Bunk");
            topBunkColumn.setCellValueFactory(new PropertyValueFactory<Cell, Inmate>("topBunk"));
            topBunkColumn.setPrefWidth(200);

            // Add columns to the table
            normalTable.getColumns().addAll(cellBlockColumn, cellNumberColumn, bottomBunkColumn, topBunkColumn);


            // Create the observable list for special cells
            ObservableList<SpecialCell> specialCells = FXCollections.observableArrayList(Jail.getHospital());


            // Add the isolation cells
            specialCells.addAll(Jail.getIsolation());

            // Create the table view for special cells
            TableView<SpecialCell> specialTable = new TableView();

            // Set the observable list in the table view
            specialTable.setItems(specialCells);

            // Create a column for cell block
            TableColumn specialType = new TableColumn("Cell Block");
            specialType.setCellValueFactory(new PropertyValueFactory<Cell, String>("cellType"));
            specialType.setPrefWidth(100);

            // Create column for cell number
            TableColumn specialNumber = new TableColumn("Cell Number");
            specialNumber.setCellValueFactory(new PropertyValueFactory<Cell, Integer>("cellNumber"));
            specialType.setPrefWidth(100);

            // Create a column for occupant
            TableColumn occupant = new TableColumn("Occupant");
            occupant.setCellValueFactory(new PropertyValueFactory<Cell, Inmate>("Occupant"));
            occupant.setPrefWidth(450);

            // Add the columns to the table
            specialTable.getColumns().addAll(specialType, specialNumber, occupant);

            // Add some labels and the tables
            tablePane.getChildren().addAll(new Label("Normal Cells"), normalTable, new Label("Special Cells"), specialTable);

            // Set some padding
            tablePane.setPadding(new Insets(5, 5, 5, 5));

            // Create a scene and display it
            Scene newScene = new Scene(tablePane, 650, 650);
            newStage.setScene(newScene);
            newStage.setTitle("View Cells");
            newStage.show();

            // Consume the event
            e.consume();
        });

        viewCourtDates.setOnAction(q -> {

                // Create new stage
            Stage newStage = new Stage();

                // Create vbox to hold all the inmates and court dates
            VBox masterBox = new VBox(10);

                // Loop through the inmate roster and get the court data
            for (Inmate inmate : InmateRoster.getRoster())
            {
                    // Create vbox to hold inmates name and court dates
                VBox inmateBox = new VBox(10);

                    // Create label for inmates name
                Label nameLabel = new Label(inmate.toString());

                    // Create label for court dates
                Label courtDateArea = new Label();

                    // Test to see if they have a court date, if so, display them, if not, display no court dates
                if (inmate.getCourtDates().size() > 0)
                {

                    for (int i = 0; i < inmate.getCourtDates().size(); i++)
                    {
                        courtDateArea.setText(courtDateArea.getText() + inmate.getCourtDates().get(i) + '\n');

                    }

                }
                else
                {
                    courtDateArea.setText("No Court Dates Scheduled");
                }

                    // Add the data to the inmates box
                inmateBox.getChildren().addAll(nameLabel, courtDateArea);

                    // Set the padding
                inmateBox.setPadding(new Insets(5, 5, 5, 5));

                    // Add the box to the main pane
                masterBox.getChildren().add(inmateBox);
            }


                // Set some padding
            masterBox.setPadding(new Insets(5, 5, 5, 5));

                // Create a scroll bar to hold everything
            ScrollPane scroller = new ScrollPane();

                // Set the content
            scroller.setContent(masterBox);

                // Create a new scene
            Scene newScene = new Scene(scroller, 450, 450);

                // Display the scene
            newStage.setScene(newScene);
            newStage.setTitle("Inmate Court Dates");
            newStage.show();
        });


        return this;

    }

}