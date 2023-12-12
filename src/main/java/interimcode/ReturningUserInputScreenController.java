package interimcode;

import interimcode.Server.ServerApp;
import interimcode.Server.Server;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller class for the returning user input screen of the application.
 */
public class ReturningUserInputScreenController {

    /**
     * TextField and label for entering the name of the returning server.
     */
    @FXML
    private Label enterNameLabel;

    @FXML
    private TextField nameTextField;

    /**
    * Instance of the ServerApp class to manage server-related operations.
    */
    ServerApp sApp = new ServerApp();

    /**
     * Handles the action when the "Check" button is clicked.
     * Checks if the entered name exists in the server data, moves the server to index 0 if found, stores server data, and navigates to the Tip Tracking Screen.
     * If the name is not found, navigates to the New User Input Screen.
     *
     * @param event The ActionEvent triggered by clicking the "Check" button.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException
     */
    @FXML
    private void handleCheckButton(ActionEvent event) throws IOException, InterruptedException {


        boolean flag = false;
        String name = nameTextField.getText();// Gets inputted name from textField

        int index = -1;

        if(!name.trim().isEmpty()){
            // Check if the user's name is in the ArrayList populated by the text file Servers.txt
            for (int i = 0; i < sApp.servers.size(); i++) {
                Server element = sApp.servers.get(i);
                if (element.getName().equalsIgnoreCase(name)) {
                    flag = true;// Name is found in ArrayList
                    index = i; // Store the index of the found element
                    break; // Exit the loop if found
                }
            }

            if (flag) { // If the name is found

                // Move the server to index 0 in the array list
                Server serverToMove = sApp.servers.remove(index);
                sApp.servers.add(0, serverToMove);

                sApp.storeServerInfo(sApp.servers);// Store the modified info from ArrayList into the text file
                App.setRoot("TipTrackingScreen");// Navigate to the tip tracking screen
            } else {

                // Change label to indicate the name is not found in text file
                enterNameLabel.setText("Name \"" + name + "\" not found.  You are being directed to our sign up page now in 5 seconds...");
                
                // Delay execution for 5 seconds
                new Thread(() -> {
                    try {
                        Thread.sleep(5000); // Sleep for 5 seconds (5000 milliseconds)
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Update UI using Platform.runLater()
                    Platform.runLater(() -> {
                        try {
                            App.setRoot("NewUserInputScreen");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                }).start();
            }
                
        }else{
            enterNameLabel.setText("The name text field can not be empty.  Please enter a name.");
        }

      
    }
}
