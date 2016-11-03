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
    public final static double interestRate = 2;
    final static double depositIntrest = 1;

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

        if (counter == 0)
        {
            super.setBalance(super.getBalance() - withdrawAmount);
            custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));
            counter++;
        } else if (counter > 0)
        {

            super.setBalance(super.getBalance() - (withdrawAmount + (interestRate * withdrawAmount / 100)));
            custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));

        }

    }

    @Override
    public void deposit(double depositAmount)
    {
        super.setBalance(depositAmount + super.getBalance());
        custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), Math.round(depositAmount * 100.0) / 100.0, super.getBalance(), "In"));

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

        return "Balance " + closeAccount() + ", Rate " + depositIntrest + ", AccountType  " + getAccountType() + ", AccountID " + accountID + "\n";
    }
}
