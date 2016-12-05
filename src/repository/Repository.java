/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;


import gui_design_1.Account;
import gui_design_1.CreditAccount;
import gui_design_1.Customer;
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
            
            System.out.println("get customer repo " + repositCustList);
                    
        }
    } catch (SQLException ex)
    {
        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return repositCustList;
}


public String getAccount(long pNr)
    {
        String getAccountReturnString = null;
        try
        {
            ResultSet result = statement.executeQuery("SELECT * FROM accounts WHERE customers_personalNumber like " + pNr);
            while (result.next())
             {
                getAccountReturnString = result.getString(1) + "   " + result.getString(2) + "   " + result.getString(3) + 
                        "   " + result.getDouble(4) + "   " + result.getString(5) ;
             }    
             
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        return getAccountReturnString;
    }

//For the customer's specific account

public List<String> getAllAccount(long pNr)
{
    repositAccountList = new ArrayList<>();
   List<String> getAccountReturnString = new ArrayList<>();

   try
        {
            ResultSet result = statement.executeQuery("SELECT * FROM accounts WHERE customers_personalNumber like " + pNr);
        while(result.next()){
            
            //get String from db
            if(result.getString("account_type").equals("Credit Account"))
            {
            getAccountReturnString.add(new CreditAccount("Credit Account").toString());
            }
            else
            {
                getAccountReturnString.add(new CreditAccount("Saving Account").toString());
            }
        
        }
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return getAccountReturnString;
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
            ResultSet result = statement.executeQuery("SELECT count(accountID) FROM accounts");
            while (result.next())
                    // hittar högsta kontonummer
                    accountCounter = result.getInt("count(accountID)") + 1001;
            System.out.println("pnr " + pNr);
            
            insertSqlAddCreditAcc = " insert into accounts (accountID, account_type, customers_personalNumber)"
                    + " values (" + (accountCounter + 1) + ", 'Credit Account', " +(double)pNr+ " )";


            
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
            ResultSet result = statement.executeQuery("SELECT count(accountID) FROM accounts");
            while (result.next())
                    // hittar högsta kontonummer
                    accountCounter = result.getInt("count(accountID)") + 1001;
            System.out.println("pnr " + pNr);
            
            insertSqlAddSavingAcc = " insert into accounts (accountID, account_type, customers_personalNumber)"
                    + " values (" + (accountCounter + 1) + ", 'Saving Account', " +(double)pNr+ " )";


            
            statement.executeUpdate(insertSqlAddSavingAcc);
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accountCounter + 1;        
        
}


public List<Transaktions> getAccoutAllTransaktions(int accountID)
{
    List <Transaktions> repositCustTransList = new ArrayList<>();
    try
    {
        //step 3. execute sql query
        
        ResultSet result = statement.executeQuery("SELECT * FROM banksystem.tranactions WHERE accountID = " + accountID );
        while(result.next())
        {
            // transaction_Id, date, accountID, personalNumber, account_type, amount
            // String date,int accountId,double amount, double balanceAfterTransaction, String inOut
            
            //System.out.println(result.getInt("transaction_Id") + result.getDate("date"));
            int transID = result.getInt("transaction_Id");
            String transDate = dateFormat.format(result.getDate("date"));
            int transAccID = result.getInt("accountID");
            String transPNr = result.getString("personalNumber");
            String transAccType = result.getString("account_type");
            double transAmount = result.getDouble("amount");
         //   Transaktions t =  new Transaktions(transDate,transAccID,transAmount );
            
         //   repositCustTransList.add(c);
                    
        }
    } catch (SQLException ex)
    {
        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return repositCustTransList;
}



public static void main( String [] args ){
    Repository repo = new Repository();
    for(Customer c : repo.getAllCustomers())
        System.out.println();
}
}

