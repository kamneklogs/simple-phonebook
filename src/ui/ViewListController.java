package ui;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.PhoneBook;

public class ViewListController {

    private PhoneBook pb;

    @FXML
    private ListView<String> contactsListView;

    public ViewListController(PhoneBook pb) {

        this.pb = pb;

    }

    public void initialize() {

        try {
            contactsListView.getItems().addAll(pb.allContactNames());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
