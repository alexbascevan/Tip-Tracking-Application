module interimcode {
    requires javafx.controls;
    requires javafx.fxml;

    opens interimcode to javafx.fxml;
    exports interimcode;
}
