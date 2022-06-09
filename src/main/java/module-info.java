module glass.aisglass {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires org.apache.poi.poi;


    exports glass.aisglass.bin;
    opens glass.aisglass.bin to javafx.fxml;
    exports glass.aisglass.controllers;
    exports glass.aisglass.models;
    opens glass.aisglass.controllers to javafx.fxml;
}