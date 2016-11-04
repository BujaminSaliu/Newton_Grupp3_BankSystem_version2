/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private AnchorPane anchorp;
    
    
    @FXML
    private void change(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("borderPanetest.fxml"));
        anchorp.getChildren().setAll(pane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

