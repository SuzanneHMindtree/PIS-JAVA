package com.junfenglu.view;

import com.junfenglu.hospitalbeans.Inpatient;
import com.junfenglu.hospitalbeans.Medication;
import com.junfenglu.hospitalbeans.Patient;
import com.junfenglu.hospitalbeans.Surgical;
import com.junfenglu.persistence.HospitalDAO;
import com.junfenglu.util.DateUtil;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * HospitalOverview controller class
 *
 * @author Junfeng Lu
 */
public class HospitalOverviewController {

    // Get the logger
    private final Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());
    private final Marker fatal = MarkerFactory.getMarker("FATAL");

    // Use the tables, columns, and labels in the FXML
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> firstNameColumn;
    @FXML
    private TableColumn<Patient, String> lastNameColumn;

    @FXML
    private Label IDLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label diagnosisLabel;
    @FXML
    private Label admissionDateLabel;
    @FXML
    private Label releaseDateLabel;

    // inpatient table
    @FXML
    private TableView<Inpatient> inpatientTable;
    @FXML
    private TableColumn<Inpatient, Timestamp> dateOfStayCol;
    @FXML
    private TableColumn<Inpatient, String> roomNbrCol;
    @FXML
    private TableColumn<Inpatient, BigDecimal> dailyRateCol;
    @FXML
    private TableColumn<Inpatient, BigDecimal> suppliesCol;
    @FXML
    private TableColumn<Inpatient, BigDecimal> servicesCol;

    // medication table
    @FXML
    private TableView<Medication> medicationTable;
    @FXML
    private TableColumn<Medication, Timestamp> dateOfMedCol;
    @FXML
    private TableColumn<Medication, String> medCol;
    @FXML
    private TableColumn<Medication, BigDecimal> unitsCol;
    @FXML
    private TableColumn<Medication, BigDecimal> unitCostCol;

    // surgical table
    @FXML
    private TableView<Surgical> surgicalTable;
    @FXML
    private TableColumn<Surgical, Timestamp> dateOfSurgeryCol;
    @FXML
    private TableColumn<Surgical, String> surgeryCol;
    @FXML
    private TableColumn<Surgical, BigDecimal> roomFeeCol;
    @FXML
    private TableColumn<Surgical, BigDecimal> surgeonFeeCol;
    @FXML
    private TableColumn<Surgical, BigDecimal> surgerySuppliesCol;

    // Reference to the data persistence classes
    private HospitalDAO hospitalDAO;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public HospitalOverviewController() {
        super();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the patient table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().FirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().LastNameProperty());

        // Initialize the inpatient table.
        dateOfStayCol.setCellValueFactory(cellData -> cellData.getValue().DateOfStayProperty());
        roomNbrCol.setCellValueFactory(cellData -> cellData.getValue().RoomNumberProperty());
        dailyRateCol.setCellValueFactory(cellData -> cellData.getValue().DailyRateProperty());
        suppliesCol.setCellValueFactory(cellData -> cellData.getValue().SuppliesProperty());
        servicesCol.setCellValueFactory(cellData -> cellData.getValue().ServicesProperty());

        // Initialize the surgical table.
        dateOfSurgeryCol.setCellValueFactory(cellData -> cellData.getValue().DateOfSurgeryProperty());
        surgeryCol.setCellValueFactory(cellData -> cellData.getValue().SurgeryProperty());
        roomFeeCol.setCellValueFactory(cellData -> cellData.getValue().RoomFeeProperty());
        surgeonFeeCol.setCellValueFactory(cellData -> cellData.getValue().SurgeonFeeProperty());
        surgerySuppliesCol.setCellValueFactory(cellData -> cellData.getValue().SuppliesProperty());

        // Initialize the medication table.
        dateOfMedCol.setCellValueFactory(cellData -> cellData.getValue().DateOfMedProperty());
        medCol.setCellValueFactory(cellData -> cellData.getValue().MedProperty());
        unitsCol.setCellValueFactory(cellData -> cellData.getValue().UnitsProperty());
        unitCostCol.setCellValueFactory(cellData -> cellData.getValue().UnitCostProperty());

        // Clear patient details.
        showPatientDetails(null);

        // adjust column widths
        adjustColumnWidths();

        // Listen for selection changes and show the patient details when
        // changed.
        patientTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> showPatientDetails(newValue));

        patientTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> setInpatientData(newValue));

        patientTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> setSurgicalData(newValue));

        patientTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> setMedicationData(newValue));

    }

    /**
     * Set the data for the patient table
     *
     * @param patientData
     */
    public void setPatientData(HospitalDAO hospitalDao) {
        this.hospitalDAO = hospitalDao;

        // Add observable list data to the table
        try {
            patientTable.setItems(this.hospitalDAO.findAll());
        } catch (SQLException ex) {

            log.error(fatal, "SQL error: " + ex.getMessage());
        }
    }

    /**
     * Show the details of a patient when clicked in the table
     *
     * @param patient
     */
    private void showPatientDetails(Patient patient) {
        if (patient != null) {
            // Fill the labels with info from the patient object.
            IDLabel.setText(Integer.toString(patient.getPatientID()));
            firstNameLabel.setText(patient.getFirstName());
            lastNameLabel.setText(patient.getLastName());
            diagnosisLabel.setText(patient.getDiagnosis());
            admissionDateLabel.setText(DateUtil.format(patient.getAdmissionDate().toLocalDateTime().toLocalDate()));
            releaseDateLabel.setText(DateUtil.format(patient.getReleaseDate().toLocalDateTime().toLocalDate()));
        } else {
            // Patient is null, remove all the text.
            IDLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            diagnosisLabel.setText("");
            admissionDateLabel.setText("");
            releaseDateLabel.setText("");

        }
    }

    /**
     * Called when the user clicks the New button. Opens a dialog to edit
     * details for a new patient.
     */
    @FXML
    private void handleNewPatient() {
        Patient tempPatient = new Patient();
        EditDialog patientEditDialog = new EditDialog();

        boolean okClicked = patientEditDialog.showPatientEditDialog(tempPatient);
        if (okClicked) {

            try {
                int createdInSQL = hospitalDAO.create(tempPatient);
                if (createdInSQL != 0) {

                    patientTable.setItems(this.hospitalDAO.findAll());

                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Create");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        }
    }

    /**
     * Called when the user clicks the Edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPatient() {
        EditDialog patientEditDialog = new EditDialog();

        Patient selectedPatient = patientTable.getSelectionModel()
                .getSelectedItem();
        if (selectedPatient != null) {
            boolean okClicked = patientEditDialog
                    .showPatientEditDialog(selectedPatient);
            if (okClicked) {

                try {

                    int editedInSQL = hospitalDAO.update(selectedPatient);
                    if (editedInSQL != 0) {
                        showPatientDetails(selectedPatient);
                    }

                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Update");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();

                    log.error(fatal, "SQL error: " + ex.getMessage());

                }

            }

        } else {
            // Nothing selected.

            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("No Selection");
            dialog.setHeaderText("No patient Selected to Edit");
            dialog.setContentText("Please select a person in the table.");
            dialog.show();
        }
    }

    /**
     * Called when the user clicks on the Delete button in the patient tab.
     */
    @FXML
    private void handleDeletePatient() {
        int inpatientsSize = 0;
        int medicationsSize = 0;
        int surgicalsSize = 0;

        int selectedIndex = patientTable.getSelectionModel().getSelectedIndex();
        Patient selectedPatient = patientTable.getSelectionModel()
                .getSelectedItem();

        try {
            inpatientsSize = hospitalDAO.findInpatients(selectedPatient.getPatientID()).size();
            medicationsSize = hospitalDAO.findMedications(selectedPatient.getPatientID()).size();
            surgicalsSize = hospitalDAO.findSurgicals(selectedPatient.getPatientID()).size();

        } catch (SQLException ex) {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("SQL Error");
            dialog.setHeaderText("Error in SQL Delete");
            dialog.setContentText("SQL error:" + ex.getMessage());
            dialog.show();
            ;
            log.error(fatal, "SQL error: " + ex.getMessage());
        }

        if (inpatientsSize > 0 || medicationsSize > 0 || surgicalsSize > 0) {
// Warning! unable to delete record in the parent table due to constraints in the database

            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("SQL Constraint");
            dialog.setHeaderText("Constraint");
            dialog.setContentText("Unable to delete the record. There are records related to this record in the children tables");
            dialog.show();
        } else {
            if (selectedIndex >= 0) {

                try {
                    int deletedInSQL = hospitalDAO.deletePatient(selectedPatient.getPatientID());
                    if (deletedInSQL != 0) {
                        patientTable.getItems().remove(selectedIndex);
                    }
                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Delete");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();
                    //System.out.println("SQL error:" + ex.getMessage());
                    log.error(fatal, "SQL error: " + ex.getMessage());
                }
            } else {
                // Nothing selected.

                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("No Selection");
                dialog.setHeaderText("No Patient Selected to Delete");
                dialog.setContentText("Please select a patient in the table.");
                dialog.show();
            }
        }
    }

    /**
     * Populate the inpatient table with data
     *
     * @param patientData
     */
    public void setInpatientData(Patient patient) {
        if (patient != null) {
            try {

                inpatientTable.setItems(this.hospitalDAO.findInpatients(patient.getPatientID()));

            } catch (SQLException ex) {

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        }
    }

    /**
     * Called when the user clicks the New button. Opens a dialog to edit
     * details for a new inpatient.
     */
    @FXML
    private void handleNewInpatient() {
        Inpatient tempInpatient = new Inpatient();

        EditDialog inpatientEditDialog = new EditDialog();
        Patient selectedPatient = patientTable.getSelectionModel()
                .getSelectedItem();

        tempInpatient.setPatientID(selectedPatient.getPatientID());

        boolean okClicked = inpatientEditDialog.showInpatientEditDialog(tempInpatient);
        if (okClicked) {

            try {
                int createdInSQL = hospitalDAO.create(tempInpatient);
                if (createdInSQL != 0) {
                    setInpatientData(selectedPatient);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Create");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        }
    }

    /**
     * Called when the user clicks the Edit button. Opens a dialog to edit
     * details for a new inpatient.
     */
    @FXML
    private void handleEditInpatient() {

        EditDialog inpatientEditDialog = new EditDialog();

        Inpatient selectedInpatient = inpatientTable.getSelectionModel()
                .getSelectedItem();

        if (selectedInpatient != null) {
            boolean okClicked = inpatientEditDialog
                    .showInpatientEditDialog(selectedInpatient);
            if (okClicked) {

                try {
                    int editedInSQL = hospitalDAO.update(selectedInpatient);

                    if (editedInSQL != 0) {
                        setInpatientData(this.hospitalDAO.findByID(selectedInpatient.getPatientID()));
                    }
                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Update");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();

                    log.error(fatal, "SQL error: " + ex.getMessage());
                }

            }

        } else if (inpatientTable.getItems().size() > 0) {
            // when there is only one record in the table
            
            boolean okClicked = inpatientEditDialog
                    .showInpatientEditDialog(inpatientTable.getItems().get(0));
            if (okClicked) {

                try {
                    int editedInSQL = hospitalDAO.update(inpatientTable.getItems().get(0));

                    if (editedInSQL != 0) {
                        setInpatientData(patientTable.getSelectionModel().getSelectedItem());
                    }
                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Update");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();

                    log.error(fatal, "SQL error: " + ex.getMessage());
                }

            }

        } else {
            // Nothing selected.

            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("No Selection");
            dialog.setHeaderText("No inpatient record Selected to Edit");
            dialog.setContentText("Please select an inpatient record in the table.");
            dialog.show();
        }
    }

    /**
     * Called when the user clicks on the delete button in the inpatient tab.
     */
    @FXML
    private void handleDeleteInpatient() {

        int selectedIndex = inpatientTable.getSelectionModel().getSelectedIndex();
        Inpatient selectedInpatient = inpatientTable.getSelectionModel()
                .getSelectedItem();

        if (selectedIndex >= 0) {

            try {
                int deletedInSQL = hospitalDAO.deleteInpatient(selectedInpatient.getInpatientID());
                if (deletedInSQL != 0) {
                    inpatientTable.getItems().remove(selectedIndex);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Delete");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        } else if (inpatientTable.getItems().get(0) != null) {

            try {
                int deletedInSQL = hospitalDAO.deleteInpatient(inpatientTable.getItems().get(0).getInpatientID());
                if (deletedInSQL != 0) {
                    inpatientTable.getItems().remove(0);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Delete");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();
                //System.out.println("SQL error:" + ex.getMessage());
            }

        } else {
            // Nothing selected.
            // Modal dialog box
            // JavaFX dialog coming in 8u40
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("No Selection");
            dialog.setHeaderText("No Inpatient Selected to Delete");
            dialog.setContentText("Please select a inpatient in the table.");
            dialog.show();
        }
    }

    /**
     * Population the medication table with data
     *
     * @param patientData
     */
    public void setMedicationData(Patient patient) {
        if (patient != null) {
            try {

                medicationTable.setItems(this.hospitalDAO.findMedications(patient.getPatientID()));

            } catch (SQLException ex) {

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        }
    }

    /**
     * Called when the user clicks the New button. Opens a dialog to edit
     * details for a new medication.
     */
    @FXML
    private void handleNewMedication() {
        Medication tempMedication = new Medication();

        EditDialog medicationEditDialog = new EditDialog();
        Patient selectedPatient = patientTable.getSelectionModel()
                .getSelectedItem();

        tempMedication.setPatientID(selectedPatient.getPatientID());

        boolean okClicked = medicationEditDialog.showMedicationEditDialog(tempMedication);
        if (okClicked) {

            try {
                int createdInSQL = hospitalDAO.create(tempMedication);
                if (createdInSQL != 0) {

                    setMedicationData(selectedPatient);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Create");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        }
    }

    /**
     * Called when the user clicks the New button. Opens a dialog to edit
     * details for a new medication.
     */
    @FXML
    private void handleEditMedication() {

        EditDialog medicationEditDialog = new EditDialog();

        Medication selectedMedication = medicationTable.getSelectionModel()
                .getSelectedItem();

        //setInpatientData(patientTable.getSelectionModel().getSelectedItem());
        if (selectedMedication != null) {
            boolean okClicked = medicationEditDialog
                    .showMedicationEditDialog(selectedMedication);
            if (okClicked) {

                try {
                    int editedInSQL = hospitalDAO.update(selectedMedication);

                    if (editedInSQL != 0) {
                        setMedicationData(this.hospitalDAO.findByID(selectedMedication.getPatientID()));
                    }
                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Update");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();

                    log.error(fatal, "SQL error: " + ex.getMessage());
                }

            }

        } else if (medicationTable.getItems().size() > 0) {
            //when there is only one record in the medication table
            
            boolean okClicked = medicationEditDialog
                    .showMedicationEditDialog(medicationTable.getItems().get(0));
            if (okClicked) {

                try {
                    int editedInSQL = hospitalDAO.update(medicationTable.getItems().get(0));

                    if (editedInSQL != 0) {
                        setMedicationData(patientTable.getSelectionModel().getSelectedItem());
                    }
                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Update");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();

                    log.error(fatal, "SQL error: " + ex.getMessage());
                }

            }

        } else {
            // Nothing selected.
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("No Selection");
            dialog.setHeaderText("No medication record Selected to Edit");
            dialog.setContentText("Please select a medication record in the table.");
            dialog.show();
        }
    }

    /**
     * Called when the user clicks on the delete button in the medication tab.
     */
    @FXML
    private void handleDeleteMedication() {

        int selectedIndex = medicationTable.getSelectionModel().getSelectedIndex();
        Medication selectedMedication = medicationTable.getSelectionModel()
                .getSelectedItem();

        if (selectedIndex >= 0) {

            try {
                int deletedInSQL = hospitalDAO.deleteMedication(selectedMedication.getID());
                if (deletedInSQL != 0) {
                    medicationTable.getItems().remove(selectedIndex);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Delete");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        } else if (medicationTable.getItems().get(0) != null) {

            try {
                int deletedInSQL = hospitalDAO.deleteMedication(medicationTable.getItems().get(0).getID());
                if (deletedInSQL != 0) {
                    medicationTable.getItems().remove(0);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Delete");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();

            }

        } else {
            // Nothing selected.
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("No Selection");
            dialog.setHeaderText("No medication Selected to Delete");
            dialog.setContentText("Please select a medication in the table.");
            dialog.show();
        }
    }

    /**
     * Set the surgical data
     * @param patient 
     */
    private void setSurgicalData(Patient patient) {
        if (patient != null) {
            try {

                surgicalTable.setItems(this.hospitalDAO.findSurgicals(patient.getPatientID()));

            } catch (SQLException ex) {
                //System.out.println("SQL error: " + ex.getMessage());
                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        }
    }

    /**
     * Called when the user clicks the New button. Opens a dialog to edit
     * details for a new surgical.
     */
    @FXML
    private void handleNewSurgical() {
        Surgical tempSurgical = new Surgical();

        EditDialog surgicalEditDialog = new EditDialog();
        Patient selectedPatient = patientTable.getSelectionModel()
                .getSelectedItem();

        tempSurgical.setPatientID(selectedPatient.getPatientID());

        boolean okClicked = surgicalEditDialog.showSurgicalEditDialog(tempSurgical);
        if (okClicked) {

            try {
                int createdInSQL = hospitalDAO.create(tempSurgical);
                if (createdInSQL != 0) {
                    setSurgicalData(selectedPatient);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Create");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();

                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        }
    }

    /**
     * Called when the user clicks the Edit button. Opens a dialog to edit
     * details for a new surgical.
     */
    @FXML
    private void handleEditSurgical() {

        EditDialog surgicalEditDialog = new EditDialog();

        Surgical selectedSurgical = surgicalTable.getSelectionModel()
                .getSelectedItem();

        if (selectedSurgical != null) {
            boolean okClicked = surgicalEditDialog
                    .showSurgicalEditDialog(selectedSurgical);
            if (okClicked) {

                try {
                    int editedInSQL = hospitalDAO.update(selectedSurgical);

                    if (editedInSQL != 0) {
                        setSurgicalData(this.hospitalDAO.findByID(selectedSurgical.getPatientID()));
                    }
                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Update");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();

                    log.error(fatal, "SQL error: " + ex.getMessage());
                }

            }

        } else if (surgicalTable.getItems().size() > 0) {
            //when there is only one record in the surgical table
            
            boolean okClicked = surgicalEditDialog
                    .showSurgicalEditDialog(surgicalTable.getItems().get(0));
            if (okClicked) {

                try {
                    int editedInSQL = hospitalDAO.update(surgicalTable.getItems().get(0));

                    if (editedInSQL != 0) {
                        setSurgicalData(patientTable.getSelectionModel().getSelectedItem());
                    }
                } catch (SQLException ex) {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("SQL Error");
                    dialog.setHeaderText("Error in SQL Update");
                    dialog.setContentText("SQL error:" + ex.getMessage());
                    dialog.show();

                    log.error(fatal, "SQL error: " + ex.getMessage());
                }

            }

        } else {
            // Nothing selected.
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("No Selection");
            dialog.setHeaderText("No surgical record Selected to Edit");
            dialog.setContentText("Please select a surgical record in the table.");
            dialog.show();
        }
    }

    /**
     * Called when the user clicks on the Delete button in the surgical tab.
     */
    @FXML
    private void handleDeleteSurgical() {

        int selectedIndex = surgicalTable.getSelectionModel().getSelectedIndex();
        Surgical selectedSurgical = surgicalTable.getSelectionModel()
                .getSelectedItem();

        if (selectedIndex >= 0) {

            try {
                int deletedInSQL = hospitalDAO.deleteSurgical(selectedSurgical.getID());
                if (deletedInSQL != 0) {
                    surgicalTable.getItems().remove(selectedIndex);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Delete");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();
                //System.out.println("SQL error:" + ex.getMessage());
                log.error(fatal, "SQL error: " + ex.getMessage());
            }
        } else if (surgicalTable.getItems().get(0) != null) {

            try {
                int deletedInSQL = hospitalDAO.deleteSurgical(surgicalTable.getItems().get(0).getID());
                if (deletedInSQL != 0) {
                    surgicalTable.getItems().remove(0);
                }
            } catch (SQLException ex) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("SQL Error");
                dialog.setHeaderText("Error in SQL Delete");
                dialog.setContentText("SQL error:" + ex.getMessage());
                dialog.show();
            }

        } else {
            // Nothing selected.
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("No Selection");
            dialog.setHeaderText("No surgical Selected to Delete");
            dialog.setContentText("Please select a surgical record in the table.");
            dialog.show();
        }
    }

    /**
     * Sets the width of the columns based on a percentage of the overall width
     *
     * This needs to enhanced so that it uses the width of the anchor pane it is
     * in and then changes the width as the table grows.
     */
    private void adjustColumnWidths() {
        // Get the current width of the table
        double patientTableWidth = patientTable.getPrefWidth();
        // Set width of each column
        firstNameColumn.setPrefWidth(patientTableWidth * .5);
        lastNameColumn.setPrefWidth(patientTableWidth * .5);

        double inpatientTableWidth = inpatientTable.getPrefWidth();

        dateOfStayCol.setPrefWidth(inpatientTableWidth * .2);
        roomNbrCol.setPrefWidth(inpatientTableWidth * .2);
        dailyRateCol.setPrefWidth(inpatientTableWidth * .2);
        suppliesCol.setPrefWidth(inpatientTableWidth * .2);
        servicesCol.setPrefWidth(inpatientTableWidth * .2);

        double surgicalTableWidth = surgicalTable.getPrefWidth();

        dateOfSurgeryCol.setPrefWidth(surgicalTableWidth * .2);
        surgeryCol.setPrefWidth(surgicalTableWidth * .2);
        roomFeeCol.setPrefWidth(surgicalTableWidth * .2);
        surgeonFeeCol.setPrefWidth(surgicalTableWidth * .2);
        surgerySuppliesCol.setPrefWidth(surgicalTableWidth * .2);

        double medicationTableWidth = medicationTable.getPrefWidth();

        dateOfMedCol.setPrefWidth(medicationTableWidth * .25);
        medCol.setPrefWidth(medicationTableWidth * .25);
        unitsCol.setPrefWidth(medicationTableWidth * .25);
        unitCostCol.setPrefWidth(medicationTableWidth * .25);

    }

}
