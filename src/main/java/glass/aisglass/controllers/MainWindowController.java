package glass.aisglass.controllers;

import glass.aisglass.Excel.Excel;
import glass.aisglass.bin.App;
import glass.aisglass.db.DeliveryDao;
import glass.aisglass.models.Delivery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Window;

public class MainWindowController {

    @FXML
    private Button btnExport;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdateData;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<Delivery, String> colAddress;

    @FXML
    private TableColumn<Delivery, String> colDescription;

    @FXML
    private TableColumn<Delivery, Integer> colNumberOfDelivery;

    @FXML
    private TableColumn<Delivery, Integer> colPrice;

    @FXML
    private TableView<Delivery> tableDelivery;

    @FXML
    private TextField tfFilterDeliveryNo;

    @FXML
    private TextField tfFilterDesc;

    @FXML
    private TextField tfFilterPrice;

    @FXML
    private ComboBox<String> cbFilterAddress;


    @FXML
    protected void initialize() {
        ObservableList<String> addresses = FXCollections.observableArrayList(null, "Kirov", "Moscow", "Novgorod");
        cbFilterAddress.setItems(addresses);
        colNumberOfDelivery.setCellValueFactory(new PropertyValueFactory<>("number_of_delivery"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price_of_delivery"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address_of_delivery"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_of_delivery"));
        showDeliveries();
    }

    @FXML
    protected void handleButtonAction(ActionEvent event) {
        Button source = ((Button) event.getSource());
        if (source.equals(btnUpdateData)) {
            System.out.println(btnUpdateData.getId());
            showDeliveries();
        }
        if (source.equals(btnInsert)) {
            System.out.println(btnInsert.getId());
            Window owner = source.getScene().getWindow();
            Modality modality = Modality.WINDOW_MODAL;
            App.openScene(owner, modality, "delivery-insert.fxml", "Добавление", false);
        }
        if (source.equals(btnUpdate)) {
            System.out.println(btnUpdate.getId());
            Delivery selectedDeliveryRow = tableDelivery.getSelectionModel().getSelectedItem();
            if (selectedDeliveryRow != null) {
                DeliveryUpdateController.ID = selectedDeliveryRow.getNumber_of_delivery();
                DeliveryUpdateController.DESCRIPTION = selectedDeliveryRow.getDescription_of_delivery();
                DeliveryUpdateController.PRICE = String.valueOf(selectedDeliveryRow.getPrice_of_delivery());
                DeliveryUpdateController.ADDRESS = selectedDeliveryRow.getAddress_of_delivery();
                Window owner = source.getScene().getWindow();
                Modality modality = Modality.WINDOW_MODAL;
                App.openScene(owner, modality, "delivery-update.fxml", "Обновление", false);
            }
        }
        if (source.equals(btnDelete)) {
            Delivery selectedDeliveryRow = tableDelivery.getSelectionModel().getSelectedItem();
            DeliveryDao.delete(selectedDeliveryRow.getNumber_of_delivery());
            showDeliveries();
        }
        if (source.equals(btnSearch)) {
            System.out.println(btnSearch.getId());
            String sql = "SELECT * FROM delivery WHERE ";
            if (!tfFilterDeliveryNo.getText().isEmpty())
                sql += "(CAST(number_of_delivery AS TEXT) LIKE '" + Integer.parseInt(tfFilterDeliveryNo.getText()) + "') AND ";
            else
                sql += "(CAST(number_of_delivery AS TEXT) LIKE '%') AND ";
            if (!tfFilterPrice.getText().isEmpty())
                sql += "(CAST(price_of_delivery AS TEXT) LIKE '" + Integer.parseInt(tfFilterPrice.getText()) + "') AND ";
            else
                sql += "(CAST(price_of_delivery AS TEXT) LIKE '%') AND ";
            if (cbFilterAddress.getValue() != null)
                sql += "(address_of_delivery LIKE '" + cbFilterAddress.getValue() + "') AND ";
            else
                sql += "(address_of_delivery LIKE '%') AND ";
            if (!tfFilterDesc.getText().isEmpty())
                sql += "(description_of_delivery LIKE '" + tfFilterDesc.getText() + "')";
            else
                sql += "(description_of_delivery LIKE '%')";
            System.out.println(sql);
            tableDelivery.setItems(DeliveryDao.getByParams(sql));
        }
        if (source.equals(btnExport))
            Excel.export();
    }

    private void showDeliveries() {
        ObservableList<Delivery> deliveryObservableList = DeliveryDao.getAll();
        tableDelivery.setItems(deliveryObservableList);
    }
}
