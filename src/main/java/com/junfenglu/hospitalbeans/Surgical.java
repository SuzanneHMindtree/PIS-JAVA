package com.junfenglu.hospitalbeans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




/**
 * Model class for surgical
 * @author Junfeng Lu
 */
public class Surgical {

    private IntegerProperty ID;
    private IntegerProperty patientID;
    private ObjectProperty<Timestamp> dateOfSurgery;
    private StringProperty surgery;
    private ObjectProperty<BigDecimal> roomFee;
    private ObjectProperty<BigDecimal> surgeonFee;
    private ObjectProperty<BigDecimal> supplies;

    /**
     * default constructor
     */
    public Surgical() {
        this(-1, -1, new Timestamp(0l), "", new BigDecimal("0.0"), new BigDecimal(
                "0.0"), new BigDecimal("0.0"));
    }

    /**
     * Parameterized constructor
     * @param ID
     * @param patientID
     * @param dateOfSurgery
     * @param surgery
     * @param roomFee
     * @param surgeonFee
     * @param supplies
     */
    public Surgical(int ID, int patientID, Timestamp dateOfSurgery,
            String surgery, BigDecimal roomFee, BigDecimal surgeonFee,
            BigDecimal supplies) {
        super();

        this.ID = new SimpleIntegerProperty(ID);
        this.patientID = new SimpleIntegerProperty(patientID);
        this.dateOfSurgery = new SimpleObjectProperty<>(dateOfSurgery);
        this.surgery = new SimpleStringProperty(surgery);
        this.roomFee = new SimpleObjectProperty<>(roomFee);
        this.surgeonFee = new SimpleObjectProperty<>(surgeonFee);
        this.supplies = new SimpleObjectProperty<>(supplies);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public IntegerProperty IDProperty() {
        return ID;
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

    public Timestamp getDateOfSurgery() {
        return dateOfSurgery.get();
    }

    public void setDateOfSurgery(Timestamp dateOfSurgery) {
        this.dateOfSurgery.set(dateOfSurgery);
    }

    public ObjectProperty<Timestamp> DateOfSurgeryProperty() {
        return dateOfSurgery;
    }

    public String getSurgery() {
        return surgery.get();
    }

    public void setSurgery(String surgery) {
        this.surgery.set(surgery);
    }

    public StringProperty SurgeryProperty() {
        return surgery;
    }

    public BigDecimal getRoomFee() {
        return roomFee.get();
    }

    public void setRoomFee(BigDecimal roomFee) {
        this.roomFee.set(roomFee);
    }

    public ObjectProperty<BigDecimal> RoomFeeProperty() {
        return roomFee;
    }

    public BigDecimal getSurgeonFee() {
        return surgeonFee.get();
    }

    public void setSurgeonFee(BigDecimal surgeonFee) {
        this.surgeonFee.set(surgeonFee);
    }

    public ObjectProperty<BigDecimal> SurgeonFeeProperty() {
        return surgeonFee;
    }

    public BigDecimal getSupplies() {
        return supplies.get();
    }

    public void setSupplies(BigDecimal supplies) {
        this.supplies.set(supplies);
    }

    public ObjectProperty<BigDecimal> SuppliesProperty() {
        return supplies;
    }

    @Override
    public String toString() {
        return "Surgical{" + "ID=" + ID + ", patientID=" + patientID + ", dateOfSurgery=" + dateOfSurgery + ", surgery=" + surgery + ", roomFee=" + roomFee + ", surgeonFee=" + surgeonFee + ", supplies=" + supplies + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.ID);
        hash = 23 * hash + Objects.hashCode(this.patientID);
        hash = 23 * hash + Objects.hashCode(this.dateOfSurgery);
        hash = 23 * hash + Objects.hashCode(this.surgery);
        hash = 23 * hash + Objects.hashCode(this.roomFee);
        hash = 23 * hash + Objects.hashCode(this.surgeonFee);
        hash = 23 * hash + Objects.hashCode(this.supplies);
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
        final Surgical other = (Surgical) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.patientID, other.patientID)) {
            return false;
        }
        if (!Objects.equals(this.dateOfSurgery, other.dateOfSurgery)) {
            return false;
        }
        if (!Objects.equals(this.surgery, other.surgery)) {
            return false;
        }
        if (!Objects.equals(this.roomFee, other.roomFee)) {
            return false;
        }
        if (!Objects.equals(this.surgeonFee, other.surgeonFee)) {
            return false;
        }
        if (!Objects.equals(this.supplies, other.supplies)) {
            return false;
        }
        return true;
    }

   

}
