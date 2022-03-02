module com.mycompany.programword {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.programword to javafx.fxml;
    exports com.mycompany.programword;
}
