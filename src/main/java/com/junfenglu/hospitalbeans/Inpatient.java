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
 * Model class for inpatient (a stay of a patient )
 * @author Junfeng Lu
 */

public class Inpatient {

    private IntegerProperty inpatientID;
    private IntegerProperty patientID;
    private ObjectProperty<Timestamp> dateOfStay;
    private StringProperty roomNumber;
    private ObjectProperty<BigDecimal> dailyRate;
    private ObjectProperty<BigDecimal> supplies;
    private ObjectProperty<BigDecimal> services;

    /**
     * default constructor
     */
    public Inpatient() {
        this(-1, -1, new Timestamp(0l), "", new BigDecimal("0.0"), new BigDecimal(
                "0.0"), new BigDecimal("0.0"));
    }

    /**
     * Parameterized constructor
     * @param iD
     * @param patientID
     * @param dateOfStay
     * @param roomNumber
     * @param dailyRate
     * @param supplies
     * @param services
     */
    public Inpatient(int inpatientID, int patientID, Timestamp dateOfStay,
            String roomNumber, BigDecimal dailyRate, BigDecimal supplies,
            BigDecimal services) {
        super();

        this.inpatientID = new SimpleIntegerProperty(inpatientID);
        this.patientID = new SimpleIntegerProperty(patientID);
        this.dateOfStay = new SimpleObjectProperty<Timestamp>(dateOfStay);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.dailyRate = new SimpleObjectProperty<BigDecimal>(dailyRate);
        this.supplies = new SimpleObjectProperty<BigDecimal>(supplies);
        this.services = new SimpleObjectProperty<BigDecimal>(services);
    }

    public int getInpatientID() {
        return inpatientID.get();
    }

    public void setInpatientID(int inpatientID) {
        this.inpatientID.set(inpatientID);
    }

    public IntegerProperty InpatientIDProperty() {
        return inpatientID;
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

    public Timestamp getDateOfStay() {
        return dateOfStay.get();
    }

    public void setDateOfStay(Timestamp dateOfStay) {
        this.dateOfStay.set(dateOfStay);
    }

    public ObjectProperty<Timestamp> DateOfStayProperty() {
        return dateOfStay;
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public StringProperty RoomNumberProperty() {
        return roomNumber;
    }

    public BigDecimal getDailyRate() {
        return dailyRate.get();
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate.set(dailyRate);
    }

    public ObjectProperty<BigDecimal> DailyRateProperty() {
        return dailyRate;
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

    public BigDecimal getServices() {
        return services.get();
    }

    public void setServices(BigDecimal services) {
        this.services.set(services);
    }

    public ObjectProperty<BigDecimal> ServicesProperty() {
        return services;
    }

    @Override
    public String toString() {
        return "Inpatient [inpatientID=" + inpatientID + ", patientID=" + patientID
                + ", dateOfStay=" + dateOfStay + ", roomNumber=" + roomNumber
                + ", dailyRate=" + dailyRate + ", supplies=" + supplies
                + ", services=" + services + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.inpatientID);
        hash = 89 * hash + Objects.hashCode(this.patientID);
        hash = 89 * hash + Objects.hashCode(this.dateOfStay);
        hash = 89 * hash + Objects.hashCode(this.roomNumber);
        hash = 89 * hash + Objects.hashCode(this.dailyRate);
        hash = 89 * hash + Objects.hashCode(this.supplies);
        hash = 89 * hash + Objects.hashCode(this.services);
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
        final Inpatient other = (Inpatient) obj;
        if (!Objects.equals(this.inpatientID, other.inpatientID)) {
            return false;
        }
        if (!Objects.equals(this.patientID, other.patientID)) {
            return false;
        }
        if (!Objects.equals(this.dateOfStay, other.dateOfStay)) {
            return false;
        }
        if (!Objects.equals(this.roomNumber, other.roomNumber)) {
            return false;
        }
        if (!Objects.equals(this.dailyRate, other.dailyRate)) {
            return false;
        }
        if (!Objects.equals(this.supplies, other.supplies)) {
            return false;
        }
        if (!Objects.equals(this.services, other.services)) {
            return false;
        }
        return true;
    }

}
