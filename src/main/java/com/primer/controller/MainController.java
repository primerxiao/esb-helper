package com.primer.controller;

import com.primer.MainApplication;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class MainController implements Initializable {

    @FXML
    public AnchorPane ctt;
    @FXML
    public TextArea console;
    @FXML
    public Button generateRequestJson;
    @FXML
    public Button loadEsbExcel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loadEsbExcel(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xlsx", "*.xlsx"));
        File file = fileChooser.showOpenDialog(MainApplication.getStage());
        if (file == null) {
            return;
        }
        if (!file.isFile()) {
            return;
        }
        //获取索引这个sheet


    }
}