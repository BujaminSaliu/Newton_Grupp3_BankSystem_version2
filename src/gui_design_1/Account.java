/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

/**
 *
 * @author Befkadu Degefa
 */
public class Account
{
    public static int accountIDCounter = 1000;
    
    double balance;
    private double interestRate; 
    private String accountType;
    int accountID;
    
   
    public Account() 
    {
            
    }

    public Account(double balance, double interestRate, String accountType, int accountID)
    {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountID;
    }
       

    public Account(double balance, double interestRate,  String accountType)
    {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter ++;
    }

    public Account(String accountType, double interestRate)
    {
        
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter ++;
       
    }
    

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    
    public String getAccountType()
    {
        return accountType;
    }

    public int getAccountID()
    {
        return accountID;
    }

    public void setAccountID(int accountID)
    {
        this.accountID = accountID;
    }
    
    
    
    public void deposit(double depositAmount)
    {
        balance = balance + depositAmount;
    }  
    
    public void withdraw(double withdrawAmount)
    {
        balance = balance -withdrawAmount;
    }
    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate = interestRate;
    }


    @Override
    public String toString()
    {
        return "Account{" + "balance=" + balance + ", interestRate=" + interestRate + ", accountType=" + accountType + ", accountID=" + accountID + '}';
    }
}