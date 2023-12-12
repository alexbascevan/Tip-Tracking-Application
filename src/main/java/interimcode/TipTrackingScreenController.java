package interimcode;

import interimcode.Server.ServerApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller class for the Tip Tracking Screen of the application.
 */
public class TipTrackingScreenController implements Initializable {

     /**
     * Label for displaying a greeting message to the server
     */
    @FXML
    private Label helloLabel;

    /**
     * Label for displaying any error messages.
     */
    @FXML
    private Label errorDisplay;

     /**
     * Text Field for getting input on tips to add to total owed
     */
    @FXML
    private TextField addTextField;

     /**
     * Text Field for getting input on tips to subtract from total owed
     */
    @FXML
    private TextField subtractTextField;

     /**
     * Label to display the updated total owed
     */
    @FXML
    private Label viewResultLabel;

    /**
     * Instance of the ServerApp class to manage server data.
     */
    ServerApp sApp = new ServerApp();

    /**
     * Initializes the screen with information about the server (i.e., name, tips owed).
     *
     * @param arg0
     * @param arg1 
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        updateViewResultLabel();
    }

    /**
     * Updates the view result label with the current server information.
     */
    private void updateViewResultLabel() {
        // Set the value to the hello label for the current server
        if (sApp.servers.get(0).getReturning()) { // If the server is returning
            helloLabel.setText("Welcome Back " + sApp.getServerName(sApp.servers, 0));
        } else { // If the server is new
            helloLabel.setText("Hello " + sApp.getServerName(sApp.servers, 0));
        }
        // Set the value to the viewResultLabel label with the current total owed from Array List
        viewResultLabel.setText("Total Tips Owed: $" + String.format("%.2f", sApp.getTotalOwed(sApp.servers, 0)));
    }

    /**
     * Adds tips to the total owed based on the user input.
     *
     * @param event The ActionEvent triggered by clicking the "Add Tips" button.
     */
    @FXML
    private void handleAddTips(ActionEvent event) {
        try {
            String num = addTextField.getText(); // Get input from text field for tip to add to total owed
            double tipAmount = Double.parseDouble(num); // Try to parse the string to a double
            sApp.addNewTip(sApp.servers, 0, tipAmount); // Add tip to total owed
            errorDisplay.setText(""); // Clear any displayed errors
            viewResultLabel.setText("Total Tips Owed: $" + String.format("%.2f", sApp.getTotalOwed(sApp.servers, 0))); // Set the value to the viewResultLabel label with the updated total owed
        } catch (NumberFormatException e) {
            // Display an error message if parsing fails
            errorDisplay.setText("Error: Invalid input. Please enter a valid number for tips to Add.");
        }
    }
    
    /**
     * Subtracts inputted tips from the total owed based on the user input.
     *
     * @param event The ActionEvent triggered by clicking the "Subtract Tips" button.
     */
    @FXML
    private void handleSubtractTips(ActionEvent event) {
        try {
            String num = subtractTextField.getText(); // Get input from text field for tip to subtract to total owed
            double tipAmount = Double.parseDouble(num); // Try to parse the string to a double
            sApp.subtractTips(sApp.servers, 0, tipAmount); // Subtract tip from total owed
            errorDisplay.setText(""); // Clear any displayed errors
            viewResultLabel.setText("Total Tips Owed: $" + String.format("%.2f", sApp.getTotalOwed(sApp.servers, 0))); // Set the value to the viewResultLabel label with the updated total owed
        } catch (NumberFormatException e) {
            // Display an error message if parsing fails
            errorDisplay.setText("Error: Invalid input. Please enter a valid number for tips to Subtract.");
        }
    }
    
    /**
     * Exits the program and saves data to the Servers.txt file.
     *
     * @param event The ActionEvent triggered by clicking the "Exit" button.
     */
    @FXML
    private void handleExit(ActionEvent event) {
        sApp.servers.get(0).setReturning(true);
        sApp.storeServerInfo(sApp.servers);// Stores data to text file
        System.exit(0); // Exit the program
    }
}
