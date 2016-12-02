/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;


import gui_design_1.Account;
import gui_design_1.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
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
            //repositCustList.clear();
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
                    accountCounter = result.getInt("count(accountID)") + 1000;
            
            String insertSqlAddCreditAcc = " insert into accounts "
                    + "(accountID, balance, interestRate, accountType, Customers_personalNumber)"
                    + " values (" + (accountCounter + 1) + ", '0', 5, 'Credit Account', " + pNr + ")";
            
            statement.executeUpdate(insertSqlAddCreditAcc);
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accountCounter + 1;        
        
}

public String getAccount(long pNr, int accountId)
{
    List <Account> repositAccountList = new ArrayList<>();
    String getAccountReturnString = null;
    
        try
        {
            ResultSet result = statement.executeQuery("SELECT * FROM accounts");
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



public static void main( String [] args ){
    Repository repo = new Repository();
    for(Customer c : repo.getAllCustomers())
        System.out.println();
}
}

