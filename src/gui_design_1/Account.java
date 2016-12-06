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

public abstract class Account
{

    private static int accountIDCounter = 1001;

    private double balance;
    private double interestRate;
    private String accountType;
    private int accountID;
    private ArrayList<Transaktions> custumerAccountsTransaktionsList;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
    Date date = new Date();

    public Account()
    {
    }

    //This method will be inherited by the credit account, edited
    public Account(String accountType)
    {
        this.accountType = accountType;
        this.accountID = accountID;
        
        custumerAccountsTransaktionsList = new ArrayList<>();

    }
    
    
    //To get the recent balance
    public double getBalance()
    {
        return balance;
    }

    //To set the updated balance 
    public void setBalance(double balance)
    {
        double roundOff = Math.round(balance * 100.0) / 100.0; //to make only 2 digits after the decimal point
        this.balance = roundOff;
    }

    //To get the account type
    public String getAccountType()
    {
        return accountType;
    }

    //To get the Account ID
    public int getAccountID()
    {
        return accountID;
    }

    //To set the Account ID
    public void setAccountID(int accountID)
    {
        this.accountID = accountID;
    }

    //Abstract methods implemented by sub classes, SavingsAccount and CreditAccount
    public abstract void withdraw(double withdrawAmount);

    public abstract void deposit(double depositAmount);

    public abstract double closeAccount();

    public abstract String toStringClose();

    //To get the interest set by the sub classes,SavingsAccount and CreditAccount
    public double getInterestRate()
    {
        return interestRate;
    }

    //To set the interest rate set by the sub classes,SavingsAccount and CreditAccount
    public void setInterestRate(double interestRate)
    {

        this.interestRate = interestRate;
    }

    //The ArrayList that holds every transaction made by the customer, it will be updated on every deposit and withdraw
    public ArrayList<Transaktions> getCustumerAccountsTransaktionsList()
    {
        return custumerAccountsTransaktionsList;
    }

    
    public String toString2()
    {
        return "Saldo: " + balance + ", Ränta: " + interestRate + "%, Kontotyp: " + getAccountType() + ", kontoID: " + accountID + "\n";
    }

}
