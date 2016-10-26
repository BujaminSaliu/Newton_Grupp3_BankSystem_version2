/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Allan
 */
public class BorderPaneTestController implements Initializable
{

    @FXML
    private ListView custumersListView;
    @FXML
    private ListView accountsListView;
    @FXML
    private ListView transactionsListView;
    @FXML
    private TextField nameTextField;
    
    
    public  ObservableList<String> obListaTest = FXCollections.observableArrayList();
    
    @FXML
    private void addCustomerButton(ActionEvent event)
    {
        obListaTest.add(0, nameTextField.getText());
        custumersListView.setItems(obListaTest);        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        
        
        obListaTest.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListaTest.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListaTest.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListaTest.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListaTest.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListaTest.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        
        obListaTest.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListaTest.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListaTest.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListaTest.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListaTest.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListaTest.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        
        obListaTest.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListaTest.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListaTest.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListaTest.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListaTest.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListaTest.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        
        for (int i = 1; i < 100; i++)
        {
            obListaTest.add(i + "");
        }
        
        custumersListView.setItems(obListaTest);
        accountsListView.setItems(obListaTest);
        transactionsListView.setItems(obListaTest);
        
    }    
    
}
