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
 * Model class for medication 
 * @author Junfeng Lu
 */

public class Medication {

    private IntegerProperty ID;
    private IntegerProperty patientID;
    private ObjectProperty<Timestamp> dateOfMed;
    private StringProperty med;
    private ObjectProperty<BigDecimal> unitCost;
    private ObjectProperty<BigDecimal> units;

    /**
     * Default constructor
     */
    public Medication() {
        this(-1, -1, new Timestamp(0l), "", new BigDecimal("0.0"), new BigDecimal(
                "0.0"));
    }

    /**
     * parameterized constructor
     * @param iD
     * @param patientID
     * @param dateOfMed
     * @param med
     * @param unitCost
     * @param units
     */
    public Medication(int ID, int patientID, Timestamp dateOfMed, String med,
            BigDecimal unitCost, BigDecimal units) {
        super();

        this.ID = new SimpleIntegerProperty(ID);
        this.patientID = new SimpleIntegerProperty(patientID);
        this.dateOfMed = new SimpleObjectProperty<Timestamp>(dateOfMed);
        this.med = new SimpleStringProperty(med);
        this.unitCost = new SimpleObjectProperty<BigDecimal>(unitCost);
        this.units = new SimpleObjectProperty<BigDecimal>(units);
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

    public Timestamp getDateOfMed() {
        return dateOfMed.get();
    }

    public void setDateOfMed(Timestamp dateOfMed) {
        this.dateOfMed.set(dateOfMed);
    }

    public ObjectProperty<Timestamp> DateOfMedProperty() {
        return dateOfMed;
    }

    public String getMed() {
        return med.get();
    }

    public void setMed(String med) {
        this.med.set(med);
    }

    public StringProperty MedProperty() {
        return med;
    }

    public BigDecimal getUnitCost() {
        return unitCost.get();
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost.set(unitCost);
    }

    public ObjectProperty<BigDecimal> UnitCostProperty() {
        return unitCost;
    }

    public BigDecimal getUnits() {
        return units.get();
    }

    public void setUnits(BigDecimal units) {
        this.units.set(units);
    }

    public ObjectProperty<BigDecimal> UnitsProperty() {
        return units;
    }

    @Override
    public String toString() {
        return "Medication{" + "ID=" + ID + ", patientID=" + patientID + ", dateOfMed=" + dateOfMed + ", med=" + med + ", unitCost=" + unitCost + ", units=" + units + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.ID);
        hash = 59 * hash + Objects.hashCode(this.patientID);
        hash = 59 * hash + Objects.hashCode(this.dateOfMed);
        hash = 59 * hash + Objects.hashCode(this.med);
        hash = 59 * hash + Objects.hashCode(this.unitCost);
        hash = 59 * hash + Objects.hashCode(this.units);
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
        final Medication other = (Medication) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.patientID, other.patientID)) {
            return false;
        }
        if (!Objects.equals(this.dateOfMed, other.dateOfMed)) {
            return false;
        }
        if (!Objects.equals(this.med, other.med)) {
            return false;
        }
        if (!Objects.equals(this.unitCost, other.unitCost)) {
            return false;
        }
        if (!Objects.equals(this.units, other.units)) {
            return false;
        }
        return true;
    }




}
