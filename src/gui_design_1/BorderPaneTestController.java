/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

//import static gui_design_1.BankLogic.allCustomersArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
 * @author SYSJM2 GRUPP 3
 */
public class BorderPaneTestController implements Initializable
{
    @FXML
    private Label nameDisplayLabel;
    @FXML
    private Label pNrDisplayLabel;
    @FXML
    private TextField nameChange;
    @FXML
    private TextField searchTextField;
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

    
    public ObservableList<String> obListtransaktion = FXCollections.observableArrayList();

    

    @FXML
    public final void getOnMouseClickedCustListView()
    {
        transactionsListView.getItems().clear();
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        returnMessageToOperator.setText(" ");
        for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
        {
            if (bankLogic.getAllCustomersArrayList().get(i).toString2().equals(selectedCustomerString))
            {
                //returnMessageToOperator.setText(bankLogic.allCustomersArrayList.get(i).getCustomerName());
                nameDisplayLabel.setText(bankLogic.getAllCustomersArrayList().get(i).getCustomerName());
                pNrDisplayLabel.setText(Long.toString(bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber()));
                obListCreateAccount.clear();

                for (int j = 0; j < bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().size(); j++)
                {
                    obListCreateAccount.add(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).toString());
                }

            }
        }
        accountsListView.setItems(obListCreateAccount);

    }

    @FXML
    public final void getOnMouseClickedAccoutListView() {
        obListtransaktion.clear();
        returnMessageToOperator.setText("");
        
        String selectedAccountStringDetail = (String) accountsListView.getSelectionModel().getSelectedItem();
        for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
        {
            if (selectedAccountStringDetail == null)
            {
                returnMessageToOperator.setText("Välj specifikt konto för att se transaktioner");
            } else
            {
                Long personalNumber = bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber();// To get a personal number
                for (int j = 0; j < bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().size(); j++)
                {

                    
                    if (selectedAccountStringDetail.equals(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).toString()))
                    {
                        int accountID = bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getAccountID();

                        /*This code is to write the first line on the transactionListView,
                        this code "transactionsListView.getItems().clear();" will clean the transaction window
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                         */
                        transactionsListView.getItems().clear();

                        //To give the Account number, balance, account type and the rate. It will be displayed as a title in the transaction
                        //window
                        obListtransaktion = FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID)
                                + "          Saldo          " + bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).
                                getBalance() + "kr          " + bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().
                                get(j).getAccountType() + "(" + bankLogic.getAllCustomersArrayList().get(i).
                                getCustumerAccountsList().get(j).getInterestRate() + "%)");

                        //All the transactions (diposit and withdraw) will be displayed
                        obListtransaktion.addAll(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getCustumerAccountsTransaktionsList().toString()
                                        .replace(",", "")//remove the commas
                                        .replace("[", "")//remove the right bracket
                                        .replace("]", "")//remove the left bracket
                                        .replace(",", ""));//remove the comma   
                        transactionsListView.setItems(obListtransaktion);
                    }

                }
            }
        }

    }

    @FXML
    private void addCustomerButton(ActionEvent event) throws Exception {

        if (nameTextField.getText().isEmpty() || pNrTextField.getText().isEmpty()) // om användare inte fyllt i båda fälten, komplettera med instanceOf?
        {
            returnMessageToOperator.setText("Du måste fylla i båda fälten!");
        } else if (pNrTextField.getText().length() > 12 || pNrTextField.getText().length() < 12)
        {
            returnMessageToOperator.setText("Du måste fylla i 12 siffror!");
        }else if (nameTextField.getText().matches(".*[0-9*@!#¤%&/()=?`+].*")) {
            returnMessageToOperator.setText("Namn får endast bestå av bokstäver!");
        }else
        {
            try
            {
                boolean add = bankLogic.addCustomer(nameTextField.getText(), Long.parseLong(pNrTextField.getText()));

                if (add) {
                    obListAllCustumers.clear();
                    obListAllCustumers.addAll(bankLogic.getCustomers());
                    custumersListView.setItems(obListAllCustumers);
                    returnMessageToOperator.setText("Kund lades till!");
                } else
                {
                    returnMessageToOperator.setText("Kund lades inte till!");
                }
            } catch (NumberFormatException nfe)
            {
                returnMessageToOperator.setText("Ange ett giltigt Personnummer");
            }
            // obListAllCustumers.add(0, bankLogic.allCustomersArrayList.get(0).toStringForcustumersListView());
            //upDateobListAllCustumers();

            nameTextField.clear();
            pNrTextField.clear();

        }
    }

  @FXML
    private void findCustumerButton(ActionEvent event) throws Exception
    {
        if(searchTextField.getText().isEmpty())
        {
            obListAllCustumers.clear();
            obListAllCustumers.addAll(bankLogic.getCustomers());
            custumersListView.setItems(obListAllCustumers);
            returnMessageToOperator.setText("Visar alla kunder");
        }
        else
        {
            obListFoundCustumers.clear();
            custumersListView.getItems().clear();
          
        for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
        {
            if (bankLogic.getAllCustomersArrayList().get(i).toString2().toLowerCase().contains(searchTextField.getText().toLowerCase()))

            {

                //custumersListView.getItems().clear();
                
                obListFoundCustumers.add(bankLogic.getAllCustomersArrayList().get(i).toString2());
                custumersListView.setItems(obListFoundCustumers);
                 
                 
            }
        }
        }
    }

    @FXML
    private void changeCustumerNameButton(ActionEvent event) throws Exception
    {
        String name = nameChange.getText();
        Long personalNumber;
        try
        {
            if (nameChange.getText().isEmpty())
            {
                returnMessageToOperator.setText("Välj kund och ange nytt namn");
            } else
            {
                personalNumber = Long.valueOf(pNrDisplayLabel.getText());// To get a personal number
                for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
                {
                    
                    if (bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber() == personalNumber)
                    {
                        if (bankLogic.changeCustomerName(name, personalNumber))
                        {
                            obListAllCustumers.clear();
                            obListAllCustumers.addAll(bankLogic.getCustomer(personalNumber));
                            custumersListView.setItems(obListAllCustumers);
                            returnMessageToOperator.setText("Kundens namn ändrades till: "+ name);
                            nameChange.clear();
                            nameDisplayLabel.setText(name);
                            break;
                        }

                    } else if (personalNumber != bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber())
                    {
                        returnMessageToOperator.setText("Kunde inte ändra namn. Ingen kund hittad");
                        
                    }
                        
                }
            }
        } catch (NumberFormatException nfe) {
            returnMessageToOperator.setText("Skriv namn och personnummer");
        }
        
    }

    @FXML
    private void removeCustomersButton(ActionEvent event) throws Exception {
        String removeCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        nameTextField.clear();
        pNrTextField.clear();

        if (!pNrTextField.getText().isEmpty())
        {
            returnMessageToOperator.setText("Välj kund att ta bort");

        } else if (removeCustomerString == null) {
            nameTextField.clear();
            pNrTextField.clear();
            returnMessageToOperator.setText("Välj kund att ta bort");
        } else
        {

            for (int j = 0; j < bankLogic.getAllCustomersArrayList().size(); j++)
            {
                nameTextField.clear();
                pNrTextField.clear();
                
                if (removeCustomerString.equals(bankLogic.getAllCustomersArrayList().get(j).toString2()))
                {
                    returnMessageToOperator.setText(bankLogic.getAllCustomersArrayList().get(j).getCustomerName() + " borttagen");

                    //To clear the observable list in the Account ListView
                    obListCreateAccount.clear();  //To make obListCreateAccount empty

                    obListCreateAccount.add("Följande konto avslutas");
                    for (int i = 0; i < bankLogic.getAllCustomersArrayList().get(j).getCustumerAccountsList().size(); i++)
                    {
                        //Adding all the customers' removed account int account ListView
                        //obListCreateAccount.addAll(bankLogic.allCustomersArrayList.get(j).getCustumerAccountsList().toString());
                        Long personalNumber = bankLogic.getAllCustomersArrayList().get(j).getPersonalNumber();// To get a personal number
                        int accountID = bankLogic.getAllCustomersArrayList().get(j).getCustumerAccountsList().get(i).getAccountID();//To get accountID   

                        obListCreateAccount.addAll(bankLogic.getAllCustomersArrayList().get(j).getCustumerAccountsList().get(i).toStringClose());
                        accountsListView.setItems(obListCreateAccount);
                    }

                    obListAllCustumers.addAll(bankLogic.removeCustomer(bankLogic.getAllCustomersArrayList().get(j).getPersonalNumber()));
                    custumersListView.setItems(obListAllCustumers);

                    obListtransaktion.clear();//To make obListtransaktion empty
                    break;
                }

            }

        }

        // 
        obListAllCustumers.clear();
        obListAllCustumers.addAll(bankLogic.getCustomers());
        custumersListView.setItems(obListAllCustumers);

    }

    //To print all customer lists to a text file
    @FXML
    private void printAllCustomersButton(ActionEvent event) throws Exception {
        List<String> stringListCustomer = bankLogic.getCustomers();
        try {
            FileWriter out = new FileWriter("Kundlista.txt");
            BufferedWriter bw = new BufferedWriter(out);
            PrintWriter pw = new PrintWriter(bw);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();

            pw.println("Listan skapad:" + "\t" + dateFormat.format(date));
            for (String list : stringListCustomer) {

                bw.write(list);
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            returnMessageToOperator.setText("Filen korrupt eller skrivskyddad! ");

        }

    }

    @FXML
    private void seeAllCustomersButton(ActionEvent event) throws Exception {
        obListAllCustumers.clear();
        obListAllCustumers.addAll(bankLogic.getCustomers());
        custumersListView.setItems(obListAllCustumers);

    }

//    @FXML
//    private void seeAccountTransactionButton(ActionEvent event) throws Exception
//    {
//        obListtransaktion.clear();
//        String selectedAccountStringDetail = (String) accountsListView.getSelectionModel().getSelectedItem();
//        for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
//        {
//            if (selectedAccountStringDetail == null)
//            {
//                returnMessageToOperator.setText("Select the specific AccountID to see the transaction");
//            } else
//            {
//                Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// To get a personal number
//                for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
//                {
//
//                    System.out.println("Test select " + selectedAccountStringDetail);
//                    System.out.println("Test toString" + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());
//                    if (selectedAccountStringDetail.equals(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString()))
//                    {
//                        int accountID = bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID();
//
//                        /*This code is to write the first line on the transactionListView,
//                        this code "transactionsListView.getItems().clear();" will clean the transaction window
//                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
//                         */
//                        transactionsListView.getItems().clear();
//
//                        //To give the Account number, balance, account type and the rate. It will be displayed as a title in the transaction
//                        //window
//                        obListtransaktion = FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID)
//                                + "          Saldo          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j)
//                                .getBalance() + "kr          " + bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().
//                                get(j).getAccountType() + "(" + bankLogic.allCustomersArrayList.get(i).
//                                getCustumerAccountsList().get(j).getInterestRate() + "%)");
//
//                        //All the transactions (diposit and withdraw) will be displayed
//                        obListtransaktion.addAll(bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getCustumerAccountsTransaktionsList().toString());
//                        transactionsListView.setItems(obListtransaktion);
//                    }
//
//                }
//            }
//        }
//    }

//    @FXML
//    private void seeCustomerAccountButton(ActionEvent event) throws Exception
//    {
//
////        accountsListView.getItems().clear();
////        transactionsListView.getItems().clear();
//        //selectedCustomerString shows null,we declared another selectedCustomerString2
//        String selectedCustomerString2 = (String) custumersListView.getSelectionModel().getSelectedItem();
//
//        if (selectedCustomerString2 != null)
//        {
//            for (int i = 0; i < bankLogic.allCustomersArrayList.size(); i++)
//            {
//                System.out.println("selectedCustomerString2 " + selectedCustomerString2);
//                System.out.println("compare " + bankLogic.allCustomersArrayList.get(i).toString2());
//
//                if (selectedCustomerString2.equals(bankLogic.allCustomersArrayList.get(i).toString2()))
//                {
//                    Long personalNumber = bankLogic.allCustomersArrayList.get(i).getPersonalNumber();// Test to get a personal number
//                    for (int j = 0; j < bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
//                    {
//                        int accountID = bankLogic.allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID                   
//
//                        //clears the obListCreateAccount observableList and fills with all accounts of the specific customer 
//                        obListCreateAccount.setAll(bankLogic.getAllAccount(personalNumber, accountID));
//                        accountsListView.setItems(obListCreateAccount);
//
//                    }
//                    break; //It breaks the loop when the specifc customer's info matches with selectedCustomerString2
//                }
//
//            }
//        } else
//        {
//            returnMessageToOperator.setText("Select the customer");
//        }
//
//    }

    @FXML
    private void withDrawButton(ActionEvent event) throws Exception {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        if (selectedAccountString == null)
        {
            returnMessageToOperator.setText("Välj eller skapa konto för transaktion");
        } else
        {
            for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
            {
                Long personalNumber = bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber();// To get a personal number
                for (int j = 0; j < bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().size(); j++)
                {

                    if (selectedAccountString.equals(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).toString()))
                    {

                        try
                        {
                        
                        int accountID = bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID   

                            //The deposited amount should be above 0
                            double amount = Double.parseDouble(depositWithDrawAmountField.getText());
                            if (amount <= 0) {
                                throw new NumberFormatException();
                            } //If withdraw is succeded, the transactionListView would be updated
                           else if ((bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getBalance() - amount) < -5000 &&
                                bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getAccountType().equals("Credit Account")){
                                throw new ArrayIndexOutOfBoundsException();
                            } //If withdraw is succeded, the transactionListView would be updated
                            else if (bankLogic.withdraw(personalNumber, accountID, amount) == true) {
                                //To clear the ListView window,
                                accountsListView.getItems().clear();
                                //Shows the updated values accountsListView for the specific accountID
                                obListCreateAccount.add(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).toString());
                                accountsListView.setItems(obListCreateAccount);

                                /*This code is to write the first line on the transactionListView, it will be reseted in every
                            withdraw that is because of this code "transactionsListView.getItems().clear();"
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                                 */
                                //transactionsListView.getItems().clear();                              
                                obListtransaktion = FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID)
                                        + "          Saldo          " + bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).
                                        getBalance() + "kr          " + bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().
                                        get(j).getAccountType() + "(" + bankLogic.getAllCustomersArrayList().get(i).
                                        getCustumerAccountsList().get(j).getInterestRate() + "%)");

                                obListtransaktion.add(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getCustumerAccountsTransaktionsList().toString()
                                        .replace("[", "")//remove the right bracket
                                        .replace("]", "")//remove the left bracket
                                        .replace(",", ""));//remove the comma   
                                transactionsListView.setItems(obListtransaktion);

                                //To show all the customers in the customer ListView
                                obListAllCustumers.setAll(bankLogic.getCustomers());
                                custumersListView.setItems(obListAllCustumers);

                            } else 
                            {
                                returnMessageToOperator.setText("Medges ej");
                            }
                        } catch (NumberFormatException nfe) {
                            returnMessageToOperator.setText("Ange giltigt belopp");
                        }
                        catch (ArrayIndexOutOfBoundsException nfe) {
                            returnMessageToOperator.setText("Medges ej. Kreditgräns övertrasserad");
                        }

                    }

                }

            }
            depositWithDrawAmountField.clear();
        }


    }

    @FXML
    private void closeAccountButton(ActionEvent event) throws Exception {
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        if (selectedAccountString == null)
        {
            returnMessageToOperator.setText("Välj specifikt konto som ska avslutas");
        } else
        {
            for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
            {
                
                for (int j = 0; j < bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().size(); j++)
                {
                    if (selectedAccountString.equals(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).toString()))
                    {
                        Long personalNumber = bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber();// To get a personal number
                        int accountID = bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID   

                        //To show the deleted Account on the transaction window, the result would be printed
                        //as a text form too, the printer code is written in BankLogic.closeAccount(personalNumber, accountID)
                        obListCreateAccount.clear();
                        transactionsListView.getItems().clear();
                        obListCreateAccount.add("Följande konto avslutas");
                        obListCreateAccount.addAll(bankLogic.closeAccount(personalNumber, accountID));
                        accountsListView.setItems(obListCreateAccount);
                        //obListCreateAccount.clear();
                    }
                }
            }
        }
    }

    @FXML
    private void depositButton(ActionEvent event) throws Exception {
        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        if (selectedAccountString == null)
        {
            returnMessageToOperator.setText("Välj eller skapa konto för transaktion");
        } else
        {
            for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
            {
                Long personalNumber = bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber();// To get a personal number
                for (int j = 0; j < bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().size(); j++)
                {

                    if (selectedAccountString.equals(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).toString()))
                    {

                        int accountID = bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getAccountID();//To get accountID   

                        try {
                            double amount = Double.parseDouble(depositWithDrawAmountField.getText());
                            if (amount <= 0) {
                                throw new NumberFormatException();

                            } else if (amount > 1000000) {
                                throw new IndexOutOfBoundsException();
                            } else if (bankLogic.deposit(personalNumber, accountID, amount) == true) {
                                transactionsListView.getItems().clear();

                                /*This code is to write the first line on the transactionListView, it will be reseted in every
                            deposit because of this code transactionsListView.getItems().clear();
                            The output is like Kontonummer: 1000 Saldo: 600 kr Saving Accout
                                 */
                                obListtransaktion = FXCollections.observableArrayList("Kontonummer:          " + Integer.toString(accountID) + "          Saldo          "
                                        + bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getBalance() + "kr          "
                                        + bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getAccountType()
                                        + "(" + bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).getInterestRate() + "%)");

                                //To get the updated new transactions in transactionsListView after every deposit made                                 
                                obListtransaktion.add(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).
                                        getCustumerAccountsTransaktionsList().toString()
                                        
                                        .replace("[", "")//remove the right bracket
                                        .replace("]", "")//remove the left bracket
                                        .replace(",", ""));//remove the comma       

                                transactionsListView.setItems(obListtransaktion);

                                //To clear the accountsListView window after every every deposit action
                                accountsListView.getItems().clear();

                                //To update the accountsListView after the deposit action
                                obListCreateAccount.add(bankLogic.getAllCustomersArrayList().get(i).getCustumerAccountsList().get(j).toString());
                                accountsListView.setItems(obListCreateAccount);

                                obListAllCustumers.setAll(bankLogic.getCustomers());

                            }
                        } catch (NumberFormatException nfe) {
                            returnMessageToOperator.setText("Ange giltigt belopp");
                        } catch (IndexOutOfBoundsException e) {
                            returnMessageToOperator.setText("Kontakta bankledningen!");
                        }
                    }

                }
            }

        }

        depositWithDrawAmountField.clear();

    }

    @FXML
    private void createNewCreditAccountButton(ActionEvent event) throws Exception {
//        if(obListCreateAccount.get(0).equals("Följande konto avslutas"))
//        {
//            accountsListView.getItems().clear();
//            
//        }
//        else
//        {

        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        String getAccountInformation;
        Long personalNumber;
        try {

            if (selectedCustomerString == null && pNrTextField.getText().isEmpty())
            {
                returnMessageToOperator.setText("Välj kund från kundlista");
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
                for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
                {
                    nameTextField.clear();
                    pNrTextField.clear();
                    if (selectedCustomerString.equals(bankLogic.getAllCustomersArrayList().get(i).toString2()))
                    {
                        System.out.println(bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber()); // Test to get a personal number
                        personalNumber = bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber();// Test to get a personal number
                        int accountID = bankLogic.addCreditAccount(personalNumber);//To get accountID                   
                        getAccountInformation = bankLogic.getAccount(personalNumber, accountID);

                        obListCreateAccount.add(getAccountInformation);
                        accountsListView.setItems(obListCreateAccount);
                    }

                }

            }
        } catch (NumberFormatException nfe)
        {
            returnMessageToOperator.setText("Ange giltigt personnummer");
        }
        //}
    }

    @FXML
    private void createNewSavingsAccountButton(ActionEvent event) throws Exception {

        selectedCustomerString = (String) custumersListView.getSelectionModel().getSelectedItem();
        selectedAccountString = (String) accountsListView.getSelectionModel().getSelectedItem();
        String getAccountInformation;
        Long personalNumber;
        try
        {
            if (selectedCustomerString == null && pNrTextField.getText().isEmpty())
            {
                returnMessageToOperator.setText("Välj kund från kundlista");
            } else if (!pNrTextField.getText().isEmpty())
            {
                getAccountInformation = bankLogic.getAccount(Long.valueOf(pNrTextField.getText()), bankLogic.addSavingsAccount(Long.valueOf(pNrTextField.getText())));
                obListCreateAccount.add(getAccountInformation);
                accountsListView.setItems(obListCreateAccount);
                nameTextField.clear();
                pNrTextField.clear();
            } else
            {
                for (int i = 0; i < bankLogic.getAllCustomersArrayList().size(); i++)
                {

                    nameTextField.clear();
                    pNrTextField.clear();
                    if (selectedCustomerString.equals(bankLogic.getAllCustomersArrayList().get(i).toString2()))
                    {

                        personalNumber = bankLogic.getAllCustomersArrayList().get(i).getPersonalNumber();// Test to get a personal number
                        int accountID = bankLogic.addSavingsAccount(personalNumber);//To get accountID                   
                        getAccountInformation = bankLogic.getAccount(personalNumber, accountID);

                        obListCreateAccount.add(getAccountInformation);
                        accountsListView.setItems(obListCreateAccount);

                    }

                }

            }
        } catch (NumberFormatException nfe)
        {
            returnMessageToOperator.setText("Ange giltigt personnummer");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        bankLogic.addCustomer("Magnus Norlin", 198205060000L);
        bankLogic.addCreditAccount(198205060000L);
        bankLogic.addSavingsAccount(198205060000L);
	
        bankLogic.addCustomer("Pontus Nygren", 198305060001L);
        bankLogic.addCreditAccount(198305060001L);
        bankLogic.addSavingsAccount(198305060001L);
        
        bankLogic.addCustomer("Befkadu Assefa", 198405060002L);
        bankLogic.addCreditAccount(198405060002L);
        bankLogic.addSavingsAccount(198405060002L);
        
        bankLogic.addCustomer("Richard Gustafsson", 198405060003L);
        bankLogic.addCreditAccount(198405060003L);
        bankLogic.addSavingsAccount(198405060003L);
        
        bankLogic.addCustomer("Bujamin Saliu", 198405060004L);
        bankLogic.addCreditAccount(198405060004L);
        bankLogic.addSavingsAccount(198405060004L);
        
        bankLogic.addCustomer("David Jonsson", 198405060006L);
        bankLogic.addCreditAccount(198405060006L);
        bankLogic.addSavingsAccount(198405060006L);
        
        bankLogic.addCustomer("Tanya Hultgren", 198405060005L);
        bankLogic.addCreditAccount(198405060005L);
        bankLogic.addSavingsAccount(198405060005L);
        
        
        
        


        obListAllCustumers.clear();
        obListAllCustumers.addAll(bankLogic.getCustomers());
        custumersListView.setItems(obListAllCustumers);        
        
        accountsListView.setItems(obListCreateAccount);
    }


}
