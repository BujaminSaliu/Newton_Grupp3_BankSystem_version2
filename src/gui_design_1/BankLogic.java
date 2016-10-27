/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Befkadu Degefa
 */
public class BankLogic
{
    static List<Customer> allCustomersArrayList;
    private static BankLogic instance; //Step 2 declare the instance variabel
    static List<String> removedCustomerList = new ArrayList<>();
    static List<Transaktions> transaktionsArrayList;
    static List<String> transaktionsStringArrayList;
    Calendar dateTime = Calendar.getInstance();
    
   
 
    private BankLogic() //Step 1 change this constructor to private
    {
        allCustomersArrayList = new ArrayList<>();
    }
   
    public static BankLogic getInstance() //Step 3 write getInstance method
    {
        if(instance==null)
        {
            instance = new BankLogic();
        }
        return instance;
    }
   
    /**
     * Returns all allCustomersArrayList of the bank(Personal number and name)
     * @return
     */
//    public List<Customer> getCustomers()    
//    {
//        System.out.println("ID" +Account.accountIDCounter);
// 
//        return allCustomersArrayList;
//    }
   
    public List<String> getCustomers()
    {
        List<String> stringListCustomer = new ArrayList<>();
        for(int i = 0;i < allCustomersArrayList.size();i++)
        {
           stringListCustomer.add(allCustomersArrayList.get(i).toString2());
        }
        
        return stringListCustomer;
    }
     
   
    /**
     * Adding the allCustomersArrayList, if not exist in the system. 
     * Returns true if the allCustomersArrayList created
     * @param name
     * @param pNr
     * @return
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
     * To get the information about the specific allCustomersArrayList after entering 
     * the personal number, 
     * returns name and pNr
     * @param pNr
     * @return
     */
    public List<String> getCustomer(long pNr)  
    {
        //Creating empty ArrayList to put all the customerAccountList
        List<String> searchCustomer = new ArrayList<>(); 
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                searchCustomer.add(allCustomersArrayList.get(i).toString());
                for (int j = 0; j < allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
                {
                    searchCustomer.add(allCustomersArrayList.get(i).getCustumerAccountsList().get(j).toString());
                }
                break;
            }
        }
        return searchCustomer;
    }
   
    /**
     * Change name for the guy having the same personal number
     * return true if the name changes
     * @param name
     * @param pNr
     * @return
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
       
        //If there is the same personal number, change the name
//        if(changeCustomerName)
//        {
//        //System.out.println(allCustomersArrayList.size());//test
//        allCustomersArrayList.add(new Customer(name, pNr));
//        }
//       
        return changeCustomerName;
    }
   
    /**
     * Deleting the allCustomersArrayList with pNr and the result will be returned
 The return list will have the information about the last balance, interest
     * @param pNr
     * @return
     */
    public List<String> removeCustomer(long pNr)  
    {
       
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
           
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                
                removedCustomerList.add(allCustomersArrayList.get(i).toString1());
                
                //To remove the allCustomersArrayList, but couldn't removed 1 account if the person has
                //3 accounts
//                for(int j = 0; j< allCustomersArrayList.get(i).getCustumerAccountsList().size(); j++)
//                {
//                    allCustomersArrayList.get(i).getCustumerAccountsList().remove(allCustomersArrayList.get(i).getCustumerAccountsList().get(j));
//                }
                
                //After removing allCustomersArrayList, the allCustomersArrayList will be removed
                allCustomersArrayList.remove(allCustomersArrayList.get(i));
                
                break;
            }
           
        }
       
        return removedCustomerList;
    }

   
    /**
     * create an account for a allCustomersArrayList with personal number, that returns 
      or return -1 if not created
     * @param pNr
     * @return
     */
    public int addSavingsAccount(long pNr) 
    {
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)   
            {   
               allCustomersArrayList.get(i).getCustumerAccountsList().add(new SavingsAccount("Savings", 2)); 
                return allCustomersArrayList.get(i).custumerAccountsList.get(allCustomersArrayList
                        .get(i).custumerAccountsList.size()-1).getAccountID();
             
               //I have no idea but it worked
//               for (int j = allCustomersArrayList.get(i).getCustumerAccountsList().size(); j > 0; j--)
//                {
//                    //System.out.println("j " + j);//test
//                    allCustomersArrayList.get(i).getCustumerAccountsList().add(new SavingsAccount("Savings", 2));
//                    return (allCustomersArrayList.get(i).custumerAccountsList.get(j).getAccountID());
//                }

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
        dateTime.add(Calendar.DATE, 0);
        Date today = dateTime.getTime();  //today        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");    //The date format    
        String formatted = format1.format(dateTime.getTime());
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr )
            {
                for (int j = 0; j < allCustomersArrayList.get(i).custumerAccountsList.size(); j++)
                {
                    //if condition edited, checked the account type- for Savings
                    if (allCustomersArrayList.get(i).custumerAccountsList.get(j).getAccountID()== accountId)
                    {
                        allCustomersArrayList.get(i).custumerAccountsList.get(j).deposit(amount);
                        System.out.println("Balance becomes in side BankLigic class in deposit method " +i + " "+ allCustomersArrayList.get(i).custumerAccountsList.get(j).getBalance()
                                );
                        //transaktionsArrayList.add(new Transaktions(accountId, allCustomersArrayList.get(i).custumerAccountsList.get(i).getAccountType(), amount, allCustomersArrayList.get(i).custumerAccountsList.get(i).getBalance()));
                        depositMade = true;
                    }
                    
                   }
            }

        }
        return depositMade;
    }
    
    public boolean withdraw(long pNr, int accountId, double amount)
    {
        boolean withdrawMade = false;
        
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)
            {
                for (int j = 0; j < allCustomersArrayList.get(i).custumerAccountsList.size(); j++)
                {
                    if (allCustomersArrayList.get(i).custumerAccountsList.get(j).getAccountID() == accountId)
                    {
                        allCustomersArrayList.get(i).custumerAccountsList.get(j).withdraw(amount);
                         System.out.println("Balance becomes in side BankLigic class in withdraw method " 
                                 + allCustomersArrayList.get(i).custumerAccountsList.get(j).getBalance());
                         allCustomersArrayList.get(i).custumerAccountsList.get(j)
                                 .custumerAccountsTransaktionsList.add(new Transaktions(accountId, "Ut", amount
                                         , allCustomersArrayList.get(i).custumerAccountsList.get(j).getBalance()));
                         withdrawMade = true;
                    }
                }
            }            
        }
        return withdrawMade;
    }
    
    public String closeAccount(long pNr, int accountId)
    {
        String closedAccount = null;
        for(int i = 0; i < allCustomersArrayList.size();i++)
        {
            if(allCustomersArrayList.get(i).getPersonalNumber()==pNr)
            {
                for(int j = 0; j < allCustomersArrayList.get(i).custumerAccountsList.size(); j++)
                {
                    if(allCustomersArrayList.get(i).custumerAccountsList.get(j).getAccountID()==accountId)
                    {
                        closedAccount = allCustomersArrayList.get(i).custumerAccountsList.get(j).toString();
                        allCustomersArrayList.get(i).custumerAccountsList.remove(allCustomersArrayList.get(i).custumerAccountsList.get(j));
                        System.out.print("Personal number " + pNr + ", ");
                    }
                }
            }
                
            }return closedAccount;
    }
    
    /**
     * create an account for a allCustomersArrayList with personal number, that returns 
      or return -1 if not created
     * @param pNr
     * @return
     */
    public int addCreditAccount(long pNr) 
    {
        for (int i = 0; i < allCustomersArrayList.size(); i++)
        {
            if (allCustomersArrayList.get(i).getPersonalNumber() == pNr)   
            {   
               allCustomersArrayList.get(i).getCustumerAccountsList().add(new CreditAccount("Credit Account", 0.5,7)); 
                return allCustomersArrayList.get(i).custumerAccountsList.get(allCustomersArrayList
                        .get(i).custumerAccountsList.size()-1).getAccountID();
            }
            
        }

       
        return -1;
    }
    
    public List<String> getTransaktions(long pNr, int accountId)
    {
        for(int i =0; i <transaktionsArrayList.size(); i++)
        {
            if(allCustomersArrayList.get(i).getPersonalNumber()== pNr 
                    && transaktionsArrayList.get(i).getAccountId()==accountId)
            {
                System.out.println("Test Transaction class" + transaktionsArrayList.get(i).getAmount());
                transaktionsStringArrayList.add(transaktionsArrayList.get(i).toString());
                return transaktionsStringArrayList;
            }
        }
        
        return null;
    }
}