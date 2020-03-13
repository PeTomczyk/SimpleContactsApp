package com.petom.contacts.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    //SimpleStringProperty
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty notes;

    public Contact(String firstName, String lastName, String phoneNumber, String notes) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.notes = new SimpleStringProperty(notes);
    }

    public Contact() {
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.phoneNumber = new SimpleStringProperty("");
        this.notes = new SimpleStringProperty("");
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getNotes() {
        return notes.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "First Name = " + firstName +
                ", Last Name = " + lastName +
                ", Phone Number = " + phoneNumber +
                ", Notes = " + notes +
                '}';
    }
}
