package com.junfenglu.persistence;

import com.junfenglu.hospitalbeans.Inpatient;
import com.junfenglu.hospitalbeans.Medication;
import com.junfenglu.hospitalbeans.Patient;
import com.junfenglu.hospitalbeans.Surgical;
import com.junfenglu.properties.connectionbean.ConnectionConfigBean;
import com.junfenglu.properties.manager.PropertiesManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.ObservableList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the HospitalDAOImpl class
 * @author Junfeng Lu
 */
public class HospitalDAOImplTest {

    private PropertiesManager pm;
    private ConnectionConfigBean connectionConfig;
    private HospitalDAOImpl instance;

    public HospitalDAOImplTest() {

    }

    @Before
    public void init() throws IOException {
        pm = new PropertiesManager();
        connectionConfig = pm.loadTextProperties("", "TextProps");
        instance = new HospitalDAOImpl(connectionConfig);
    }

    /**
     * Test of findAll method, of class PatientDAOImpl.
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testFindAllRecords() throws Exception {

        ObservableList<Patient> result = instance.findAll();
        assertEquals("Expected to retrieve 6 records but did not", 6, result.size());
    }

    /**
     * Test of create method, of class PatientDAOImpl.
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testCreatePatient() throws Exception {
        int result;

        Patient patient = new Patient(-1, "Bryant", "Kobe", "Cancer", new Timestamp((new Date()).getTime()), new Timestamp((new Date()).getTime()));
        result = instance.create(patient);

        assertEquals("Inserted ID expected to be 6 but was not", 6, result);

    }

    /**
     * Test of update method, of class PatientDAOImpl.
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testUpdatePatient() throws Exception {
        int result;

        Patient patient = new Patient(5, "james", "Bond", "ACL", new Timestamp((new Date()).getTime()), new Timestamp((new Date()).getTime()));
        result = instance.update(patient);

        assertEquals("# of record updated expected to be 1 but was not", 1, result);
    }

    /**
     * Test of delete method, of class PatientDAOImpl.
     * @throws java.lang.Exception
     */

    @Test(timeout = 10000)
    public void testDeletePatient() throws Exception {
        int result;
        result = instance.deletePatient(6);

        assertEquals("# of record(s) deleted expected to be 1 but was not", 1, result);
    }

    @Test(timeout = 10000)
    public void testfindInpatients() throws Exception {
        int result;
        result = instance.findInpatients(3).size();

        assertEquals("# of inpatient record(s) expected to be 7 but was not", 7, result);
    }

    @Test(timeout = 10000)
    public void testfindSurgicals() throws Exception {
        int result;
        result = instance.findSurgicals(3).size();

        assertEquals("# of surgicals record(s) expected to be 1 but was not", 1, result);
    }

    @Test(timeout = 10000)
    public void testfindMedications() throws Exception {
        int result;
        result = instance.findMedications(3).size();

        assertEquals("# of Medications record(s) expected to be 1 but was not", 1, result);
    }

    
    /**
     * Test of create inpatient method
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testCreateInpatient() throws Exception {
        int result;

        Inpatient inpatient = new Inpatient(-1, 2, new Timestamp((new Date()).getTime()), "", new BigDecimal("50.0"), new BigDecimal(
                "500.0"), new BigDecimal("600.0"));
        result = instance.create(inpatient);

        assertEquals("Inserted ID expected to be 21 but was not", 21, result);

    }

    /**
     * Test of update inpatient method.
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testUpdateInPatient() throws Exception {
        int result;

        Inpatient inpatient = new Inpatient(11, 3, new Timestamp((new Date()).getTime()), "", new BigDecimal("66.0"), new BigDecimal(
                "700.0"), new BigDecimal("400.0"));
        result = instance.update(inpatient);

        assertEquals("# of record updated expected to be 1 but was not", 1, result);
    }

    /**
     * Test of delete inpatient method.
     * @throws java.lang.Exception
     */

    @Test(timeout = 10000)
    public void testDeleteInpatient() throws Exception {
        int result;
        result = instance.deleteInpatient(19);

        assertEquals("# of record(s) deleted expected to be 1 but was not", 1, result);
    }

    
    /**
     * Test of create surgical method
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testCreateSurgical() throws Exception {
        int result;

        Surgical surgical = new Surgical(-1, 2, new Timestamp((new Date()).getTime()), "", new BigDecimal("0.0"), new BigDecimal(
                "0.0"), new BigDecimal("0.0"));
        result = instance.create(surgical);

        assertEquals("Inserted ID expected to be 6 but was not", 6, result);

    }

    /**
     * Test of update surgical method.
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testUpdateSurgical() throws Exception {
        int result;

        Surgical surgical = new Surgical(3, 3, new Timestamp((new Date()).getTime()), "", new BigDecimal("0.0"), new BigDecimal(
                "10.0"), new BigDecimal("60.0"));
        result = instance.update(surgical);

        assertEquals("# of record updated expected to be 1 but was not", 1, result);
    }

    /**
     * Test of delete surgical method.
     * @throws java.lang.Exception
     */

    @Test(timeout = 10000)
    public void testDeleteSurgical() throws Exception {
        int result;
        result = instance.deleteSurgical(6);

        assertEquals("# of record(s) deleted expected to be 1 but was not", 1, result);
    }

    
    /**
     * Test of create medication method
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testCreateMedicationl() throws Exception {
        int result;

        Medication medication = new Medication(-1, 2, new Timestamp((new Date()).getTime()), "", new BigDecimal("0.0"), new BigDecimal(
                "0.0"));
        result = instance.create(medication);

        assertEquals("Inserted ID expected to be 6 but was not", 6, result);

    }

    /**
     * Test of update medication method.
     * @throws java.lang.Exception
     */
    @Test(timeout = 10000)
    public void testUpdateMedication() throws Exception {
        int result;

        Medication medication = new Medication(3, 3, new Timestamp((new Date()).getTime()), "", new BigDecimal("0.0"), new BigDecimal(
                "0.0"));
        result = instance.update(medication);

        assertEquals("# of record updated expected to be 1 but was not", 1, result);
    }

    /**
     * Test of delete medication method.
     * @throws java.lang.Exception
     */

    @Test(timeout = 10000)
    public void testDeleteMedication() throws Exception {
        int result;
        result = instance.deleteMedication(1);

        assertEquals("# of record(s) deleted expected to be 1 but was not", 1, result);
    }

}
