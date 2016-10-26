/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Allan
 */
public class Transaktions
{
    private Account accountId;
    private Date dateTime;
    private String transactionType;
    private double amount;
    private double balanceAfterTransaction;

    
    
    public Transaktions()
    {
        
    }
        
    public Transaktions(Account accountId, Date dateTime, String transactionType, double amount, double balanceAfterTransaction)
    {
        this.accountId = accountId;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Account getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Account accountId)
    {
        this.accountId = accountId;
    }

    public Date getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(Date dateTime)
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
        return "accountId=" + accountId + ", dateTime=" + dateTime + ", transactionType=" + transactionType + ", amount=" + amount + ", balanceAfterTransaction=" + balanceAfterTransaction + '}';
    } 
}