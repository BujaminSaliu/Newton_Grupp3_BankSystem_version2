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
        }

        try
        {
            boolean add = bankLogic.addCustomer(nameTextField.getText(), Long.parseLong(pNrTextField.getText()));

            if (add)
            {
                obListAllCustumers.clear();
                obListAllCustumers.addAll(bankLogic.getCustomers());
                custumersListView.setItems(obListAllCustumers);
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
        //upDateobListAllCustumers();

        nameTextField.clear();
        pNrTextField.clear();

    }

    @FXML
    private void findCustumerButton(ActionEvent event) throws Exception
    {
//        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
//        {
//            if (bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView().contains(nameTextField.getText())
//                    || bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView().contains(pNrTextField.getText()))
//
//            {
//
//                custumersListView.getItems().clear();
//                
//                obListFoundCustumers.add(bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView());
//                custumersListView.setItems(obListFoundCustumers);
//                 
//                 
//            }
//        }
        Long personalNumber;
        try
        {
            personalNumber = Long.valueOf(pNrTextField.getText());// To get a personal number  
            for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
            {

                obListFoundCustumers.clear();
                custumersListView.getItems().clear();

                /*
                "bankLogic.getCustomer(personalNumber).toString()" will return a List and "[]" should
                have to be removed in order to add, this will help us in createNewSavingsAccountButton and 
                createNewCreaditAccountButton 
                 */
//                obListFoundCustumers.add(bankLogic.getCustomer(personalNumber).toString()
//                        .replace(",", "")//remove the commas
//                        .replace("[", "")//remove the right bracket
//                        .replace("]", "")//remove the left bracket
//                        .trim());//remove trailing spaces from partially initialized arrays
                obListFoundCustumers.addAll(bankLogic.getCustomer(personalNumber));
                custumersListView.setItems(obListFoundCustumers);
                System.out.println("hii " + bankLogic.getCustomer(personalNumber));
                
                //A message will be displayed if there is no customer having the given personal 
                //number the return arrayList size would be 0,"[]"
                if(bankLogic.getCustomer(personalNumber).isEmpty())
                {
                    returnMessageToOperator.setText("No customer having this personal number");
                }
                break;

            }
        } catch (NumberFormatException nfe)
        {
            returnMessageToOperator.setText("Enter a valid Personal number");
        }

    }

    @FXML
    private void changeCustumerNameButton(ActionEvent event) throws Exception
    {
        String name = nameTextField.getText();
        Long personalNumber;
        try
        {
            if(nameTextField.getText().isEmpty())
            {
                returnMessageToOperator.setText("Enter your name");
            }

            else
            {
            personalNumber = Long.valueOf(pNrTextField.getText());// To get a personal number
            for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
            {

                if (bankLogic.allCustomersArrayList.get(i).getPersonalNumber() == personalNumber)
                {
                    if (bankLogic.changeCustomerName(name, personalNumber))
                    {
                        obListAllCustumers.clear();
                        obListAllCustumers.addAll(bankLogic.getCustomer(personalNumber));
                        custumersListView.setItems(obListAllCustumers);

                    }
                }
            }
            }
        } catch (NumberFormatException nfe)
        {
            returnMessageToOperator.setText("Enter a name and valid Personal number");
        }

    }

    @FXML
    private void removeCustomersButton(ActionEvent event) throws Exception
    {
        String removeCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        nameTextField.clear();
        pNrTextField.clear();

            if(!pNrTextField.getText().isEmpty())
            {
                returnMessageToOperator.setText("Select the customer or search the specific customer");

            }

            else if(removeCustomerString==null)
            {
                nameTextField.clear();
                pNrTextField.clear();
                returnMessageToOperator.setText("Select the customer or search the specific customer");
            }
       
            else
            {
                
            for (int j = 0; j < bankLogic.allCustomersArrayList.size(); j++)
            {
                nameTextField.clear();
                pNrTextField.clear();
                System.out.println("removeCustomerString " + removeCustomerString);
                System.out.println("string2 " + bankLogic.allCustomersArrayList.get(j).toString2());
            if(removeCustomerString.equals(bankLogic.allCustomersArrayList.get(j).toString2()))
            {
                returnMessageToOperator.setText(bankLogic.allCustomersArrayList.get(j).getCustomerName() + " is removed");
            obListAllCustumers.addAll(bankLogic.removeCustomer(bankLogic.allCustomersArrayList.get(j).getPersonalNumber()));
            custumersListView.setItems(obListAllCustumers);
            break;
            }
                
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
        obListAllCustumers.addAll(bankLogic.getCustomers());
        custumersListView.setItems(obListAllCustumers);

    }

    @FXML
    private void seeAccountTransactionButton(ActionEvent event) throws Exception
    {
        obListtransaktion.clear();
        String selectedAccountStringDetail = (String) accountsListView.getSelectionModel().getSelectedItem();
        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
        {
            if (selectedAccountStringDetail == null)
            {
                returnMessageToOperator.setText("Select the specific AccountID to see the transaction");
            } else
            {
                Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// To get a personal number
                for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {

                    System.out.println("Test select " + selectedAccountStringDetail);
                    System.out.println("Test toString" + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());
                    if (selectedAccountStringDetail.equals(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString()))
                    {
                        int accountID = bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID();

                        /*This code is to write the first line on the transactionListView,
                        this code "transactionsListView.getItems().clear();" will clean the transaction window
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                         */
                        transactionsListView.getItems().clear();

                        //To give the Account number, balance, account type and the rate. It will be displayed as a title in the transaction
                        //window
                        obListtransaktion = FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID)
                                + "          Saldo          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).
                                getBalance() + "kr          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().
                                get(j).getAccountType() + "(" + bankLogic.allCustomersArrayList.get(i).
                                getCustumerAccountsList().get(j).getInterestRate()+ "%)");

                        //All the transactions (diposit and withdraw) will be displayed
                        obListtransaktion.addAll(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getCustumerAccountsTransaktionsList().toString());
                        transactionsListView.setItems(obListtransaktion);
                    }

                }
            }
        }
    }

    @FXML
    private void seeCustomerAccountButton(ActionEvent event) throws Exception
    {

//        accountsListView.getItems().clear();
//        transactionsListView.getItems().clear();
        //selectedCustomerString shows null,we declared another selectedCustomerString2
        String selectedCustomerString2 = (String) custumersListView.getSelectionModel().getSelectedItem();

        if (selectedCustomerString2 != null)
        {
            for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
            {
                System.out.println("selectedCustomerString2 " + selectedCustomerString2);
                System.out.println("compare " + bankLogic.allCustomersArrayList.get(i).toString2());

                if (selectedCustomerString2.equals(bankLogic.allCustomersArrayList.get(i).toString2()))
                {
                    Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// Test to get a personal number
                    for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                    {
                        int accountID = bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID                   

                        //clears the obListCreateAccount observableList and fills with all accounts of the specific customer 
                        obListCreateAccount.setAll(bankLogic.getAllAccount(personalNumber, accountID));
                        accountsListView.setItems(obListCreateAccount);

                    }
                    break; //It breaks the loop when the specifc customer's info matches with selectedCustomerString2
                }

            }
        } else
        {
            returnMessageToOperator.setText("Select the customer");
        }

    }

    @FXML
    private void withDrawButton(ActionEvent event) throws Exception
    {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        if (selectedAccountString == null)
        {
            returnMessageToOperator.setText("Select the specific accountID");
        } else
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
                            if (amount < 0)
                            {
                                throw new NumberFormatException();
                            }

                            //If withdraw is succeded, the transactionListView would be updated
                            else if (bankLogic.withdraw(personalNumber, accountID, amount) == true)
                            {
                                transactionsListView.getItems().clear();
                                /*This code is to write the first line on the transactionListView, it will be reseted in every
                            withdraw that is because of this code "transactionsListView.getItems().clear();"
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                                 */
                                obListtransaktion = FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID)
                                        + "          Saldo          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).
                                        getBalance() + "kr          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().
                                        get(j).getAccountType() + "(" + bankLogic.allCustomersArrayList.get(i).
                                        getCustumerAccountsList().get(j).getInterestRate() + "%)");
                                obListtransaktion.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getCustumerAccountsTransaktionsList().toString());
                                transactionsListView.setItems(obListtransaktion);

                                //To clear the ListView window,
                                accountsListView.getItems().clear();
                                //Shows the updated values accountsListView for the specific accountID
                                obListCreateAccount.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());
                                accountsListView.setItems(obListCreateAccount);

                                //To show all the customers in the customer ListView
                                obListAllCustumers.setAll(bankLogic.getCustomers());
                                custumersListView.setItems(obListAllCustumers);

                            } 
                            else if (bankLogic.withdraw(personalNumber, accountID, amount) == false);
                            {
                                returnMessageToOperator.setText("Maximum limit is 5000");
                            }
                        } catch (NumberFormatException nfe)
                        {
                            returnMessageToOperator.setText("Enter a valid amount");
                        }

                    }

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
        if (selectedAccountString == null)
        {
            returnMessageToOperator.setText("Select the specific accountID to close the account");
        } else
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

                        //To show the deleted Account on the transaction window, the result would be printed
                        //as a text form too, the printer code is written in BankLogic.closeAccount(personalNumber, accountID)
                        transactionsListView.getItems().clear();
                        obListtransaktion.addAll(bankLogic.closeAccount(personalNumber, accountID));
                        transactionsListView.setItems(obListtransaktion);
                        System.out.println("The value of i" + i + " j " + j);
                        obListCreateAccount.clear();
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
        if (selectedAccountString == null)
        {
            returnMessageToOperator.setText("Select the specific accountID to deposit");
        } else
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
                            if (amount < 0)
                            {
                                throw new NumberFormatException();
                            } else if (bankLogic.deposit(personalNumber, accountID, amount) == true)
                            {
                                transactionsListView.getItems().clear();

                                /*This code is to write the first line on the transactionListView, it will be reseted in every
                            deposit because of this code transactionsListView.getItems().clear();
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                                 */
                                obListtransaktion = FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID) + "          Saldo          "
                                        + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance() + "kr          "
                                        + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountType()
                                        + "(" + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getInterestRate() + "%)");

                                //To get the updated new transactions in transactionsListView after every deposit made                                 
                                obListtransaktion.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).
                                        getCustumerAccountsTransaktionsList().toString());
                                transactionsListView.setItems(obListtransaktion);

                                //To clear the accountsListView window after every every deposit action
                                accountsListView.getItems().clear();

                                //To update the accountsListView after the deposit action
                                obListCreateAccount.add(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());
                                accountsListView.setItems(obListCreateAccount);

                                obListAllCustumers.setAll(bankLogic.getCustomers());

                            }
                        } catch (NumberFormatException nfe)
                        {
                            returnMessageToOperator.setText("Enter a valid amount");
                        }

                    }

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
        Long personalNumber;
        try
        {

            if (selectedCustomerString == null && pNrTextField.getText().isEmpty())
            {
                returnMessageToOperator.setText("Select the customer from the lists");
            } else if (!pNrTextField.getText().isEmpty())
            {
                personalNumber = Long.valueOf(pNrTextField.getText());
                getAccountInformation = bankLogic.getAccount(personalNumber, bankLogic.addCreditAccount(Long.valueOf(pNrTextField.getText())));
                nameTextField.clear();
                pNrTextField.clear();
                obListCreateAccount.add(getAccountInformation);
                accountsListView.setItems(obListCreateAccount);
            } else
            {
                for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
                {
                    nameTextField.clear();
                    pNrTextField.clear();
                    if (selectedCustomerString.equals(bankLogic.allCustomersArrayList.get(i).toString2()))
                    {
                        System.out.println(bankLogic.allCustomersArrayList.get(i).getPersonalNumber()); // Test to get a personal number
                        personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// Test to get a personal number
                        int accountID = bankLogic.addCreditAccount(personalNumber);//To get accountID                   
                        getAccountInformation = bankLogic.getAccount(personalNumber, accountID);

                        obListCreateAccount.add(getAccountInformation);
                        accountsListView.setItems(obListCreateAccount);
                    }

                }

            }
        } catch (NumberFormatException nfe)
        {
            returnMessageToOperator.setText("Enter a valid Personal number");
        }
    }

    @FXML
    private void createNewSavingsAccountButton(ActionEvent event) throws Exception
    {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        String getAccountInformation;
        Long personalNumber;
        try
        {
            if (selectedCustomerString == null && pNrTextField.getText().isEmpty())
            {
                returnMessageToOperator.setText("Either write the personal number or select the customer from the lists");
            } else if (!pNrTextField.getText().isEmpty())
            {
                getAccountInformation = bankLogic.getAccount(Long.valueOf(pNrTextField.getText()), bankLogic.addSavingsAccount(Long.valueOf(pNrTextField.getText())));
                obListCreateAccount.add(getAccountInformation);
                accountsListView.setItems(obListCreateAccount);
                nameTextField.clear();
                pNrTextField.clear();
            } else
            {
                for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
                {

                    nameTextField.clear();
                    pNrTextField.clear();
                    if (selectedCustomerString.equals(bankLogic.allCustomersArrayList.get(i).toString2()))
                    {

                        System.out.println(bankLogic.allCustomersArrayList.get(i).getPersonalNumber()); // Test to get a personal number
                        personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// Test to get a personal number
                        int accountID = bankLogic.addSavingsAccount(personalNumber);//To get accountID                   
                        getAccountInformation = bankLogic.getAccount(personalNumber, accountID);

                        obListCreateAccount.add(getAccountInformation);
                        accountsListView.setItems(obListCreateAccount);
                    }

                }

            }
        } catch (NumberFormatException nfe)
        {
            returnMessageToOperator.setText("Enter a valid Personal number");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
//        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
//        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
//        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
//        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
//        obListAllCustumers.add("Sofia-Marie af Hegelknekt 01234567890123456789");
//
//        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
//        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
//        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
//        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
//        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
//        obListAllCustumers.add("2015-01-27\t\t\t11:28:55\t\t\tIn: 300.00 kr\t\t\tSaldo: 300.00 kr");
//
//        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
//        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
//        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
//        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
//        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
//        obListAllCustumers.add("Kontonummer: 1005\t\t\tSaldo: 600.00 kr\t\t\tSparkont (2%)");
//
//        for (int i = 1; i < 100; i++)
//        {
//            obListAllCustumers.add(i + "");
//        }
//
//        custumersListView.setItems(obListAllCustumers);
//        accountsListView.setItems(obListAllCustumers);
//        transactionsListView.setItems(obListAllCustumers);

    }

//    private void upDateobListAllCustumers()
//    {
//        obListAllCustumers.clear();
//        obListAllCustumers.addAll(bankLogic.getCustomers());
////        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
////        {
////            obListAllCustumers.add(bankLogic.allCustomersArrayList.get(i).toStringForcustumersListView());
////        }
//    }
}
