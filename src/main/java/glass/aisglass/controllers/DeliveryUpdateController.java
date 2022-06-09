package glass.aisglass.controllers;

import glass.aisglass.db.DeliveryDao;
import glass.aisglass.models.Delivery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DeliveryUpdateController {
    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cbAddress;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfPrice;

    public static int ID;
    public static String PRICE;
    public static String DESCRIPTION;
    public static String ADDRESS;

    @FXML
    protected void initialize() {
        ObservableList<String> addresses = FXCollections.observableArrayList("Kirov", "Moscow", "Novgorod");
        cbAddress.setItems(addresses);
        tfDescription.setPromptText(DESCRIPTION);
        tfPrice.setPromptText(PRICE);
        cbAddress.setPromptText(ADDRESS);
    }

    @FXML
    void handleButtonAction() {
        Delivery delivery = DeliveryDao.get(ID);
        if (!tfPrice.getText().isEmpty())
            delivery.setPrice_of_delivery(Integer.parseInt(tfPrice.getText()));
        else
            delivery.setPrice_of_delivery(Integer.parseInt(tfPrice.getPromptText()));
        if (cbAddress.getValue() != null)
            delivery.setAddress_of_delivery(cbAddress.getValue());
        else
            delivery.setAddress_of_delivery(cbAddress.getPromptText());
        if (!tfDescription.getText().isEmpty())
            delivery.setDescription_of_delivery(tfDescription.getText());
        else
            delivery.setDescription_of_delivery(tfDescription.getPromptText());
        DeliveryDao.update(delivery);
        btnUpdate.setDisable(true);
    }

}
