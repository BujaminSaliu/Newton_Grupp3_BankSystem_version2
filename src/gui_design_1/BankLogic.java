/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.Repository;

/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class BankLogic {

    private static Repository bankLogicRepository = new Repository();
    private static List<Customer> allCustomersArrayList;
    private static BankLogic instance; //Step 2 declare the instance variabel
    static List<String> removedCustomerList = new ArrayList<>();

    private BankLogic() //Step 1 declare the constructor and change it to private
    {
        allCustomersArrayList = new ArrayList<>();
    }

    public static BankLogic getInstance() //Step 3 write getInstance method
    {
        if (instance == null) {
            instance = new BankLogic();
        }
        return instance;
    }

    public static List<Customer> getAllCustomersArrayList() {
        // return allCustomersArrayList;
        return bankLogicRepository.getAllCustomers();
    }

    public static ArrayList<Account> getAllAccountsArrayList(Long pNr) {
        return bankLogicRepository.getAllAccountArrayList(pNr);
    }

    public static List<Transaktions> getAllTransactionsArrayList(int accountID) {
        // return allCustomersArrayList;
        return bankLogicRepository.getAllTransactions(accountID);
    }

    /**
     * Returns all allCustomersArrayList of the bank(Personal number and name)
     *
     * @return
     */
    public List<String> getCustomers() {

        return bankLogicRepository.getCustomers();
    }

    /**
     * Adding the allCustomersArrayList, if not exist in the system. Returns
     * true if the allCustomersArrayList created
     *
     * @param name
     * @param pNr
     * @return check
     */
    public boolean addCustomer(String name, long pNr) {
        boolean check = true;
        if (!getAllCustomersArrayList().isEmpty()) {  /// Pontus la till
            for (int i = 0; i < getAllCustomersArrayList().size(); i++) {
                if (bankLogicRepository.getAllCustomers().get(i).getPersonalNumber() == pNr) {
                    check = false;
                    break;
                }
            }
        } 
        //if the allCustomersArrayList doesn't exist in the database, he/she will be added here
        if (check == true) {
            try {
                bankLogicRepository.addCustomer(name, pNr);

            } catch (Exception ex) {
                Logger.getLogger(BankLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    /**
     * To get the information about the specific allCustomersArrayList after
     * entering the personal number, returns name and pNr
     *
     * @param pNr
     * @return searchCustomer
     */
    public List<String> getCustomer(long pNr) {
        //Creating empty ArrayList to put all the customerAccountList
        List<String> searchCustomer = new ArrayList<>();
        for (int i = 0; i < allCustomersArrayList.size(); i++) {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr) {
                searchCustomer.add(allCustomersArrayList.get(i).toString2());
                break;
            }

        }
        return searchCustomer;
    }

    /**
     * Change name for the guy having the same personal number return true if
     * the name changes
     *
     * @param name
     * @param pNr
     * @return changeCustomerName
     */
    public boolean changeCustomerName(String name, long pNr) {
        boolean changeCustomerName = true;

        return bankLogicRepository.changeCustomerName(name, pNr);
    }

    public List<String> removeCustomer(long pNr) {
        ArrayList<String> removedCustomerList = new ArrayList<>();
        for (int i = 0; i < getAllCustomersArrayList().size(); i++) {
            if (getAllCustomersArrayList().get(i).getPersonalNumber() == pNr) {

                for (int k = 0; k < getAllAccountsArrayList(pNr).size(); k++) {
                    removedCustomerList.add(getAllAccountsArrayList(pNr).get(k).toStringClose());
                }
                if (bankLogicRepository.removeCustomer(pNr)) { // Kalla på repository metoden och kolla så den är true(tagit bort kund)

                    break;
                }
            }
        }
        return removedCustomerList;
    }

    /**
     * create an account for a allCustomersArrayList with personal number, that
     * returns or return -1 if not created
     *
     * @param pNr
     * @return
     */
    public int addSavingsAccount(long pNr) {

        return bankLogicRepository.addSavingsAccount(pNr);
    }

    public String getAccount(long pNr, int accountId) {
        String getAccountReturnString = null;

        return bankLogicRepository.getAccount(pNr);
    }

    public boolean deposit(int acoountID, double amount) //    public boolean deposit(long pNr, int accountId, double amount)
    {
        boolean depositMade = true;
        bankLogicRepository.deposit(acoountID, amount);

        return depositMade;
    }

    /**
     * To check if it is possible to make a withdraw, if successful the return
     * is true if not(for example if there is no enough money in the savings
     * account) the return is false
     *
     * @param pNr
     * @param accountId
     * @param withdrawDepositStatus
     * @param amount
     * @return withdrawMade
     */
    public boolean withdraw(int accountId, double amount) //public boolean withdraw(long pNr, int accountId, double amount)  //old
    {
        boolean withdrawMade = true;
        try {
            bankLogicRepository.withdraw(accountId, amount);
        } catch (SQLException ex) {
            Logger.getLogger(BankLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return withdrawMade;
    }

    public String closeAccount(long pNr, int accountId) throws SQLException {
        String closedAccount = null;
        for (int i = 0; i < getAllAccountsArrayList(pNr).size(); i++) {
            if (getAllAccountsArrayList(pNr).get(i).getAccountID() == accountId) {
                closedAccount = getAllAccountsArrayList(pNr).get(i).toStringClose();
                bankLogicRepository.closeAccount(pNr, getAllAccountsArrayList(pNr).get(i).getAccountID());
            }
        }
        return closedAccount;
    }

    /**
     * create an account for a allCustomersArrayList with personal number, that
     * returns or return -1 if not created
     *
     * @param pNr
     * @return
     */
    public int addCreditAccount(long pNr) {

        return bankLogicRepository.addCreditAccount(pNr);
    }

    public List<String> getTransaktions(long pNr, int accountId) {
        List<String> transactionList = null;
        for (int i = 0; i < allCustomersArrayList.size(); i++) {

            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr) {
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++) {
                    if (allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID() == accountId) {
                        for (int t = 0; t < allCustomersArrayList.get(i).getCustumerAccountsList().get(j).
                                getCustumerAccountsTransaktionsList().size(); t++) {

                            transactionList.add(allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getCustumerAccountsTransaktionsList().get(t).toString());
                            return transactionList;
                        }
                    }
                }
            }
        }

        return null;
    }

    //To get the specific customer's accounts
    public List<Account> getAllAccount(long pNr) {

        return bankLogicRepository.getAllAccountArrayList(pNr);
    }
}
