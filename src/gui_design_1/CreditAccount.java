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
    private double creditLimit;
    private double debtRate;
    private int accountID;
    
    public CreditAccount(){
    }

    public CreditAccount(double creditLimit, double debtRate, int accountID, double balance, double interestRate, String accountType)
    {
        super(balance, interestRate, accountType);
        this.creditLimit = creditLimit;
        this.debtRate = debtRate;
        this.accountID = accountID;
    }
    
    
    
//    public CreditAccount( String accountType, double interestRate, double creditLimit, double debtRate){
//    super(accountType, interestRate);
//    this.creditLimit=creditLimit;
//    this.debtRate=debtRate;
//    this.accountID = Account.accountIDCounter;
//    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getDebtRate() {
        return debtRate;
    }

    public void setDebtRate(double debtRate) {
        this.debtRate = debtRate;
    }
    
    
    
}