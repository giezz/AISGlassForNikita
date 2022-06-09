package glass.aisglass.controllers;

import glass.aisglass.db.DeliveryDao;
import glass.aisglass.models.Delivery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DeliveryInsertController {
    @FXML
    private Button btnInsert;

    @FXML
    private ComboBox<String> cbAddress;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfPrice;

    @FXML
    protected void initialize(){
        ObservableList<String> addresses = FXCollections.observableArrayList("Kirov", "Moscow", "Novgorod");
        cbAddress.setItems(addresses);
    }

    @FXML
    protected void handleButtonAction(ActionEvent event) {
        System.out.println(btnInsert.getId() + "data inserted");
        DeliveryDao.create(
                new Delivery(
                        Double.parseDouble(tfPrice.getText()),
                        cbAddress.getValue(),
                        tfDescription.getText()
                )
        );
    }
}
