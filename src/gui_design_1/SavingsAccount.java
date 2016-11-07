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
    
    private double interestRate = 2;
    private final static double DEPOSITINTREST = 1;

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
        

    }



    @Override
    public void withdraw(double withdrawAmount)
    {

        if (counter == 0)
        {
            super.setBalance(super.getBalance() - withdrawAmount);
            getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));
            counter++;
        } else if (counter > 0)
        {
            //To protect the saving account above 0
            if((withdrawAmount + (interestRate * withdrawAmount / 100)) > super.getBalance())
            {
                super.setBalance(super.getBalance());
            }

            else
            {
            super.setBalance(super.getBalance() - (withdrawAmount + (interestRate * withdrawAmount / 100)));
            getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));

            }
        }

    }

    @Override
    public void deposit(double depositAmount)
    {
        super.setBalance(depositAmount + super.getBalance());
        getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), getAccountID(), Math.round(depositAmount * 100.0) / 100.0, super.getBalance(), "In"));

    }

    @Override
    public double closeAccount()
    {

        super.setBalance(super.getBalance() + (super.getBalance() * DEPOSITINTREST / 100));
        return super.getBalance();
    }

    @Override
    public String toStringClose()
    {

        return "Saldo: " + closeAccount() + ", Ränta: " + DEPOSITINTREST + "%, Kontotyp:  " + getAccountType() + ", KontoID: " + super.getAccountID() + "\n";
    }
}
