/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class BankLogic
{

    private static List<Customer> allCustomersArrayList;
    private static BankLogic instance; //Step 2 declare the instance variabel
    static List<String> removedCustomerList = new ArrayList<>();

    private BankLogic() //Step 1 declare the constructor and change it to private
    {
        allCustomersArrayList = new ArrayList<>();
    }

    public static BankLogic getInstance() //Step 3 write getInstance method
    {
        if (instance == null)
        {
            instance = new BankLogic();
        }
        return instance;
    }

    public static List<Customer> getAllCustomersArrayList() {
        return allCustomersArrayList;
    }
    
    

    /**
     * Returns all allCustomersArrayList of the bank(Personal number and name)
     *
     * @return
     */
    public List<String> getCustomers()
    {
        List<String> stringListCustomer = new ArrayList<>();
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            stringListCustomer.add(allCustomersArrayList.get(i).toString2());
        }

        return stringListCustomer;
    }

    /**
     * Adding the allCustomersArrayList, if not exist in the system. Returns
     * true if the allCustomersArrayList created
     *
     * @param name
     * @param pNr
     * @return check
     */
    public boolean addCustomer(String name, long pNr)
    {
        boolean check = true;
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                check = false;
                break;
            }
        }

        //if the allCustomersArrayList doesn't exist in the database, he/she will be added here
        if (check == true)
        {
            allCustomersArrayList.add(new Customer(name, pNr));//
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
    public List<String> getCustomer(long pNr)
    {
        //Creating empty ArrayList to put all the customerAccountList
        List<String> searchCustomer = new ArrayList<>();
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
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
    public boolean changeCustomerName(String name, long pNr)
    {
        boolean changeCustomerName = false;
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {

            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                //allCustomersArrayList.remove(allCustomersArrayList.get(i));
                allCustomersArrayList.get(i).setCustomerName(name);
                changeCustomerName = true;
                break;
            }

        }

        return changeCustomerName;
    }

    /**
     * Deleting the allCustomersArrayList with pNr and the result will be
     * returned The return list will have the information about the last
     * balance, interest
     *
     * @param pNr
     * @return removedCustomerList
     */
    public List<String> removeCustomer(long pNr)
    {

        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {

            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {

                removedCustomerList.add(allCustomersArrayList.get(i).toString1());

                //After removing allCustomersArrayList, the allCustomersArrayList will be removed
                allCustomersArrayList.remove(allCustomersArrayList.get(i));

                break;
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
    public int addSavingsAccount(long pNr)
    {
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                allCustomersArrayList.get(i).getCustumerAccountsList().add(new SavingsAccount("Saving Account"));
                return allCustomersArrayList.get(i).getCustumerAccountsList().get(allCustomersArrayList
                        .get(i).getCustumerAccountsList().size() - 1).getAccountID();

            }

        }

        return -1;
    }

    public String getAccount(long pNr, int accountId)
    {
        String getAccountReturnString = null;
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {
                    if (allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID() == accountId)
                    {
                        getAccountReturnString = allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString();
                    }

                }
            }
        }
        return getAccountReturnString;
    }

    public boolean deposit(long pNr, int accountId, double amount)
    {
        boolean depositMade = false;
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {
                    //if condition edited, checked the account type- for Savings
                    if (allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID() == accountId)
                    {
                        allCustomersArrayList.get(i).getCustumerAccountsList().get(j).deposit(amount);
                        System.out.println("Balance becomes in side BankLigic class in deposit method " + i + " " + allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance()
                        );
                        //transaktionsArrayList.add(new Transaktions(accountId, allCustomersArrayList.get(i).custumerAccountsList.get(i).getAccountType(), amount, allCustomersArrayList.get(i).custumerAccountsList.get(i).getBalance()));
                        depositMade = true;
                    }

                }
            }

        }
        return depositMade;
    }

    /**
     * To check if it is possible to make a withdraw, if successful the return is true 
     * if not(for example if there is no enough money in the savings account) the return is false
     * @param pNr
     * @param accountId
     * @param amount
     * @return withdrawMade
     */
    public boolean withdraw(long pNr, int accountId, double amount)
    {
        boolean withdrawMade = false;

        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {
                    if (allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID() == accountId)
                    {
                        //withdrawRate (7%) of withdraw amount plus withdraw amount should be less than -5000
                        //-4672 * 7% - 4672 = -5000
                        if (allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance() <= -5000 &&
                                allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountType().equals("Credit Account")||
                                allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance() <= 0 &&
                                allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountType().equals("Saving Account")
                                ||allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance() < amount &&
                                allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountType().equals("Saving Account")
                                ||allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance() - amount <= 0 &&
                                allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountType().equals("Saving Account"))
                        {
                            withdrawMade = false;
                        } else
                        {
                            allCustomersArrayList.get(i).getCustumerAccountsList().get(j).withdraw(amount);
                            System.out.println("Balance becomes in side BankLigic class in withdraw method "
                                    + allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getBalance());
                            withdrawMade = true;
                        }
                    }
                }
            }System.out.println("Output " + withdrawMade);
        }
        return withdrawMade;
    }

    /**
     * To close the specific account for the customer which returns the customer's 
     * account information, the remaining money, interest rate
     * @param pNr
     * @param accountId
     * @return 
     */
    public String closeAccount(long pNr, int accountId)
    {
        String closedAccount = null;
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {
                    if (allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID() == accountId)
                    {
                        closedAccount = allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toStringClose();

                      
                        allCustomersArrayList.get(i).getCustumerAccountsList().remove(allCustomersArrayList.get(i).getCustumerAccountsList().get(j));
                        System.out.print("Personal number " + pNr + ", ");

                    }
                }
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
    public int addCreditAccount(long pNr)
    {
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                allCustomersArrayList.get(i).getCustumerAccountsList().add(new CreditAccount("Credit Account"));
                return allCustomersArrayList.get(i).getCustumerAccountsList().get(allCustomersArrayList
                        .get(i).getCustumerAccountsList().size() - 1).getAccountID();
            }

        }

        return -1;
    }

    public List<String> getTransaktions(long pNr, int accountId)
    {
        List<String> transactionList = null;
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {

            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)

            {
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {
                    if (allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getAccountID() == accountId)
                    {
                        for (int t = 0; t < allCustomersArrayList.get(i).getCustumerAccountsList().get(j).
                                getCustumerAccountsTransaktionsList().size(); t++)

                        {

                            System.out.println(allCustomersArrayList.get(i).getCustumerAccountsList().get(j).getCustumerAccountsTransaktionsList().toString()); //Test
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
    public List<String> getAllAccount(long pNr, int accountId)
    {
        List<String> getAccountReturnString = new ArrayList<>();
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {

                    getAccountReturnString.add(allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());

                }
            }
        }
        return getAccountReturnString;
    }
}
