/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;


import gui_design_1.Account;
import gui_design_1.CreditAccount;
import gui_design_1.Customer;
import gui_design_1.SavingsAccount;
import gui_design_1.Transaktions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allan
 */
public class Repository
{
    Connection connection;
   Statement statement;
   List <Customer> repositCustList;
   List <Account> getAccountArrayList;
   private ArrayList <Account> repositAccountList;
    
    // URL 
    String url = "jdbc:mysql://127.0.0.1:3306/banksystem?user=root&password=root";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
    

    public Repository()
    {
        try
        {
            connection = DriverManager.getConnection(url);
            statement =  connection.createStatement();
            
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Fel i Connection till Databas");
            //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns all allCustomersArrayList of the bank(Personal number and name)
     *
     * @return
     */
    public List<String> getCustomers()
    {
        List<String> stringListCustomer = new ArrayList<>();
        try
        {

            ResultSet result = statement.executeQuery("SELECT * FROM customers");
            while(result.next()){
                  String s1= result.getString("customerName");
            String s2 = result.getString("personalNumber");
            Customer c =  new Customer(s1, Long.parseLong(s2));
            
            stringListCustomer.add(c.toString2());
            
     
            }

            
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stringListCustomer;
    }
    
    //returns list of customer, ersätter allcustomerarraylist
public List<Customer> getAllCustomers( )
{
    repositCustList = new ArrayList<>();
    try
    {
        //step 3. execute sql query
        
        ResultSet result = statement.executeQuery("SELECT * FROM customers");
        while(result.next()){
            String s1= result.getString("customerName");
            String s2 = result.getString("personalNumber");
            Customer c =  new Customer(s1, Long.parseLong(s2));
            
            repositCustList.add(c);
          
        }
    } catch (SQLException ex)
    {
        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return repositCustList;
    
}

public List<Transaktions> getAllTransactions(int accountID)
{
    List <Transaktions> repositTransList = new ArrayList<>();
    try
    {
        //step 3. execute sql query
        
        ResultSet result = statement.executeQuery("SELECT * FROM transactions WHERE accounts_accountID = " + accountID);
        while(result.next()){
            System.out.println(result.getInt("transaction_Id") + result.getInt("accounts_accountID"));
            int transID = result.getInt("transaction_Id");
            String date = result.getString("date");
            double amount = result.getDouble("amount");
            int accounts_accountID = result.getInt("account_accounts_accountID");
            double balance_after = result.getDouble("balance_after_tranaction");
            String inOut = "toFill";
            
            if (amount < 0)
                inOut = "out";
            if (amount > 0)
                inOut = "in";           
            
            
            Transaktions t =  new Transaktions(date, accounts_accountID, amount, balance_after, inOut);
//            (String date,int accountId,double amount, double balanceAfterTransaction, String inOut)
            //transaction_Id, date, amount, account_accounts_accountID, balance_after_tranaction
            
            repositTransList.add(t);
                    
        }
    } catch (SQLException ex)
    {
        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return repositTransList;
}


public String getAccount(long pNr)
    {
        String getAccountReturnString = null;
        int accountCounter = 0;
        try
        {
            ResultSet result1 = statement.executeQuery("SELECT max(accounts_accountID) FROM accounts");
            while (result1.next())
                    // hittar högsta kontonummer
            {  
                accountCounter = result1.getInt("max(accounts_accountID)")+ 1001 ;
        }
            ResultSet result = statement.executeQuery("SELECT * FROM accounts WHERE customers_personalNumber like " + pNr);
            while(result.next()){
            
            
            if(result.getString("account_type").equals("Credit"))
            {
                CreditAccount ca = new CreditAccount("Credit");
                ca.setAccountID(accountCounter);
                getAccountReturnString = ca.toString2();
                
            }
            else
            {
                SavingsAccount sa = new SavingsAccount("Savings");
                sa.setAccountID(accountCounter);
                getAccountReturnString= sa.toString2();

            }
            }
//            ResultSet result = statement.executeQuery("SELECT * FROM accounts WHERE customers_personalNumber like " + pNr);
//            while (result.next())
//             {
//                getAccountReturnString = result.getString(1) + "   " + result.getString(2) + "   " + result.getString(3) + 
//                        "   " + result.getDouble(4) + "   " + result.getString(5) ;
//             }    
             
              
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        return getAccountReturnString;
    }

//For the customer's specific account

public List<Account> getAllAccountArrayList(long pNr)
{
    //repositAccountList = new ArrayList<>();
   getAccountArrayList = new ArrayList<>();

   try
        {
            
            ResultSet result = statement.executeQuery("SELECT * FROM accounts WHERE customers_personalNumber like " + pNr);
            while(result.next()){
            
            
            if(result.getString("account_type").equals("Credit"))
            {
                CreditAccount ca = new CreditAccount("Credit");
                ca.setBalance(result.getDouble("balance"));
                ca.setAccountID(result.getInt("accounts_accountID"));
                getAccountArrayList.add(ca);
                
            }
            else
            {
                SavingsAccount sa = new SavingsAccount("Savings");
                sa.setBalance(result.getDouble("balance"));
                sa.setAccountID(result.getInt("accounts_accountID"));
                getAccountArrayList.add(sa);
            }
        
        }
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return getAccountArrayList;
}



public boolean addCustomer(String name, long pNr) throws Exception
{
    boolean added = true; 
       
   
        try
        { 
             ResultSet result = statement.executeQuery("SELECT * from customers ");
             while (result.next())
             {
            statement.executeUpdate("INSERT INTO customers (customerName, personalNumber) VALUES ('"  + name + "', " + pNr + ")");
             }
        } catch (SQLException ex)
        {
            added = false;
            return added;
        }
        return added;
}

public int addCreditAccount(long pNr)
{
    int accountCounter = 0;
     String insertSqlAddCreditAcc = null;
    try
        {
            ResultSet result = statement.executeQuery("SELECT count(accounts_accountID) FROM accounts");
            while (result.next())
                    // hittar högsta kontonummer
                    accountCounter = result.getInt("count(accounts_accountID)")+ 1001 ;
            
            insertSqlAddCreditAcc = " insert into accounts (accounts_accountID, account_type, customers_personalNumber)"
                    + " values (" + (accountCounter + 1) + ", 'Credit', " +(double)pNr+ " )";


            
            statement.executeUpdate(insertSqlAddCreditAcc);
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accountCounter + 1;        
        
}

public int addSavingsAccount(long pNr)
{
    int accountCounter = 0;
     String insertSqlAddSavingAcc = null;
    
    try
        {
            ResultSet result = statement.executeQuery("SELECT count(accounts_accountID) FROM accounts");
            while (result.next())
                    // hittar högsta kontonummer
                    accountCounter = result.getInt("count(accounts_accountID)") + 1001 ;
            System.out.println("pnr " + pNr);
            
            insertSqlAddSavingAcc = " insert into accounts (accounts_accountID, account_type, customers_personalNumber)"
                    + " values (" + (accountCounter + 1) + ", 'Savings', " +(double)pNr+ " )";


            
            statement.executeUpdate(insertSqlAddSavingAcc);
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accountCounter + 1;        
        
}



//public List<Transaktions> getAccoutAllTransaktions(int accountID)
//{
//    List <Transaktions> repositCustTransList = new ArrayList<>();
//    try
//    {
//        //step 3. execute sql query
//        
//        ResultSet result = statement.executeQuery("SELECT * FROM banksystem.tranactions WHERE accountID = " + accountID );
//        while(result.next())
//        {
//            // transaction_Id, date, accountID, personalNumber, account_type, amount
//            // String date,int accountId,double amount, double balanceAfterTransaction, String inOut
//            
//            //System.out.println(result.getInt("transaction_Id") + result.getDate("date"));
//            int transID = result.getInt("transaction_Id");
//            String transDate = dateFormat.format(result.getDate("date"));
//            int transAccID = result.getInt("accountID");
//            String transPNr = result.getString("personalNumber");
//            String transAccType = result.getString("account_type");
//            double transAmount = result.getDouble("amount");
//            Transaktions t =  new Transaktions(transDate,transAccID,transAmount );
//            
//            repositCustTransList.add(t);
//                    
//        }
//    } catch (SQLException ex)
//    {
//        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return repositCustTransList;
//}

public List<Transaktions> getAccoutAllTransaktions(int accountID)
{
    List <Transaktions> repositCustTransList = new ArrayList<>();
    try
    {
        //step 3. execute sql query
        
        ResultSet result = statement.executeQuery("SELECT * FROM tranactions WHERE accountID = " + accountID );
        while(result.next())
        {
            // transaction_Id, date, accountID, personalNumber, account_type, amount
            // String date,int accountId,double amount, double balanceAfterTransaction, String inOut
            
            //System.out.println(result.getInt("transaction_Id") + result.getDate("date"));
            int transID = result.getInt("transaction_Id");
            String transDate = dateFormat.format(result.getDate("date"));
            int transAccID = result.getInt("accounts_accountID");
            String transPNr = result.getString("personalNumber");
            String transAccType = result.getString("account_type");
            double transAmount = result.getDouble("amount");
            double balance_after_tranaction = result.getDouble("balance_after_tranaction");
            String inOut = "null";
            if (transAmount < 0)
                inOut = "out";
            if (transAmount > 0)
                inOut = "in";
            Transaktions t =  new Transaktions(transDate, transAccID, transAmount, balance_after_tranaction, inOut);
            
            repositCustTransList.add(t);
                    
        }
    } catch (SQLException ex)
    {
        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return repositCustTransList;
}

public boolean deposit(int accountID, double amount)
    {
        double currentBalance = 0;
        double newBalance = 0;
        boolean depositMade = false;
        // System.out.println("accountID " +accountID);
        try
        {
            
            
           ResultSet result1 = statement.executeQuery("SELECT * FROM accounts WHERE accounts_accountID = " + accountID );
          while (result1.next())
                  {
                      currentBalance = result1.getDouble("balance");
                      newBalance = currentBalance + amount;
                  } 

                statement.executeUpdate("UPDATE accounts SET balance = " + newBalance + " WHERE accounts_accountID = " + accountID );

        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    

        return depositMade;
    }






public static void main( String [] args ){
    Repository repo = new Repository();
    for(Customer c : repo.getAllCustomers())
        System.out.println();
}

    
}

