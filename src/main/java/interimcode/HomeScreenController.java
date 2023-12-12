package interimcode;

import interimcode.Server.ServerApp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller class for the home screen of the application.
 */
public class HomeScreenController {

    /**
     * Instance of the ServerApp class to manage server-related operations.
     */
    ServerApp sApp = new ServerApp();

    /**
     * Takes the user to the returning user page when the "Yes" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Yes" button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void handleYesButton(ActionEvent event) throws IOException {
        App.setRoot("ReturningUserInputScreen"); // Navigate to returning user screen
    }

    /**
     * Takes the user to the new user page when the "No" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "No" button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void handleNoButton(ActionEvent event) throws IOException {
        App.setRoot("NewUserInputScreen"); // Navigate to new user screen
    }
}
