/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.util.Calendar;

/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class SavingsAccount extends Account
{

    Calendar currentYear = Calendar.getInstance();
    private int counter = 0;
    //private int accountID;
    private double interestRate = 2;
    final static private double depositIntrest = 1;

    /**
     * Default constructor
     */
    public SavingsAccount()
    {
        super();
    }

    public SavingsAccount(String accountType)
    {
        super(accountType);
        super.setInterestRate(interestRate);
        //this.accountID = super.getAccountID();

    }

//    @Override
//    public int getAccountID()
//    {
//        return accountID;
//    }

    @Override
    public void withdraw(double withdrawAmount)
    {

        if (counter == 0)
        {
            super.setBalance(super.getBalance() - withdrawAmount);
            getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), super.getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));
            counter++;
        } else if (counter > 0)
        {
            //To protect the saving account above 0
            if((withdrawAmount + (super.getInterestRate() * withdrawAmount / 100)) > super.getBalance())
            {
                super.setBalance(super.getBalance());
            }

            else
            {
            super.setBalance(super.getBalance() - (withdrawAmount + (interestRate * withdrawAmount / 100)));
            getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), super.getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));

            }
        }

    }

    @Override
    public void deposit(double depositAmount)
    {
        super.setBalance(depositAmount + super.getBalance());
        getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), super.getAccountID(), Math.round(depositAmount * 100.0) / 100.0, super.getBalance(), "In"));

    }

    @Override
    public double closeAccount()
    {

        super.setBalance(super.getBalance() + (super.getBalance() * depositIntrest / 100));
        return super.getBalance();
    }

    @Override
    public String toStringClose()
    {

        return "Saldo: " + closeAccount() + ", Ränta: " + depositIntrest + "%, Kontotyp:  " + super.getAccountType() + ", KontoID: " + super.getAccountID() + "\n";
    }
}
