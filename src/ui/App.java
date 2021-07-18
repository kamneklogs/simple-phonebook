package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));

        fxmlLoader.setController(new MainController(primaryStage));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setResizable(false);

        primaryStage.setTitle("Simple phonebook :)");

        primaryStage.show();

    }

}
