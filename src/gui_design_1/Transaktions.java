/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Befkadu Degefa
 */
public class Transaktions
{
    private long accountId;
    //private Date dateTime;
    private String transactionType;
    private double amount;
    private double balanceAfterTransaction;
    
    Calendar dateTime = Calendar.getInstance();
      
    
    public Transaktions()
    {
        
    }
        
    public Transaktions(long accountId, String transactionType, double amount, double balanceAfterTransaction)
    {
        this.accountId = accountId;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(long accountId)
    {
        this.accountId = accountId;
    }

    public Calendar getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(String transactionType)
    {
        this.transactionType = transactionType;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public double getBalanceAfterTransaction()
    {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(double balanceAfterTransaction)
    {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    @Override
    public String toString()
    {
        return "accountId=" + accountId +  ", transactionType=" + transactionType + ", amount=" + amount + ", balanceAfterTransaction=" + balanceAfterTransaction + '}';
    } 
}