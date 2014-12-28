package com.junfenglu.view;

import com.junfenglu.hospitalbeans.Inpatient;

import com.junfenglu.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of an impatient
 *
 * @author Junfeng Lu
 */
public class InpatientEditDialogController {

    @FXML
    private TextField dateOfStayField;
    @FXML
    private TextField roomNbrField;
    @FXML
    private TextField dailyRateField;
    @FXML
    private TextField suppliesField;
    @FXML
    private TextField servicesField;

    private Stage dialogStage;
    private Inpatient inpatient;
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
     * @param inpatient
     */
    public void setInpatient(Inpatient inpatient) {
        this.inpatient = inpatient;

        this.patientID = inpatient.getPatientID();

        dateOfStayField.setText(DateUtil.format(inpatient.getDateOfStay().toLocalDateTime().toLocalDate()));
        roomNbrField.setText(inpatient.getRoomNumber());
        dailyRateField.setText(inpatient.getDailyRate().toString());
        suppliesField.setText(inpatient.getSupplies().toString());
        servicesField.setText(inpatient.getServices().toString());
        dateOfStayField.setPromptText("dd.mm.yyyy");
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

            inpatient.setPatientID(this.patientID);
            System.out.println("patientID is: "+this.patientID);

            LocalDate stayLocalDate = DateUtil.parse(dateOfStayField.getText());
            inpatient.setDateOfStay(DateUtil.toTimestamp(stayLocalDate));

            inpatient.setRoomNumber(roomNbrField.getText());
            inpatient.setDailyRate(new BigDecimal(dailyRateField.getText()));
            inpatient.setSupplies(new BigDecimal(suppliesField.getText()));
            inpatient.setServices(new BigDecimal(servicesField.getText()));

            // System.out.println("services is: "+servicesField.getText());
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

        if (roomNbrField.getText() == null || roomNbrField.getText().length() == 0) {
            errorMessage += "No valid room number!\n";
        }

        if (dailyRateField.getText() == null || dailyRateField.getText().length() == 0) {
            errorMessage += "No valid daily rate!\n";
        }

        if (Double.parseDouble(dailyRateField.getText()) < 0) {
            errorMessage += "Daily rate must be greater or equal to 0!\n";
        }

        if (suppliesField.getText() == null || suppliesField.getText().length() == 0) {
            errorMessage += "No valid supplies!\n";
        }

        if (Double.parseDouble(suppliesField.getText()) < 0) {
            errorMessage += "Supplies must be greater or equal to 0!\n";
        }

        if (servicesField.getText() == null || servicesField.getText().length() == 0) {
            errorMessage += "No valid services!\n";
        }
        
        if (Double.parseDouble(servicesField.getText()) < 0) {
            errorMessage += "Services must be greater or equal to 0!\n";
        }

        if (dateOfStayField.getText() == null || dateOfStayField.getText().length() == 0) {
            errorMessage += "No valid date of stay!\n";
        } else {
            if (!DateUtil.validDate(dateOfStayField.getText())) {
                errorMessage += "No valid date of stay. Use the format dd.mm.yyyy!\n";
            }
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
