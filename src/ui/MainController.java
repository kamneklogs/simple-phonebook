package ui;

import model.PhoneBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainController {

    private PhoneBook pb;

    @FXML
    private Stage primaryStage;

    @FXML
    private FlowPane mainPane;

    @FXML
    private ImageView loaderIco;

    @FXML
    private Pane buttonsPane;

    @FXML
    private Label statusLabel;

    public MainController(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }

    public void initialize() throws IOException {

        buttonsPane.setDisable(true);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(
                "Select the url string to connect to the server\n\nFormat:\njdbc:oracle:thin:@IP:SERVNAME,USER,PASS");

        alert.showAndWait();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));

        File urlFile = fileChooser.showOpenDialog(primaryStage);

        BufferedReader reader;
        String url = null;

        try {
            reader = new BufferedReader(new FileReader(urlFile));

            url = reader.readLine();

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pb = new PhoneBook(url);

        Thread thread = new Thread(() -> {
            pb.connect();

            Platform.runLater(() -> {

                statusLabel.setText("Connection successful");

                buttonsPane.setDisable(false);
            });

            try {
                Thread.sleep(3000);

                statusLabel.setDisable(true);
                statusLabel.setVisible(false);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });

        thread.setDaemon(true);

        thread.start();

        Thread threadToAnimate = new Thread(() -> {

            while (pb.isConnected() == false) {

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    loaderIco.setRotate(loaderIco.getRotate() + 8);
                });

            }
            for (double i = loaderIco.getOpacity(); i >= 0.0; i = i - 0.1) {
                try {
                    Thread.sleep(30);
                    loaderIco.setOpacity(i);

                    loaderIco.setRotate(loaderIco.getRotate() + 8);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            loaderIco.setVisible(false);
        });

        threadToAnimate.setDaemon(true);

        threadToAnimate.start();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent arg0) {
                pb.disconnect();

                System.out.println("Desconectado");
                primaryStage.close();
            }
        });

    }

}