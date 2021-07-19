package ui;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import model.PhoneBook;

public class NewContactController {
    @FXML
    private TextField nameNewContact;

    @FXML
    private TextField phoneNumberNewContact;

    @FXML
    private TextField addressNewContact;

    @FXML
    private ToggleGroup bestFriendRBs;

    @FXML
    private Label statusNewContact;

    private PhoneBook pb;

    public NewContactController(PhoneBook pb) {

        this.pb = pb;
    }

    @FXML
    void saveNewContact(ActionEvent event) {
        try {

            if (!pb.contactExists(nameNewContact.getText())) {
                RadioButton selectedRadioButton = (RadioButton) bestFriendRBs.getSelectedToggle();
                int isBestFriend = selectedRadioButton.getText().equals("Yes") ? 1 : 0;

                pb.saveNewContact(nameNewContact.getText(), phoneNumberNewContact.getText(),
                        addressNewContact.getText(), isBestFriend);

                statusNewContact.setText("Contact saved!");

                statusNewContact.setOpacity(1.0);

                statusNewContact.setTextFill(Color.GREEN);

                statusNewContact.setVisible(true);

                Thread thread = new Thread(() -> {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (double i = 1; i >= 0; i = i - 0.01)
                        try {
                            Thread.sleep(30);
                            statusNewContact.setOpacity(i);

                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }

                    statusNewContact.setVisible(false);

                    statusNewContact.setTextFill(Color.BLACK);

                });

                thread.setDaemon(true);

                clearForm();

                thread.start();
            } else {
                throw new Exception();
            }

        } catch (SQLException e) {

            Thread thread = new Thread(() -> {

                statusNewContact.setVisible(true);
                statusNewContact.setOpacity(1.0);
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                for (double i = 1; i >= 0; i = i - 0.01)
                    try {
                        Thread.sleep(30);
                        statusNewContact.setOpacity(i);

                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                statusNewContact.setVisible(false);

            });

            thread.setDaemon(true);
            statusNewContact.setText("Unexpected error");

            thread.start();
            e.printStackTrace();
        } catch (Exception e) {

            Thread thread = new Thread(() -> {

                statusNewContact.setVisible(true);
                statusNewContact.setOpacity(1.0);
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                for (double i = 1; i >= 0; i = i - 0.01)
                    try {
                        Thread.sleep(30);
                        statusNewContact.setOpacity(i);

                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                statusNewContact.setVisible(false);

            });

            thread.setDaemon(true);
            statusNewContact.setText("Contact already exists");

            thread.start();
        }
    }

    private void clearForm() {

        nameNewContact.clear();

        phoneNumberNewContact.clear();
        addressNewContact.clear();

        bestFriendRBs.getSelectedToggle().setSelected(false);

    }

}
