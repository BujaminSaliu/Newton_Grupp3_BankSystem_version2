/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class FXMLDocumentController implements Initializable
{
    
    @FXML
    private Label label;
    
    @FXML
    private TextFlow textflowTest;
    
    @FXML
    private TextField textFieldName;
    
    @FXML
    private TextField textFieldPnr;
    
    
    public static BankLogic bankLogic;
    
    @FXML
    private void addCustomerButton(ActionEvent event)
    {
//        BankLogic bl = new BankLogic
        bankLogic.addCustomer(textFieldName.getText(),Long.parseLong(textFieldPnr.getText()));
        for (int i = 0; i < bankLogic.getCustomers().size(); i++)
        {
            System.out.println(bankLogic.getCustomers().get(i).toString());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        bankLogic = BankLogic.getInstance();
    }    
    
}
