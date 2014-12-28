package com.junfenglu.persistence;

import com.junfenglu.hospitalbeans.Patient;
import com.junfenglu.hospitalbeans.Inpatient;
import com.junfenglu.hospitalbeans.Surgical;
import com.junfenglu.hospitalbeans.Medication;
import com.junfenglu.properties.connectionbean.ConnectionConfigBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Data persistence class
 *
 * @author Junfeng Lu
 */
public class HospitalDAOImpl implements HospitalDAO {

    // Get the logger

    private final Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());
    private final Marker fatal = MarkerFactory.getMarker("FATAL");

    private final String url;
    private final String user;
    private final String password;

    public HospitalDAOImpl(ConnectionConfigBean connectionConfig) {
        super();

        // Retrieve database configuration data from connection bean
        url = connectionConfig.getUrl() + ":" + connectionConfig.getPort() + "/" + connectionConfig.getDatabase();
        user = connectionConfig.getUsername();
        password = connectionConfig.getPassword();
    }

    /**
     * Create a new patient record
     *
     * @param patient
     * @return an integer which is the generated ID
     * @throws SQLException Return the ID of the new patient record
     */
    @Override
    public int create(Patient patient) throws SQLException {

        int generatedKey = 0;
        String createQuery = "INSERT INTO PATIENT (LASTNAME, FIRSTNAME, DIAGNOSIS, ADMISSIONDATE, RELEASEDATE) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(createQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, patient.getLastName());
            ps.setString(2, patient.getFirstName());
            ps.setString(3, patient.getDiagnosis());
            ps.setTimestamp(4, patient.getAdmissionDate());
            ps.setTimestamp(5, patient.getReleaseDate());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
        }
        log.info("Inserted patient ID is : " + generatedKey);
        return generatedKey;
    }

    /**
     * Create a new inpatient record
     *
     * @param inpatient
     * @return an integer which is the generated ID
     * @throws SQLException Return the ID of the new inpatient record
     */
    @Override
    public int create(Inpatient inpatient) throws SQLException {

        int generatedKey = 0;
        String createQuery = "INSERT INTO INPATIENT (PATIENTID,DATEOFSTAY,ROOMNUMBER,DAILYRATE,SUPPLIES,SERVICES) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(createQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, inpatient.getPatientID());
            ps.setTimestamp(2, inpatient.getDateOfStay());
            ps.setString(3, inpatient.getRoomNumber());
            ps.setBigDecimal(4, inpatient.getDailyRate());
            ps.setBigDecimal(5, inpatient.getSupplies());
            ps.setBigDecimal(6, inpatient.getServices());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
        }
        log.info("Inserted inpatient ID is : " + generatedKey);
        return generatedKey;
    }

    /**
     * create a medication record
     *
     * @param medication
     * @return the ID of the new medication record
     * @throws SQLException
     *
     */
    @Override
    public int create(Medication medication) throws SQLException {
        int generatedKey = 0;
        String createQuery = "INSERT INTO MEDICATION (PATIENTID,DATEOFMED,MED,UNITCOST,UNITS) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(createQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, medication.getPatientID());
            ps.setTimestamp(2, medication.getDateOfMed());
            ps.setString(3, medication.getMed());
            ps.setBigDecimal(4, medication.getUnitCost());
            ps.setBigDecimal(5, medication.getUnits());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
        }
        log.info("Inserted medication ID is : " + generatedKey);
        return generatedKey;
    }

    /**
     * Create a surgical record
     *
     * @param surgical
     * @return the ID of the new surgical record
     * @throws SQLException
     *
     */
    @Override
    public int create(Surgical surgical) throws SQLException {
        int generatedKey = 0;
        String createQuery = "INSERT INTO SURGICAL (PATIENTID,DATEOFSURGERY,SURGERY,ROOMFEE,SURGEONFEE,SUPPLIES) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(createQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, surgical.getPatientID());
            ps.setTimestamp(2, surgical.getDateOfSurgery());
            ps.setString(3, surgical.getSurgery());
            ps.setBigDecimal(4, surgical.getRoomFee());
            ps.setBigDecimal(5, surgical.getSurgeonFee());
            ps.setBigDecimal(6, surgical.getSupplies());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
        }
        log.info("Inserted surgical ID is : " + generatedKey);
        return generatedKey;
    }

    /**
     * retrieve all the patient records
     *
     * @return an ObservableList of patients
     * @throws SQLException
     */
    @Override
    public ObservableList<Patient> findAll() throws SQLException {

        ObservableList<Patient> rows = FXCollections.observableArrayList();

        String selectQuery = "SELECT PATIENTID, LASTNAME, FIRSTNAME, DIAGNOSIS, ADMISSIONDATE, RELEASEDATE FROM PATIENT";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                // You must use PreparedStatements to guard against SQL Injection
                PreparedStatement pStatement = connection
                .prepareStatement(selectQuery);
                ResultSet resultSet = pStatement.executeQuery()) {
            while (resultSet.next()) {
                Patient patient = new Patient();

                patient.setPatientID(resultSet.getInt("PATIENTID"));
                patient.setLastName(resultSet.getString("LASTNAME"));
                patient.setFirstName(resultSet.getString("FIRSTNAME"));
                patient.setDiagnosis(resultSet.getString("DIAGNOSIS"));
                patient.setAdmissionDate(resultSet.getTimestamp("ADMISSIONDATE"));
                patient.setReleaseDate(resultSet.getTimestamp("RELEASEDATE"));

                rows.add(patient);
            }
        }
        log.info("# of records found : " + rows.size());
        return rows;

    }

    /**
     * find a patient record by ID
     *
     * @param patientID
     * @return a patient record
     * @throws SQLException
     */
    @Override
    public Patient findByID(int patientID) throws SQLException {
        Patient patient = new Patient();

        String selectQuery = "SELECT PATIENTID, LASTNAME, FIRSTNAME, DIAGNOSIS, ADMISSIONDATE, RELEASEDATE FROM PATIENT WHERE PATIENTID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                // You must use PreparedStatements to guard against SQL Injection
                PreparedStatement pStatement = connection
                .prepareStatement(selectQuery);) {
            pStatement.setInt(1, patientID);
            try (ResultSet resultSet = pStatement.executeQuery()) {

                while (resultSet.next()) {

                    patient.setPatientID(resultSet.getInt("PATIENTID"));
                    patient.setLastName(resultSet.getString("LASTNAME"));
                    patient.setFirstName(resultSet.getString("FIRSTNAME"));
                    patient.setDiagnosis(resultSet.getString("DIAGNOSIS"));
                    patient.setAdmissionDate(resultSet.getTimestamp("ADMISSIONDATE"));
                    patient.setReleaseDate(resultSet.getTimestamp("RELEASEDATE"));

                }
            }
        }
        log.info("ID of patient record found : " + patient.getPatientID());
        return patient;
    }

    /**
     * update a record of patient
     *
     * @param patient
     * @return the number of record(s) updated, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int update(Patient patient) throws SQLException {
        int updateCount = 0;

        String updateQuery = "UPDATE PATIENT SET LASTNAME=?, FIRSTNAME=?, DIAGNOSIS=?, ADMISSIONDATE=?, RELEASEDATE=? WHERE PATIENTID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(updateQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, patient.getLastName());
            ps.setString(2, patient.getFirstName());
            ps.setString(3, patient.getDiagnosis());
            ps.setTimestamp(4, patient.getAdmissionDate());
            ps.setTimestamp(5, patient.getReleaseDate());
            ps.setInt(6, patient.getPatientID());

            ps.executeUpdate();
            updateCount = ps.getUpdateCount();

        }

        log.info("ID of patient record updated : " + updateCount);
        return updateCount;

    }

    /**
     * update a record of inpatient
     *
     * @param inpatient
     * @return the number of record(s) updated, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int update(Inpatient inpatient) throws SQLException {
        int updateCount = 0;

        String updateQuery = "UPDATE INPATIENT SET PATIENTID=?, DATEOFSTAY=?, ROOMNUMBER=?, DAILYRATE=?, SUPPLIES=?, SERVICES=? WHERE ID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(updateQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, inpatient.getPatientID());
            ps.setTimestamp(2, inpatient.getDateOfStay());
            ps.setString(3, inpatient.getRoomNumber());
            ps.setBigDecimal(4, inpatient.getDailyRate());
            ps.setBigDecimal(5, inpatient.getSupplies());
            ps.setBigDecimal(6, inpatient.getServices());
            ps.setInt(7, inpatient.getInpatientID());

            ps.executeUpdate();
            updateCount = ps.getUpdateCount();

        }

        log.info("ID of inpatient record updated : " + updateCount);
        return updateCount;

    }

    /**
     * update a medication record
     *
     * @param medication
     * @return the number of record(s) updated, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int update(Medication medication) throws SQLException {
        int updateCount = 0;

        String updateQuery = "UPDATE MEDICATION SET PATIENTID=?, DATEOFMED=?, MED=?, UNITCOST=?, UNITS=? WHERE ID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(updateQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, medication.getPatientID());
            ps.setTimestamp(2, medication.getDateOfMed());
            ps.setString(3, medication.getMed());
            ps.setBigDecimal(4, medication.getUnitCost());
            ps.setBigDecimal(5, medication.getUnits());
            ps.setInt(6, medication.getID());

            ps.executeUpdate();
            updateCount = ps.getUpdateCount();

        }

        log.info("ID of medication record updated : " + updateCount);
        return updateCount;
    }

    /**
     * update a surgical record
     *
     * @param surgical
     * @return the number of record(s) updated, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int update(Surgical surgical) throws SQLException {
        int updateCount = 0;

        String updateQuery = "UPDATE SURGICAL SET PATIENTID=?, DATEOFSURGERY=?, SURGERY=?, ROOMFEE=?, SURGEONFEE=?, SUPPLIES=? WHERE ID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(updateQuery, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, surgical.getPatientID());
            ps.setTimestamp(2, surgical.getDateOfSurgery());
            ps.setString(3, surgical.getSurgery());
            ps.setBigDecimal(4, surgical.getRoomFee());
            ps.setBigDecimal(5, surgical.getSurgeonFee());
            ps.setBigDecimal(6, surgical.getSupplies());
            ps.setInt(7, surgical.getID());

            ps.executeUpdate();
            updateCount = ps.getUpdateCount();

        }

        log.info("ID of surgical record updated : " + updateCount);
        return updateCount;
    }

    /**
     * Delete a patient record
     *
     * @param patientID
     * @return the number of records deleted, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int deletePatient(int patientID) throws SQLException {
        int result = 0;

        String deleteQuery = "DELETE FROM PATIENT WHERE PATIENTID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(deleteQuery);) {
            ps.setInt(1, patientID);

            result = ps.executeUpdate();

        }
        log.info("# of patient records deleted : " + result);
        return result;
    }

    /**
     * Delete a inpatient record
     *
     * @param inpatientID
     * @return the number of records deleted, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int deleteInpatient(int inpatientID) throws SQLException {
        int result = 0;

        String deleteQuery = "DELETE FROM INPATIENT WHERE ID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(deleteQuery);) {
            ps.setInt(1, inpatientID);

            result = ps.executeUpdate();

        }
        log.info("# of inpatient records deleted : " + result);
        return result;
    }

    /**
     * delete a medication record
     *
     * @param medicationID
     * @return the number of records deleted, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int deleteMedication(int medicationID) throws SQLException {
        int result = 0;

        String deleteQuery = "DELETE FROM MEDICATION WHERE ID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(deleteQuery);) {
            ps.setInt(1, medicationID);

            result = ps.executeUpdate();

        }
        log.info("# of medication records deleted : " + result);
        return result;
    }

    /**
     * delete a surgical record
     *
     * @param surgicalID
     * @return the number of records deleted, actually 1 if successful
     * @throws SQLException
     */
    @Override
    public int deleteSurgical(int surgicalID) throws SQLException {
        int result = 0;

        String deleteQuery = "DELETE FROM SURGICAL WHERE ID=?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement ps = connection.prepareStatement(deleteQuery);) {
            ps.setInt(1, surgicalID);

            result = ps.executeUpdate();

        }
        log.info("# of surgical records deleted : " + result);
        return result;
    }

    /**
     * Find all the inpatient records of a patient
     *
     * @param patientID
     * @return an ObservableList of inpatients associated with a patient
     * @throws SQLException
     */
    @Override
    public ObservableList<Inpatient> findInpatients(int patientID) throws SQLException {
        ObservableList<Inpatient> rows = FXCollections.observableArrayList();

        String selectQuery = "SELECT ID, PATIENTID, DATEOFSTAY, ROOMNUMBER, DAILYRATE, SUPPLIES, SERVICES FROM INPATIENT WHERE PATIENTID = ?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement pStatement = connection
                .prepareStatement(selectQuery);) {
            pStatement.setInt(1, patientID);
            try (ResultSet resultSet = pStatement.executeQuery()) {

                while (resultSet.next()) {
                    Inpatient inpatient = new Inpatient();

                    inpatient.setInpatientID(resultSet.getInt("ID"));
                    inpatient.setPatientID(resultSet.getInt("PATIENTID"));
                    inpatient.setDateOfStay(resultSet.getTimestamp("DATEOFSTAY"));
                    inpatient.setRoomNumber(resultSet.getString("ROOMNUMBER"));
                    inpatient.setDailyRate(resultSet.getBigDecimal("DAILYRATE"));
                    inpatient.setSupplies(resultSet.getBigDecimal("SUPPLIES"));
                    inpatient.setServices(resultSet.getBigDecimal("SERVICES"));

                    rows.add(inpatient);

                }
            }
        }
        log.info("# of records found : " + rows.size());

        return rows;
    }

    /**
     * Find all the medication records of a patient
     *
     * @param patientID
     * @return an ObservableList of medications associated with a patient
     * @throws SQLException
     */
    @Override
    public ObservableList<Medication> findMedications(int patientID) throws SQLException {
        ObservableList<Medication> rows = FXCollections.observableArrayList();

        String selectQuery = "SELECT ID, PATIENTID,DATEOFMED,MED,UNITCOST,UNITS FROM MEDICATION WHERE PATIENTID = ?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement pStatement = connection
                .prepareStatement(selectQuery);) {
            pStatement.setInt(1, patientID);
            try (ResultSet resultSet = pStatement.executeQuery()) {

                while (resultSet.next()) {
                    Medication medication = new Medication();

                    medication.setID(resultSet.getInt("ID"));
                    medication.setPatientID(resultSet.getInt("PATIENTID"));
                    medication.setDateOfMed(resultSet.getTimestamp("DATEOFMED"));
                    medication.setMed(resultSet.getString("MED"));
                    medication.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                    medication.setUnits(resultSet.getBigDecimal("UNITS"));

                    rows.add(medication);

                }
            }
        }
        log.info("# of medication records found : " + rows.size());

        return rows;
    }

    /**
     * Find all the surgical records of a patient
     *
     * @param patientID
     * @return an ObservableList of surgicals associated with a patient
     * @throws SQLException
     */
    @Override
    public ObservableList<Surgical> findSurgicals(int patientID) throws SQLException {
        ObservableList<Surgical> rows = FXCollections.observableArrayList();

        String selectQuery = "SELECT ID, PATIENTID,DATEOFSURGERY,SURGERY,ROOMFEE,SURGEONFEE,SUPPLIES FROM SURGICAL WHERE PATIENTID = ?";

        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                PreparedStatement pStatement = connection
                .prepareStatement(selectQuery);) {
            pStatement.setInt(1, patientID);
            try (ResultSet resultSet = pStatement.executeQuery()) {

                while (resultSet.next()) {
                    Surgical surgical = new Surgical();

                    surgical.setID(resultSet.getInt("ID"));
                    surgical.setPatientID(resultSet.getInt("PATIENTID"));
                    surgical.setDateOfSurgery(resultSet.getTimestamp("DATEOFSURGERY"));
                    surgical.setSurgery(resultSet.getString("SURGERY"));
                    surgical.setRoomFee(resultSet.getBigDecimal("ROOMFEE"));
                    surgical.setSurgeonFee(resultSet.getBigDecimal("SURGEONFEE"));
                    surgical.setSupplies(resultSet.getBigDecimal("SUPPLIES"));

                    rows.add(surgical);

                }
            }
        }
        log.info("# of surgical records found : " + rows.size());

        return rows;
    }

}
