package com.junfenglu.view;

import com.junfenglu.app.MainApp;
import com.junfenglu.hospitalbeans.Inpatient;
import com.junfenglu.hospitalbeans.Medication;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.junfenglu.hospitalbeans.Patient;
import com.junfenglu.hospitalbeans.Surgical;

/**
 * The controller class for editor dialogs
 *
 * @author Junfeng Lu
 */
public class EditDialog {

    /**
     * Patient editor dialog
     *
     * @param patient
     * @return a boolean value (TRUE if OK button is clicked)
     */
    public boolean showPatientEditDialog(Patient patient) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("/fxml/PatientEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Patient");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the patient into the controller.
            PatientEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPatient(patient);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inpatient editor dialog
     * @param inpatient
     * @return TRUE if OK button is clicked, FALSE otherwise
     */
    public boolean showInpatientEditDialog(Inpatient inpatient) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("/fxml/InpatientEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Inpatient");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the INpatient into the controller.
            InpatientEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInpatient(inpatient);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    
    /**
     * Medication edit dialog
     * @param medication
     * @return TRUE if OK button is clicked, FALSE otherwise
     */
    public boolean showMedicationEditDialog(Medication medication) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("/fxml/MedicationEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Medication");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the medication into the controller.
            MedicationEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMedication(medication);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    
    /**
     * Surgical edit dialog
     * @param surgical
     * @return TRUE if OK button is clicked, FALSE if otherwise
     */
    public boolean showSurgicalEditDialog(Surgical surgical) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("/fxml/SurgicalEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Surgical");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the medication into the controller.
            SurgicalEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSurgical(surgical);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
