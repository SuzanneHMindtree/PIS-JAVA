package com.junfenglu.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.time.LocalDate;

import com.junfenglu.hospitalbeans.Patient;
import com.junfenglu.util.DateUtil;

/**
 * Dialog to edit details of a patient.
 *
 * @author Junfeng Lu
 */
public class PatientEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField diagnosisField;
    @FXML
    private TextField admissionDateField;
    @FXML
    private TextField releaseDateField;

    private Stage dialogStage;
    private Patient patient;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;

        firstNameField.setText(patient.getFirstName());
        lastNameField.setText(patient.getLastName());
        diagnosisField.setText(patient.getDiagnosis());
        admissionDateField.setText(DateUtil.format(patient.getAdmissionDate().toLocalDateTime().toLocalDate()));
        releaseDateField.setText(DateUtil.format(patient.getReleaseDate().toLocalDateTime().toLocalDate()));
        admissionDateField.setPromptText("dd.mm.yyyy");
        releaseDateField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOK() {
        if (isInputValid()) {
            patient.setFirstName(firstNameField.getText());
            patient.setLastName(lastNameField.getText());
            patient.setDiagnosis(diagnosisField.getText());

            LocalDate admissionLocalDate = DateUtil.parse(admissionDateField.getText());
            //Date admissionDate=Date.from(admissionLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            // patient.setAdmissionDate(new Timestamp(admissionDate.getTime()));
            patient.setAdmissionDate(DateUtil.toTimestamp(admissionLocalDate));

            LocalDate releaseLocalDate = DateUtil.parse(releaseDateField.getText());
            //Date releaseDate=Date.from(releaseLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            //patient.setReleaseDate(new Timestamp(releaseDate.getTime()));
            patient.setReleaseDate(DateUtil.toTimestamp(releaseLocalDate));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (diagnosisField.getText() == null || diagnosisField.getText().length() == 0) {
            errorMessage += "No valid diagnosis!\n";
        }

        if (admissionDateField.getText() == null || admissionDateField.getText().length() == 0) {
            errorMessage += "No valid admission date!\n";
        } else {
            if (!DateUtil.validDate(admissionDateField.getText())) {
                errorMessage += "No valid admission date. Use the format dd.mm.yyyy!\n";
            }
        }

        if (releaseDateField.getText() == null || releaseDateField.getText().length() == 0) {
            errorMessage += "No valid release date!\n";
        } else {
            if (!DateUtil.validDate(releaseDateField.getText())) {
                errorMessage += "No valid release date. Use the format dd.mm.yyyy!\n";
            }
        }

        if (DateUtil.parse(admissionDateField.getText()).compareTo(DateUtil.parse(releaseDateField.getText())) <= 0) {
            return true;
        } else {
            errorMessage += "Release date earlier than admission date! ";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert dialog = new Alert(AlertType.ERROR);
            dialog.setTitle("Invalid Fields");
            dialog.setHeaderText("Please correct invalid fields");
            dialog.setContentText(errorMessage);
            dialog.show();
            return false;
        }
    }
}
