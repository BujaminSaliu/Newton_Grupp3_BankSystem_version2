/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;


import gui_design_1.Account;
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
    com.mysql.jdbc.Connection connection;
    com.mysql.jdbc.Statement statement;
    
    // URL 
    String url = "jdbc:mysql://127.0.0.1:3306/banksystem?user=root&password=root";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
    

    public Repository()
    {
        try
        {
            connection = (com.mysql.jdbc.Connection)DriverManager.getConnection(url);
            statement = (com.mysql.jdbc.Statement) connection.createStatement();
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Fel i Connection till Databas");
            //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //returns list of customer, ersätter allcustomerarraylist
public List<Customer> getAllCustomers( )
{
    List <Customer> repositCustList = new ArrayList<>();
    try
    {
        //step 3. execute sql query
        
        ResultSet result = statement.executeQuery("SELECT * FROM banksystem.customers");
        while(result.next()){
            System.out.println(result.getString("customerName") + result.getString("personalNumber"));
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

public boolean addCustomer(String name, long pNr) throws Exception
{
    boolean added = true; 
        try
        {
            statement.executeUpdate("INSERT into Customers (customerName, personalNumber) VALUES ('"  + name + "', " + pNr + ")");
        } catch (SQLException ex)
        {
            added = false;
            return added;
            //Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return added;
}

public int addCreditAccount(long pNr)
{
    int accountCounter = 0;
    
    try
        {
            ResultSet result = statement.executeQuery("SELECT count(accountID) FROM accounts");
            while (result.next())
                    // hittar högsta kontonummer
                    accountCounter = result.getInt("count(accountID)") + 1000;
            
            String insertSqlAddCreditAcc = " insert into accounts "
                    + "(accountID, balance, personalNumber, account_type)"
                    + " values (" + (accountCounter + 1) + ", 0, " +(double)pNr+ " , 'Credit Account')";
            
            statement.executeUpdate(insertSqlAddCreditAcc);
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accountCounter + 1;        
        
}

public String getCustumerAllAccounts(int pNr)
{
    List <Account> repositAccountList = new ArrayList<>();
    String getAccountReturnString = null;
    
        try
        {
            ResultSet result = statement.executeQuery("SELECT * FROM accounts WHERE ");
            while (result.next())
            {
                
            }
        } 
        
        catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return getAccountReturnString;
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
            Transaktions t =  new Transaktions(transDate,transAccID,transAmount );
            
            repositCustTransList.add(c);
                    
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

