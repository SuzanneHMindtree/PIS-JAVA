package com.junfenglu.view;

import com.junfenglu.hospitalbeans.Surgical;
import com.junfenglu.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a surgical.
 *
 * @author Junfeng Lu
 */
public class SurgicalEditDialogController {

    @FXML
    private TextField dateOfSurgeryField;
    @FXML
    private TextField surgeryField;
    @FXML
    private TextField roomFeeField;
    @FXML
    private TextField surgeonFeeField;
    @FXML
    private TextField suppliesField;

    private Stage dialogStage;
    private Surgical surgical;
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
     * @param surgical
     */
    public void setSurgical(Surgical surgical) {
        this.surgical = surgical;

        this.patientID = surgical.getPatientID();

        dateOfSurgeryField.setText(DateUtil.format(surgical.getDateOfSurgery().toLocalDateTime().toLocalDate()));
        surgeryField.setText(surgical.getSurgery());
        roomFeeField.setText(surgical.getRoomFee().toString());
        surgeonFeeField.setText(surgical.getSurgeonFee().toString());
        suppliesField.setText(surgical.getSupplies().toString());

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

            surgical.setPatientID(this.patientID);
            //System.out.println("patientID is: "+this.patientID);

            LocalDate surgeryLocalDate = DateUtil.parse(dateOfSurgeryField.getText());
            surgical.setDateOfSurgery(DateUtil.toTimestamp(surgeryLocalDate));

            surgical.setSurgery(surgeryField.getText());
            surgical.setRoomFee(new BigDecimal(roomFeeField.getText()));
            surgical.setSurgeonFee(new BigDecimal(surgeonFeeField.getText()));
            surgical.setSupplies(new BigDecimal(suppliesField.getText()));

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

        if (surgeryField.getText() == null || surgeryField.getText().length() == 0) {
            errorMessage += "No valid surgery!\n";
        }

        if (roomFeeField.getText() == null || roomFeeField.getText().length() == 0) {
            errorMessage += "No valid room!\n";
        }

        if (Double.parseDouble(roomFeeField.getText()) < 0) {
            errorMessage += "Room fee must be greater or equal to 0!\n";
        }

        if (surgeonFeeField.getText() == null || surgeonFeeField.getText().length() == 0) {
            errorMessage += "No valid surgeon fee!\n";
        }

        if (Double.parseDouble(surgeonFeeField.getText()) < 0) {
            errorMessage += "Surgeon fee must be greater or equal to 0!\n";
        }

        if (suppliesField.getText() == null || suppliesField.getText().length() == 0) {
            errorMessage += "No valid supplies fee!\n";
        }

        if (Double.parseDouble(suppliesField.getText()) < 0) {
            errorMessage += "Supplies must be greater or equal to 0!\n";
        }

        if (dateOfSurgeryField.getText() == null || dateOfSurgeryField.getText().length() == 0) {
            errorMessage += "No valid date of surgery!\n";
        } else {
            if (!DateUtil.validDate(dateOfSurgeryField.getText())) {
                errorMessage += "No valid date of surgery. Use the format dd.mm.yyyy!\n";
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
