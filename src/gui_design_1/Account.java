/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.util.ArrayList;


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

public class Account
{
    public static int accountIDCounter = 1000;
    
    double balance;
    private double interestRate; 
    private String accountType;
    int accountID;
    int counter = 0;
    private double depositIntrest = 1;
    public ArrayList<Transaktions> custumerAccountsTransaktionsList;
    
   
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
        accountIDCounter ++;
        custumerAccountsTransaktionsList = new ArrayList<>();
    }
    
    public Account(double balance, double interestRate, String accountType, int accountID)
    {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter ++;
        custumerAccountsTransaktionsList = new ArrayList<>();
    }
       

    public Account(double balance, double interestRate,  String accountType)
    {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter ++;
        custumerAccountsTransaktionsList = new ArrayList<>();
    }

    public Account(String accountType, double interestRate)
    {
        
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter ++;
        custumerAccountsTransaktionsList = new ArrayList<>();
       
    }
    
    //This method will be inherited by the credit account, edited
    public Account(String accountType)
    {
        
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountID = accountIDCounter;
        accountIDCounter ++;
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
        if(getAccountType().equals("Saving"))
        {
            balance = depositAmount + balance +(depositIntrest * depositAmount/100) ;
        }
        else if(getAccountType().equals("Credit Account"))
        {
            
            balance = balance + depositAmount;
        }
    }  
   
    public void withdraw(double withdrawAmount)
    {
        if(getAccountType().equals("Saving"))
        {
        if(counter == 0)
            {
            balance = balance - withdrawAmount;
            counter++;
            }
            
        else if(counter>0)
            {
                balance = balance - withdrawAmount;
            }
        }
        else if(getAccountType().equals("Credit Account"))
        {
            balance = balance - withdrawAmount;
            
        }
    }
    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate = interestRate;
    }


    @Override
    public String toString()
    {
        return "Account{" + "balance=" + balance + ", interestRate=" + interestRate + ", accountType = " + getAccountType() + ", accountID=" + accountID + '}';
    }

}