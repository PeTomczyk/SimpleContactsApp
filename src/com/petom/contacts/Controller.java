package com.petom.contacts;

import com.petom.contacts.datamodel.Contact;
import com.petom.contacts.datamodel.ContactData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainWindow;

    @FXML
    private TableView<Contact> contactsTableView;
    @FXML
    private TableColumn<Contact, String> colFirstName;
    @FXML
    private TableColumn<Contact, String> colLastName;
    @FXML
    private TableColumn<Contact, String> colPhoneNumber;
    @FXML
    private TableColumn<Contact, String> colNotes;

    private ContactData data;

    public void initialize() {
        data = new ContactData();
        data.loadContacts();

        colFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("Notes"));

        contactsTableView.setItems(data.getContacts());
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }

    @FXML
    public void handleAdd(ActionEvent actionEvent) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactController contactController = fxmlLoader.getController();

        Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btOk.addEventFilter(ActionEvent.ACTION, event -> {
            if (!contactController.isDataOK()) {
                event.consume();
            }
        });
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Contact newItem = contactController.getNewContact();
            data.addContact(newItem);
            data.saveContacts();
        }
    }


    @FXML
    public void handleEdit(ActionEvent actionEvent) {
        Contact selectedContact = contactsTableView.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you want to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Edit Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactController contactController = fxmlLoader.getController();
        contactController.editContact(selectedContact);

        Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btOk.addEventFilter(ActionEvent.ACTION, event -> {
            if (!contactController.isDataOK()) {
                event.consume();
            }
        });

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            contactController.updateContact(selectedContact);
            data.saveContacts();
            contactsTableView.refresh();
        }
    }

    @FXML
    public void handleDelete(ActionEvent actionEvent) {
        Contact contact = contactsTableView.getSelectionModel().getSelectedItem();
        if (contact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you want to delete.");
            alert.showAndWait();
            return;
        }
        deleteContact(contact);
    }

    public void deleteContact(Contact item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Delete contact: " + item.getFirstName() + ", " + item.getLastName());
        alert.setContentText("Are you sure? Press OK to confirm.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            data.deleteContact(item);
            data.saveContacts();
        }
    }

    @FXML
    public void handleAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Simple Contacts App");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for using this application!");
        alert.showAndWait();
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        Contact selectedItem = contactsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteContact(selectedItem);
            }
        }
    }
}




