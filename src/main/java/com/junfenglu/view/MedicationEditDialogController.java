package com.junfenglu.view;

import com.junfenglu.hospitalbeans.Medication;


import com.junfenglu.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a medication.
 *
 * @author Junfeng Lu
 */
public class MedicationEditDialogController {

    @FXML
    private TextField dateOfMedField;
    @FXML
    private TextField medField;
    @FXML
    private TextField unitsField;
    @FXML
    private TextField unitCostField;

    private Stage dialogStage;
    private Medication medication;
    private int patientID;
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
     * Sets the inpatient to be edited in the dialog.
     *
     * @param medication
     */
    public void setMedication(Medication medication) {
        this.medication = medication;

        this.patientID = medication.getPatientID();
        
        
        dateOfMedField.setText(DateUtil.format(medication.getDateOfMed().toLocalDateTime().toLocalDate()));
        medField.setText(medication.getMed());
        unitsField.setText(medication.getUnits().toString());
        unitCostField.setText(medication.getUnitCost().toString());

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

            medication.setPatientID(this.patientID);

            LocalDate medLocalDate = DateUtil.parse(dateOfMedField.getText());
            medication.setDateOfMed(DateUtil.toTimestamp(medLocalDate));

            medication.setMed(medField.getText());
            medication.setUnits(new BigDecimal(unitsField.getText()));
            medication.setUnitCost(new BigDecimal(unitCostField.getText()));

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

        if (medField.getText() == null || medField.getText().length() == 0) {
            errorMessage += "No valid medication!\n";
        }

        if (unitsField.getText() == null || unitsField.getText().length() == 0) {
            errorMessage += "No valid units!\n";
        }

        if (Double.parseDouble(unitsField.getText()) < 0) {
            errorMessage += "Units must be greater or equal to 0!\n";
        }

        if (unitCostField.getText() == null || unitCostField.getText().length() == 0) {
            errorMessage += "No valid unit cost!\n";
        }

        if (Double.parseDouble(unitCostField.getText()) < 0) {
            errorMessage += "Unit cost must be greater or equal to 0!\n";
        }

        if (dateOfMedField.getText() == null || dateOfMedField.getText().length() == 0) {
            errorMessage += "No valid date of medication!\n";
        } else {
            if (!DateUtil.validDate(dateOfMedField.getText())) {
                errorMessage += "No valid date of medication. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            // Modal dialog box
            // JavaFX dialog coming in 8u40
            Alert dialog = new Alert(AlertType.ERROR);
            dialog.setTitle("Invalid Fields");
            dialog.setHeaderText("Please correct invalid fields");
            dialog.setContentText(errorMessage);
            dialog.show();
            return false;
        }
    }
}
