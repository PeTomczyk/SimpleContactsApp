package com.petom.contacts;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.petom.contacts.datamodel.Contact;
import javafx.scene.paint.Color;


public class ContactController {

    @FXML
    private Label firstNameLabel;
    @FXML
    private TextField firstNameField;
    @FXML
    private Label lastNameLabel;
    @FXML
    private TextField lastNameField;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField notesField;

    public Contact getNewContact() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String notes = notesField.getText().trim();

        Contact contact = new Contact(firstName, lastName, phoneNumber, notes);
        return contact;
    }

    public void editContact(Contact selectedContact) {
        firstNameField.setText(selectedContact.getFirstName());
        lastNameField.setText(selectedContact.getLastName());
        phoneNumberField.setText(selectedContact.getPhoneNumber());
        notesField.setText(selectedContact.getNotes());
    }

    public void updateContact(Contact selectedContact) {
        selectedContact.setFirstName(firstNameField.getText());
        selectedContact.setLastName(lastNameField.getText());
        selectedContact.setPhoneNumber(phoneNumberField.getText());
        selectedContact.setNotes(notesField.getText());
    }

    public boolean isDataOK() {
        boolean isOk = true;

        if ((firstNameField.getText().trim().equals(""))) {
            firstNameLabel.setTextFill(Color.RED);
            isOk = false;
        } else {
            firstNameLabel.setTextFill(Color.BLACK);
        }

        if ((lastNameField.getText().trim().equals(""))) {
            lastNameLabel.setTextFill(Color.RED);
            isOk = false;
        } else {
            lastNameLabel.setTextFill(Color.BLACK);
        }

        if ((phoneNumberField.getText().trim().equals(""))) {
            phoneNumberLabel.setTextFill(Color.RED);
            isOk = false;
        } else {
            phoneNumberLabel.setTextFill(Color.BLACK);
        }

        if (!isOk) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Contact Data");
            alert.setHeaderText(null);
            alert.setContentText("Please provide all required information to add new contact.");
            alert.showAndWait();
        }
        return isOk;
    }
}
