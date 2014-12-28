package com.junfenglu.app;

import com.junfenglu.hospitalbeans.Patient;
import com.junfenglu.persistence.HospitalDAOImpl;
import com.junfenglu.properties.connectionbean.ConnectionConfigBean;
import com.junfenglu.properties.manager.PropertiesManager;
import com.junfenglu.view.HospitalOverviewController;
import com.junfenglu.view.HospitalOverviewController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Patient Information System Application
 *
 * @author Junfeng Lu This application will allow user to interact with a data
 * base of patient information (create records, retrieve records, update
 * records, and delete records).
 *
 * The database is comprised of four tables: patient, inpatient, surgical, medication
 */
public class MainApp extends Application {

    //Get the logger
    private final Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());
    private final Marker fatal = MarkerFactory.getMarker("FATAL");

    //Declare the variables for stage and root layout
    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Constructor
     */
    public MainApp() {
        super();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Patient Information System");

        //initialize the root layout
        initRootLayout();

        // show all the tables and add data from database
        showHospitalOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("/fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            log.info("Scene set up!");
        } catch (IOException e) {
            log.error(fatal, "Input/output error: " + e.getMessage());
        }
    }

    /**
     * Shows the hospital overview inside the root layout.
     */
    public void showHospitalOverview() {
        try {
            // Load the FXML controller for all the tables
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("/fxml/HospitalOverview.fxml"));
            AnchorPane hospitalOverview = (AnchorPane) loader.load();

            // Set hospital overview into the center of root layout.
            rootLayout.setCenter(hospitalOverview);

            // Give the controller the data persistence object.
            PropertiesManager pm = new PropertiesManager();
            ConnectionConfigBean connectionConfig = pm.loadTextProperties("", "TextProps");
            HospitalOverviewController controller = loader.getController();
            // set the data from patient table first
            controller.setPatientData(new HospitalDAOImpl(connectionConfig));

        } catch (IOException e) {
            // e.printStackTrace();
            log.error(fatal, "Input/output error: " + e.getMessage());
        }
    }

    /**
     * Returns the main stage.
     *
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * This is where it begins
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);

    }

}
