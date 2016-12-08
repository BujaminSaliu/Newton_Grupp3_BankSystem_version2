/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.util.Date;

/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class Transaktions
{
    //private long accountId;
    private int accountId;
    private Date dateTime;
    private String transactionType;
    private double amount;
    private double balanceAfterTransaction;
    private String inOut;
    private String date;
    
      
    
    public Transaktions()
    {
                            
    }
        
    public Transaktions(String date,int accountId,double amount, double balanceAfterTransaction, String inOut)
    {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.inOut = inOut;
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
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

    public String getInOut()
    {
        return inOut;
    }

   public void setInOut(String inOut)
    {
        this.inOut = inOut;
    }
    public String toString2()
    {
        
        
        return  getDate()+ "          " + getInOut() + "          " + amount + "kr          Saldo: " + balanceAfterTransaction + " kr\n";
    } 
}
