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
public class CreditAccount extends Account 
{
    final static double creditLimit = -5000;
    final static private double creditAccountLoanRate = 7;
    final static private double creditAccountPositiveRate=0.5;
    final static double interestRate = 0;

    
    public CreditAccount(){
    }

    public CreditAccount( String accountType)
    {
        super(accountType);  //edited
        super.setInterestRate(interestRate);

    }
 
    public double getCreditAccountPositiveRate()
    {
        return creditAccountPositiveRate;
    }


    public double getCreditAccountLoanRate() {
        return creditAccountLoanRate;
    }

    @Override
    public void withdraw(double withdrawAmount)
    {

       double sum = super.getBalance()- withdrawAmount;
       if(sum >= creditLimit && sum < 0)
       {
           super.setBalance(sum);        
           custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), -withdrawAmount, getBalance(), "Ut"));
        
       }
       else if (sum >=0 )
        {
            super.setBalance(sum); 
            
        }
        
      else
        {
           
             System.out.println("Credit limit is -5000");
                    }
    }
    
    @Override
    public void deposit(double depositAmount)
    {
        super.setBalance(depositAmount + super.getBalance());
            custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), depositAmount, getBalance(), "In"));
       
    }
    
    
    
    @Override
    public double closeAccount()
    {
         
        //double division = sum/1.07 = 4672;
        if(super.getBalance() >=  0)
        {
            setBalance(super.getBalance() + (creditAccountPositiveRate * super.getBalance()/100));//rate is 0.5% for the balance 
        }
        else if(super.getBalance() <  0)
        {
            setBalance(super.getBalance() + (creditAccountLoanRate * super.getBalance()/100));//rate is 7% for the balance
        }
        return super.getBalance();
    }
    @Override
     public String toStringClose()
     {
      if(super.getBalance() >=  0)
                {
                    return "Balance " + closeAccount() + ", Rate " + creditAccountPositiveRate + ", AccountType  " + getAccountType() + ", AccountID " + accountID + "\n";
                }
             else if(super.getBalance() <  0)
             {
                 return "Balance " + closeAccount() + ", Rate " + creditAccountLoanRate + ", AccountType  " + getAccountType() + ", AccountID " + accountID + "\n";
             }
      return "";
     }
    
}