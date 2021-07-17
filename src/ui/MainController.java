package ui;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

public class MainController {

    private PhoneBook pb;

    @FXML
    private Pane mainPane;

    private Stage primaryStage;

    public MainController(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }

    public void initialize() {

        mainPane.setDisable(true);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setHeaderText(null);

        // "jdbc:oracle:thin:@200.3.193.24:1522:ESTUD", "P09551_2_6",
        // "P09551_2_6_20211"

        alert.setContentText(
                "Select the url string to connect to the server\n\nFormat:\n\"jdbc:oracle:thin:@IP:SERVNAME\",\"USER\",\"PASS\"");

        alert.showAndWait();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));

        File url = fileChooser.showOpenDialog(primaryStage);

        System.out.println(url.getAbsolutePath());

    }

}