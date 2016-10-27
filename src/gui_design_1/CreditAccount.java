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
    private double creditLimit = -5000;
    static private double creditAccountLoanRate = 7;
    static private double creditAccountDepositInterest=0.5;
    private int accountID;
    private double creditBalance;
    
    public CreditAccount(){
    }

    public CreditAccount( String accountType, double interestRate,double creditAccountLoanRate)
    {
        super(accountType, interestRate);  //edited
        this.creditLimit = creditLimit;
        this.creditAccountLoanRate = creditAccountLoanRate;
        this.creditAccountDepositInterest = creditAccountDepositInterest;
        this.creditBalance= creditBalance;
        this.accountID = super.getAccountID();
    }
    
    
    
    public CreditAccount( String accountType, double interestRate, double creditLimit, double debtRate, double balance){
     super(balance, interestRate, accountType);
    this.creditLimit = creditLimit;
        this.creditAccountLoanRate = debtRate;
        this.creditBalance= creditBalance;
        super.balance=creditBalance;
        this.accountID = super.getAccountID();
        this.creditAccountDepositInterest = interestRate;
    }
    
     public CreditAccount( String accountType){
    super(accountType);
    this.creditLimit = creditLimit;
        this.creditAccountLoanRate = creditAccountLoanRate;
        this.creditBalance= creditBalance;
        this.accountID = super.getAccountID();
        super.setBalance(balance);
    }


    public double getCreditAccountDepositInterest()
    {
        return creditAccountDepositInterest;
    }

    public void setCreditAccountDepositInterest(double creditAccountDepositInterest)
    {
        this.creditAccountDepositInterest = creditAccountDepositInterest;
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

    
}