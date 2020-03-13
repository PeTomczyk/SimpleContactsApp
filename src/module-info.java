module SimpleContactsApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;

    opens com.petom.contacts;
    opens com.petom.contacts.datamodel;
}