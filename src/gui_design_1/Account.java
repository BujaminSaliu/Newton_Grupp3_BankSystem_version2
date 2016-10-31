/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//public class Account
//{
//    // Magnus testar 2
//    public double balance;
//    private String accountType;
//    private double interestRate = 2;    
//    private static int accountID = 1000;
//    private double depositIntrest = 1;
//    private int counter=0;
//   
//    /**
//     * Default constructor
//     */
//    public Account()  { }
// 
//    
//    public Account(String accountType, double interestRate)
//    {
//        
//        //this.balance = balance;
//        this.accountType = accountType;
//        this.interestRate=interestRate;
//        accountID++;
//        setAccountID(accountID);
//       
//    }
//    
//    public Account(double balance, double interestRate,  String accountType)
//    {
//         this.balance = balance;
//        this.accountType = accountType;
//        this.interestRate=interestRate;
//        accountID++;
//        setAccountID(accountID);
//    }
// 
//    //This method will be inherited by the credit account, edited
//    public Account(String accountType)
//    {
//        
//        this.balance = balance;
//        this.accountType = accountType;
//        this.interestRate=interestRate;
//        accountID++;
//        setAccountID(accountID);
//       
//    }
//    public double getBalance()
//    {
//        return balance;
//    }
// 
//    public void setBalance(double balance)
//    {
//        this.balance = balance;
//    }
// 
//   
//    public String getAccountType()
//    {
//        return accountType;
//    }
// 
//    public int getAccountID()
//    {
//        return accountID;
//    }
// 
//    public void setAccountID(int accountID)
//    {
//        this.accountID = accountID;
//    }
//   
//   
//        public void deposit(double depositAmount)
//    {
//        if(getAccountType().equals("Saving"))
//        {
//            balance = depositAmount + balance +(depositIntrest * depositAmount/100) ;
//        }
//        else if(getAccountType().equals("Credit Account"))
//        {
//            
//            CreditAccount creditAccount = new CreditAccount();
//            balance = balance + (depositAmount*creditAccount.getCreditAccountDepositInterest()/100);
//        }
//    }  
//   
//    public void withdraw(double withdrawAmount)
//    {
//        if(getAccountType().equals("Saving"))
//        {
//        if(counter == 0)
//            {
//            balance = balance - withdrawAmount;
//            counter++;
//            }
//            
//        else if(counter>0)
//            {
//                balance = balance - withdrawAmount-(withdrawAmount*interestRate/100);
//            }
//        }
//        else if(getAccountType().equals("Credit Account"))
//        {
//            if((balance< 0 && balance> -5000)|| (withdrawAmount>-5000 && withdrawAmount<0))
//        {
//            balance = balance + withdrawAmount+  withdrawAmount*7/100;
//        }
//        else if(balance < -5000|| (withdrawAmount<-5000))
//        {
//            System.out.println("Credit limit is -5000");
//            this.balance= balance; 
//        }
//            
//        }
//    
//            
//        else if(counter>0)
//            {
//                balance = balance - withdrawAmount-(withdrawAmount*interestRate)/100;
//            }
//    }
//    public double getInterestRate()
//    {
//        return interestRate;
//    }
// 
//    public void setInterestRate(double interestRate)
//    {
//        this.interestRate = interestRate;
//    }
// 
//    @Override
//    public String toString()
//    {
//        return "InterestRate=" + interestRate +  ", Balance: " + getBalance()+ ", Account number " +
//                getAccountID()+ ", Account type: " + accountType;
//    }
//}
public abstract class Account
{

    public static int accountIDCounter = 1000;

    double balance;
    private double interestRate;
    private String accountType;
    int accountID;
    int counter = 0;
    private double depositIntrest = 1;
    public ArrayList<Transaktions> custumerAccountsTransaktionsList;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
    Date date = new Date();
    static private double creditAccountLoanRate = 7;
    static private double creditAccountPositiveRate=0.5;

    public Account()
    {

    }

    //New implementation for the credit account
    public Account(double interestRate, String accountType, int accountID)
    {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter++;
        custumerAccountsTransaktionsList = new ArrayList<>();
    }

    public Account(double balance, double interestRate, String accountType, int accountID)
    {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter++;
        custumerAccountsTransaktionsList = new ArrayList<>();
    }

    public Account(double balance, double interestRate, String accountType)
    {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter++;
        custumerAccountsTransaktionsList = new ArrayList<>();
    }

    public Account(String accountType, double interestRate)
    {

        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter++;
        custumerAccountsTransaktionsList = new ArrayList<>();

    }

    //This method will be inherited by the credit account, edited
    public Account(String accountType)
    {

        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter++;
        custumerAccountsTransaktionsList = new ArrayList<>();

    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public int getAccountID()
    {
        return accountID;
    }

    public void setAccountID(int accountID)
    {
        this.accountID = accountID;
    }

    public void deposit(double depositAmount)
    {
        if (getAccountType().equals("Saving Account"))
        {
            balance = depositAmount + balance;

            custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), depositAmount, getBalance(), "In"));
        } else if (getAccountType().equals("Credit Account"))
        {

             balance = depositAmount + balance;
            custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), depositAmount, getBalance(), "In"));
        }
    }

    public void withdraw(double withdrawAmount)
    {
        if (getAccountType().equals("Saving Account"))
        {
            if (counter == 0)
            {
                balance = balance - withdrawAmount;
                custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), -withdrawAmount, getBalance(), "Ut"));
                counter++;
            } else if (counter > 0)
            {
                balance = balance - withdrawAmount;
                custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), -withdrawAmount, getBalance(), "Ut"));
            }
        } else if (getAccountType().equals("Credit Account"))
        {
            balance = balance - withdrawAmount;
            custumerAccountsTransaktionsList.add(new Transaktions(dateFormat.format(date), getAccountID(), -withdrawAmount, getBalance(), "Ut"));

        }
    }
    public double closeAccount()
    {
        if (getAccountType().equals("Saving Account"))
        {
        balance = balance + (balance*depositIntrest/100);
        }
        else if (getAccountType().equals("Credit Account"))
        {
            
              if(balance >=  0)
        {
            setBalance(balance + (creditAccountPositiveRate * balance/100));//rate is 0.5% for the balance 
        }
        else if(balance <  0)
        {
            balance = balance + (creditAccountLoanRate * balance/100);
        }
        
    }return balance;
        

       
    }
    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate = interestRate;
    }

    public ArrayList<Transaktions> getCustumerAccountsTransaktionsList()
    {
        return custumerAccountsTransaktionsList;
    }
    
    //@Override
    public String toStringClose()
    {
        if (getAccountType().equals("Saving Account"))
        {
            return "Balance " + closeAccount() + ", Rate " + depositIntrest + ", AccountType  " + getAccountType() + ", AccountID " + accountID + "\n";
        }
        else if(getAccountType().equals("Credit Account"))
        {
            if(getBalance() >=  0)
                {
                    return "Balance " + closeAccount() + ", Rate " + creditAccountPositiveRate + ", AccountType  " + getAccountType() + ", AccountID " + accountID + "\n";
                }
             else if(getBalance() <  0)
             {
                 return "Balance " + closeAccount() + ", Rate " + creditAccountLoanRate + ", AccountType  " + getAccountType() + ", AccountID " + accountID + "\n";
             }
        }
        return "";
    }

    @Override
    public String toString()
    {
        return "Balance " + balance + ", Rate " + interestRate + ", AccountType  " + getAccountType() + ", AccountID " + accountID + "\n";
    }

}
