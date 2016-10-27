/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
//import static gui_design_1.BankLogic.allCustomersArrayList;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private Label returnMessageToOperator; // varningar och felmedelanden    
    @FXML
    private ListView custumersListView; // den första, översta listan i GUI
    @FXML
    private ListView accountsListView; 
    @FXML
    private ListView transactionsListView;
    @FXML
    private TextField nameTextField; // name-input från användare
    @FXML
    private TextField pNrTextField; // pnr-input från användare
    @FXML
    private TextField depositWithDrawAmountField; // name-input från användare
    
    private BankLogic bankLogic = BankLogic.getInstance(); // singleton 
    
    public ObservableList<String> obListAllCustumers = FXCollections.observableArrayList(); // alla kunder som ska visas i custumersListView
    
    public ObservableList<String> obListFoundCustumers = FXCollections.observableArrayList(); // kunder som hittas och visas i custumersListView
    
    @FXML
    private void addCustomerButton(ActionEvent event) throws Exception
    {
        if (nameTextField.getText().isEmpty() || pNrTextField.getText().isEmpty()) // om användare inte fyllt i båda fälten, komplettera med instanceOf?
        {
            returnMessageToOperator.setText("Du måste fyll i båda fälten!");
            throw new Exception("Du måste fyll i båda fälten!");
        }
        
//        try 
//        {
//            Double userInput = Double.parseDouble(pNrTextField.getText());
//            
//        } 
//        catch (NumberFormatException ignore) 
//        {
//            System.out.println("Invalid input");
//        }
//} 
        else
        {
            boolean add = bankLogic.addCustomer(nameTextField.getText(), Long.parseLong(pNrTextField.getText()));
            // obListAllCustumers.add(0, bankLogic.allCustomersArrayList.get(0).toStringForcustumersListView());
            upDateobListAllCustumers();
            custumersListView.setItems(obListAllCustumers);
            if (add)
            {
                returnMessageToOperator.setText("Kund lades till!");
            }
            else
            {
                returnMessageToOperator.setText("Kund lades inte  till!");
            }
            nameTextField.clear();
            pNrTextField.clear();
        }
    }
    
    @FXML
    private void findCustumerButton(ActionEvent event) throws Exception
    {
        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
        {
            if (bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView().contains(nameTextField.getText()) 
                    || bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView().contains(pNrTextField.getText()))
            {
                
                custumersListView.getItems().clear();
                obListFoundCustumers.add(bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView());
                custumersListView.setItems(obListFoundCustumers);
            }
        }
    }
    
    @FXML
    private void withDrawButton(ActionEvent event) throws Exception
    {
        String selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        String selectedAccountString =  (String) accountsListView.getSelectionModel().getSelectedItem();
        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
        {
            if (selectedCustomerString.equals(bankLogic.allCustomersArrayList
                    .get(i).toStringForcustumersListView()))
                for (int j = 0; j < bankLogic.allCustomersArrayList.get(i)
                        .custumerAccountsList.size(); j++)
                {
                    if (selectedAccountString.equals( bankLogic.allCustomersArrayList
                            .get(i).getCustumerAccountsList().get(j).toString()))
                    {
                        bankLogic.allCustomersArrayList
                            .get(i).getCustumerAccountsList()
                                .get(j).withdraw((Double) custumersListView
                                        .getSelectionModel().getSelectedItem());
                    }
                }
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
        
        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
        
        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
        
        for (int i = 1; i < 100; i++)
        {
            obListAllCustumers.add(i + "");
        }
        
        custumersListView.setItems(obListAllCustumers);
        accountsListView.setItems(obListAllCustumers);
        transactionsListView.setItems(obListAllCustumers);
        
    }   
    
    private void upDateobListAllCustumers()
    {
        obListAllCustumers.clear();
        obListAllCustumers.setAll(bankLogic.getCustomers());
//        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
//        {
//            obListAllCustumers.add(bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView());
//        }
    }
    
}
