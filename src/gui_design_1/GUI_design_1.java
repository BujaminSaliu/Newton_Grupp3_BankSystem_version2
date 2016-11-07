/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class GUI_design_1 extends Application
{
    
    
    @Override
    public void start(Stage stage) throws Exception
    {
         
        
            
        //Parent root = FXMLLoader.load(getClass().getResource("borderPanetest.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("NewtonBank");
        stage.show();   
        stage.setResizable(false);  
    
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
        
    }
    
}
