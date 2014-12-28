package com.junfenglu.persistence;

import com.junfenglu.hospitalbeans.Inpatient;
import com.junfenglu.hospitalbeans.Medication;
import com.junfenglu.hospitalbeans.Patient;
import com.junfenglu.hospitalbeans.Surgical;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 * Interface for data persistence class
 * @author Junfeng Lu
 */
public interface HospitalDAO {

    // Create
    public int create(Patient patient) throws SQLException;

    public int create(Inpatient inpatient) throws SQLException;

    public int create(Medication medication) throws SQLException;

    public int create(Surgical surgical) throws SQLException;

    // Read
    public ObservableList<Patient> findAll() throws SQLException;

    // find patient by ID
    public Patient findByID(int patientID) throws SQLException;

    // Update
    public int update(Patient patient) throws SQLException;

    public int update(Inpatient inpatient) throws SQLException;

    public int update(Medication medication) throws SQLException;

    public int update(Surgical surgical) throws SQLException;

    // Delete
    public int deletePatient(int patientID) throws SQLException;

    public int deleteInpatient(int inpatientID) throws SQLException;

    public int deleteMedication(int medicationID) throws SQLException;

    public int deleteSurgical(int surgicalID) throws SQLException;

// retrieve list of inpatients
    public ObservableList<Inpatient> findInpatients(int patientID) throws SQLException;
    
    public ObservableList<Medication> findMedications(int patientID) throws SQLException;
    
    public ObservableList<Surgical> findSurgicals(int patientID) throws SQLException;

}
