/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.util.Calendar;

/**
 *
 * @author Befkadu Degefa
 */
public class CreditAccount extends Account 
{
    private double creditLimit = -5000;
    static private double creditAccountLoanRate = 7;
    static private double creditAccountPositiveRate=0.5;
    //private int accountID;
    private double creditBalance;
    
    public CreditAccount(){
    }

    public CreditAccount( String accountType, double creditAccountLoanRate,double interestRate)
    {
        super(accountType, interestRate);  //edited
        this.creditLimit = creditLimit;
        this.creditAccountLoanRate = creditAccountLoanRate;
        this.creditAccountPositiveRate = creditAccountPositiveRate;
        this.creditBalance= creditBalance;
       // this.accountID = super.getAccountID();
    }




    public double getCreditAccountPositiveRate()
    {
        return creditAccountPositiveRate;
    }

    public void setCreditAccountPositiveRate(double creditAccountPositiveRate)
    {
        this.creditAccountPositiveRate = creditAccountPositiveRate;
    }

    
    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCreditAccountLoanRate() {
        return creditAccountLoanRate;
    }

    public void setCreditAccountLoanRate(double creditAccountLoanRate) {
        this.creditAccountLoanRate = creditAccountLoanRate;
    }

    public double getCreditBalance()
    {
        return creditBalance;
    }

    @Override
    public void withdraw(double withdrawAmount)
    {

        double sum = super.getBalance()- withdrawAmount;
        //double division = sum/1.07 = 4672;
        if(sum > -5000 && sum < 0)
        {
            //super.setBalance(0);
            super.withdraw(-sum);  
            //super.withdraw(-sum - (super.getInterestRate()*sum/100));  //7% loan rate
        }
        
        else if (sum >=0 )
        {
            super.withdraw(withdrawAmount);
        }
        
      else
        {
           
             System.out.println("Credit limit is -5000");
                    }

    }
    
    @Override
    public void deposit(double depositAmount)
    {
        super.deposit(depositAmount);
    }
    
//    @Override
//    public double closeAccount()
//    {
//         
//        //double division = sum/1.07 = 4672;
//        if(super.getBalance() >=  0)
//        {
//            setBalance(super.getBalance() + (creditAccountPositiveRate * super.getBalance()/100));//rate is 0.5% for the balance 
//        }
//        else if(super.getBalance() <  0)
//        {
//            setBalance(-super.getBalance() - (creditAccountLoanRate * super.getBalance()/100));
//        }
//        return balance;
//    }
    
}