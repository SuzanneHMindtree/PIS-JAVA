package com.junfenglu.hospitalbeans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Model class for patient
 * @author Junfeng Lu
 */
public class Patient {

    private IntegerProperty patientID;
    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty diagnosis;
    private ObjectProperty<Timestamp> admissionDate;
    private ObjectProperty<Timestamp> releaseDate;

    private ArrayList<Inpatient> inpatients;
    private ArrayList<Surgical> surgicals;
    private ArrayList<Medication> medications;
    /**
     * Default constructor.
     */
    public Patient() {

        this(-1, "", "", "", new Timestamp(0l), new Timestamp(0l));
    }

    /**
     * Parameterized constructor.
     *
     * @param patientId
     * @param lastName
     * @param firstName
     * @param diagnosis
     * @param admissionDate
     * @param releaseDate
     */
    public Patient(int patientId, String lastName, String firstName,
            String diagnosis, Timestamp admissionDate, Timestamp releaseDate) {
        super();
        this.patientID = new SimpleIntegerProperty(patientId);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.diagnosis = new SimpleStringProperty(diagnosis);
        this.admissionDate = new SimpleObjectProperty<>(admissionDate);
        this.releaseDate = new SimpleObjectProperty<>(releaseDate);


    }

    public int getPatientID() {
        return patientID.get();
    }

    public void setPatientID(int patientID) {
        this.patientID.set(patientID);
    }

    public IntegerProperty PatientIDProperty() {
        return patientID;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty LastNameProperty() {
        return lastName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty FirstNameProperty() {
        return firstName;
    }

    public String getDiagnosis() {
        return diagnosis.get();
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis.set(diagnosis);
    }

    public StringProperty DiagnosisProperty() {
        return diagnosis;
    }

    public Timestamp getAdmissionDate() {
        return admissionDate.get();
    }

    public void setAdmissionDate(Timestamp admissionDate) {
        this.admissionDate.set(admissionDate);
    }

    public ObjectProperty<Timestamp> AdmissionDateProperty() {
        return admissionDate;
    }

    public Timestamp getReleaseDate() {
        return releaseDate.get();
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public ObjectProperty<Timestamp> ReleaseDateProperty() {
        return releaseDate;
    }

    public ArrayList<Inpatient> getInpatients() {
        return inpatients;
    }

    public void setInpatients(ArrayList<Inpatient> inpatients) {
        this.inpatients = inpatients;
    }

    public ArrayList<Surgical> getSurgicals() {
        return surgicals;
    }

    public void setSurgicals(ArrayList<Surgical> surgicals) {
        this.surgicals = surgicals;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }
    @Override
    public String toString() {
        return "Patient [patientId=" + patientID + ", lastName=" + lastName
                + ", firstName=" + firstName + ", diagnosis=" + diagnosis
                + ", admissionDate=" + admissionDate + ", releaseDate="
                + releaseDate + "]";
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.patientID);
        hash = 23 * hash + Objects.hashCode(this.lastName);
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.diagnosis);
        hash = 23 * hash + Objects.hashCode(this.admissionDate);
        hash = 23 * hash + Objects.hashCode(this.releaseDate);
        hash = 23 * hash + Objects.hashCode(this.inpatients);
        hash = 23 * hash + Objects.hashCode(this.surgicals);
        hash = 23 * hash + Objects.hashCode(this.medications);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        if (!Objects.equals(this.patientID, other.patientID)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.diagnosis, other.diagnosis)) {
            return false;
        }
        if (!Objects.equals(this.admissionDate, other.admissionDate)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.inpatients, other.inpatients)) {
            return false;
        }
        if (!Objects.equals(this.surgicals, other.surgicals)) {
            return false;
        }
        if (!Objects.equals(this.medications, other.medications)) {
            return false;
        }
        return true;
    }





}
