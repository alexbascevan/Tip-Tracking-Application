package interimcode;

import interimcode.Server.ServerApp;
import interimcode.Server.Server;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller class for the new user input screen of the application.
 */
public class NewUserInputScreenController {

    /**
     * Label for displaying any error messages.
     */
    @FXML
    private Label errorDisplay;

    /**
     * TextField for entering the name of the new server.
     */
    @FXML
    private TextField nameTextField;

    /**
     * Instance of the ServerApp class to manage server-related operations.
     */
    ServerApp sApp = new ServerApp();

    /**
     * Handles the action when the "Continue" button is clicked.
     * Creates a new server with the entered name, adds it to the ServerApp, stores server data, and navigates to the Tip Tracking Screen.
     *
     * @param event The ActionEvent triggered by clicking the "Continue" button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void handleContinueButton(ActionEvent event) throws IOException {

        String name;// Name of current Server

        name = nameTextField.getText();// Get name input from textField

        if(!name.trim().isEmpty()){ 
            // Create a server with the inputted name
            Server sv = new Server(name);

            // Add the server sv to the arraylist
            sApp.addServer(sv);

            // Stores the server data in the text file
            sApp.storeServerInfo(sApp.servers);

            // Navigate to the Tip Tracking Screen
            App.setRoot("TipTrackingScreen");
                
        }else{
            errorDisplay.setText("The name text field can not be empty.  Please enter a name.");
        }
    }

    
}
