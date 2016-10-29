/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

//import static gui_design_1.BankLogic.allCustomersArrayList;
import java.net.URL;

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

    private String selectedCustomerString;
    private String selectedAccountString;

    private BankLogic bankLogic = BankLogic.getInstance(); // singleton 

    public ObservableList<String> obListAllCustumers = FXCollections.observableArrayList(); // alla kunder som ska visas i custumersListView

    public ObservableList<String> obListFoundCustumers = FXCollections.observableArrayList(); // kunder som hittas och visas i custumersListView

    public ObservableList<String> obListCreateAccount = FXCollections.observableArrayList(); // kunder som skapas och visas i accountsListView

    public ObservableList<Account> obListCreateAccountList = FXCollections.observableArrayList();
    public ObservableList<String> obListtransaktion = FXCollections.observableArrayList();

    @FXML
    private void addCustomerButton(ActionEvent event) throws Exception
    {

        if (nameTextField.getText().isEmpty() || pNrTextField.getText().isEmpty()) // om användare inte fyllt i båda fälten, komplettera med instanceOf?
        {
            returnMessageToOperator.setText("Du måste fyll i båda fälten!");
            //throw new Exception("Du måste fyll i båda fälten!");
        } //        try 
        //        {
        //            Double userInput = Double.parseDouble(pNrTextField.getText());
        //            
        //        } 
        //        catch (NumberFormatException ignore) 
        //        {
        //            System.out.println("Invalid input");
        //        }
        //} 

        try
        {
        boolean add = bankLogic.addCustomer(nameTextField.getText(), Long.parseLong(pNrTextField.getText()));
        if (add)
        {
            returnMessageToOperator.setText("Kund lades till!");
        } else
        {
            returnMessageToOperator.setText("Kund lades inte  till!");
        }
        } catch (NumberFormatException nfe)
                    {
                        returnMessageToOperator.setText("Enter a valid Personal number");
                    }
        // obListAllCustumers.add(0, bankLogic.allCustomersArrayList.get(0).toStringForcustumersListView());
        upDateobListAllCustumers();
        custumersListView.setItems(obListAllCustumers);
        
        nameTextField.clear();
        pNrTextField.clear();

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
    private void printAllCustomersButton(ActionEvent event) throws Exception
    {
        bankLogic.getCustomers();

    }

    @FXML
    private void seeAllCustomersButton(ActionEvent event) throws Exception
    {
        obListAllCustumers.clear();
        bankLogic.getCustomers();
        obListAllCustumers.add(bankLogic.getCustomers().toString());
        custumersListView.setItems(obListAllCustumers);

    }
    @FXML
    private void withDrawButton(ActionEvent event) throws Exception
    {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        if(selectedAccountString== null)
        {
            returnMessageToOperator.setText("Select the specific accountID");
        }
        else
        {
        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
        {
            Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// To get a personal number
            for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
            {

                if (selectedAccountString.equals(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString()))
                {

                    System.out.println("test Account number " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID());
                    int accountID = bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID   
                     
                    try
                    {
                        //The deposited amount should be above 0
                        double amount = Double.parseDouble(depositWithDrawAmountField.getText());
                        if(amount <0)
                        {
                            throw new NumberFormatException();
                        }
                        
                        //If withdraw is succeded, the transactionListView would be updated
                        if(bankLogic.withdraw(personalNumber, accountID, amount)== true)
                        {
                            transactionsListView.getItems().clear();
                            /*This code is to write the first line on the transactionListView, it will be reseted in every
                            withdraw that is because of this code "transactionsListView.getItems().clear();"
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                            */
                            obListtransaktion= FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID) +
                                    "          Saldo          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).
                                            getBalance() +"kr          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().
                                                    get(j).getAccountType() + "("+bankLogic.allCustomersArrayList.get(i).
                                                            getCustumerAccountsList().get(j).getInterestRate()+"%)") ;
                            obListtransaktion.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).custumerAccountsTransaktionsList.toString());
                            transactionsListView.setItems(obListtransaktion);

                        }
                        
                        else if (bankLogic.withdraw(personalNumber, accountID, amount)==false);
                        {
                            returnMessageToOperator.setText("Maximum limit is 5000");
                        }
                    } catch (NumberFormatException nfe)
                    {
                        returnMessageToOperator.setText("Enter a valid amount");
                    }

                    //To reset the ListView window, it will be removed and the updated values will appear
                    obListCreateAccount.remove(0, bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size());

                }

                //Shows the updated values
                obListCreateAccount.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());
                accountsListView.setItems(obListCreateAccount);
                
                //To show all the customers in the customer ListView
                obListAllCustumers.setAll(bankLogic.getCustomers());
                custumersListView.setItems(obListAllCustumers);
            }

        }
        }
        //Magnus kod
//        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
//        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
//         for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
//        {
//            if (selectedCustomerString.equals(bankLogic.allCustomersArrayList
//                    .get(i).toStringForcustumersListView()))
//            {
//                for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).custumerAccountsList.size(); j++)
//                {
//                    if (selectedAccountString.equals(bankLogic.allCustomersArrayList
//                            .get(i).getCustumerAccountsList().get(j).toString()))
//                    {
//                        bankLogic.allCustomersArrayList
//                                .get(i).getCustumerAccountsList()
//                                .get(j).withdraw((Double) custumersListView.getSelectionModel().getSelectedItem());
//                    }
//                }
//            }
//        }

    }

    @FXML
    private void closeAccountButton(ActionEvent event) throws Exception
    {
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        if(selectedAccountString== null)
        {
            returnMessageToOperator.setText("Select the specific accountID to close the account");
        }
        else
        {
        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
        {
            System.out.println(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size());
            for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
            {
                if (selectedAccountString.equals(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString()))
                {
                    Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// To get a personal number
                    int accountID = bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID   
                    bankLogic.closeAccount(personalNumber, accountID);
                    System.out.println(j);
                    obListCreateAccount.remove(j);
                }
            }
        }
        }
    }

    @FXML
    private void depositButton(ActionEvent event) throws Exception
    {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        if(selectedAccountString== null)
        {
            returnMessageToOperator.setText("Select the specific accountID to deposit");
        }
        else
        {
        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
        {
            Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// To get a personal number
            for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
            {

                if (selectedAccountString.equals(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString()))
                {

                    System.out.println("test Account number " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID());
                    int accountID = bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID   

                    try
                    {
                    double amount = Double.parseDouble(depositWithDrawAmountField.getText());
                    if(amount < 0)
                    {
                        throw new NumberFormatException();
                    }
                    else
                    {
                        if(bankLogic.deposit(personalNumber, accountID, amount)== true)
                        {
                            transactionsListView.getItems().clear();
                            
                            /*This code is to write the first line on the transactionListView, it will be reseted in every
                            deposit because of this code transactionsListView.getItems().clear();
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                            */
                            obListtransaktion= FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID) + "          Saldo          "
                            + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance() +"kr          "
                            + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountType() +
                                    "("+bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getInterestRate()+"%)") ;
                            
                            obListtransaktion.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).custumerAccountsTransaktionsList.toString());
                            transactionsListView.setItems(obListtransaktion);

                        }
                    }
                    } catch (NumberFormatException nfe)
                    {
                        returnMessageToOperator.setText("Enter a valid amount");
                    }

                    //To reset the ListView window after every every deposit action, it will be removed and the updated values will appear
                    obListCreateAccount.remove(0, bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size());

                }

                //To update the transaction after the deposit action
                obListCreateAccount.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());
                accountsListView.setItems(obListCreateAccount);
                
                obListAllCustumers.setAll(bankLogic.getCustomers());
                custumersListView.setItems(obListAllCustumers);
            }
        }
        
        }

    }

    @FXML
    private void createNewCreditAccountButton(ActionEvent event) throws Exception
    {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        String getAccountInformation;
        if (selectedCustomerString == null && pNrTextField.getText().isEmpty())
        {
            returnMessageToOperator.setText("Either write the personal number or select the customer from the lists");
        } else if (!pNrTextField.getText().isEmpty())
        {
            Long personalNumber = Long.valueOf(pNrTextField.getText());
            getAccountInformation = bankLogic.getAccount(personalNumber, bankLogic.addCreditAccount(Long.valueOf(pNrTextField.getText())));
            obListCreateAccount.add(getAccountInformation);
            accountsListView.setItems(obListCreateAccount);
        } else
        {
            for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
            {
                if (selectedCustomerString.equals(bankLogic.allCustomersArrayList.get(i).toString2()))
                {
                    System.out.println(bankLogic.allCustomersArrayList.get(i).getPersonalNumber()); // Test to get a personal number
                    Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// Test to get a personal number
                    int accountID = bankLogic.addCreditAccount(personalNumber);//To get accountID                   
                    getAccountInformation = bankLogic.getAccount(personalNumber, accountID);

                    obListCreateAccount.add(getAccountInformation);
                    accountsListView.setItems(obListCreateAccount);
                } 
                
            }

        }
    }

    @FXML
    private void createNewSavingsAccountButton(ActionEvent event) throws Exception
    {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        String getAccountInformation;
        if (selectedCustomerString == null && pNrTextField.getText().isEmpty())
        {
            returnMessageToOperator.setText("Either write the personal number or select the customer from the lists");
        } else if (!pNrTextField.getText().isEmpty())
        {
            getAccountInformation = bankLogic.getAccount(Long.valueOf(pNrTextField.getText()), bankLogic.addSavingsAccount(Long.valueOf(pNrTextField.getText())));
            obListCreateAccount.add(getAccountInformation);
            accountsListView.setItems(obListCreateAccount);
        } else
        {
            for (int i = 0; i < bankLogic.allCustomersArrayList.size() ; i++)
            {                    

                if (selectedCustomerString.equals(bankLogic.allCustomersArrayList.get(i).toString2()))
                {
                    
                    System.out.println(bankLogic.allCustomersArrayList.get(i).getPersonalNumber()); // Test to get a personal number
                    Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// Test to get a personal number
                    int accountID = bankLogic.addSavingsAccount(personalNumber);//To get accountID                   
                    getAccountInformation = bankLogic.getAccount(personalNumber, accountID);

                    obListCreateAccount.add(getAccountInformation);
                    accountsListView.setItems(obListCreateAccount);
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
