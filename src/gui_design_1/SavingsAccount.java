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
public class SavingsAccount extends Account
{

    
    Calendar currentYear = Calendar.getInstance();
    private int counter = 0;
    private int accountID;
    
    /**
     * Default constructor
     */
    public SavingsAccount(){ 
    super();
    }

    public SavingsAccount(double balance, double interestRate, String accountType)
    {
        super(balance, interestRate, accountType);
    }

    public SavingsAccount(String accountType, double interestRate)
    {
        super(accountType, interestRate);
        
        this.accountID = super.getAccountID();
        
        
    }
    
    @Override
    public int getAccountID()
    {
        return accountID;
    }

    
    
    @Override
    public void withdraw(double withdrawAmount)
    {
        
        int numberOfWeeksInYear = currentYear.getWeeksInWeekYear();
        int currentWeekOfYear = currentYear.get(Calendar.WEEK_OF_YEAR);
        if(currentWeekOfYear <= numberOfWeeksInYear)
        {
            if(counter == 0)
            {
            super.withdraw(withdrawAmount);
            counter++;
            }
            
            else
            {
               super.withdraw(withdrawAmount + (getInterestRate()*withdrawAmount/100));
            }
        }

    }

//    System.out
//    public void CalculateInterest()
//    {
//        System.out.println("Balance " + getBalance()); //test
//        double calculatedInterest = getBalance() * interestRate/100;   
//        System.out.println("Calculated Interest " + calculatedInterest); //test
//    }
}