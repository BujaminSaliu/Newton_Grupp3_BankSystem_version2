/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;


/**
 *
 * @author SYSJM2 GRUPP 3
 */
public class CreditAccount extends Account 
{
    final static double creditLimit = -5000;
    private final static double CREDITACCOUNTLOANRATE = 7;
    final static private double CREDITACCOUNTPOSITIVERATE=0.5;
    private double interestRate = 0;

    
    public CreditAccount(){
    }

    public CreditAccount( String accountType)
    {
        super(accountType);  //edited
        super.setInterestRate(interestRate);

    }
 
    public double getCREDITACCOUNTPOSITIVERATE()
    {
        return CREDITACCOUNTPOSITIVERATE;
    }


    public double getCREDITACCOUNTLOANRATE() {
        return CREDITACCOUNTLOANRATE;
    }

    @Override
    public void withdraw(double withdrawAmount)
    {

       double sum = super.getBalance()- withdrawAmount;
       if(sum >= creditLimit && sum < 0)
       {
           
           super.setBalance(sum);  
          
           getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), super.getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));
       }
       else if (sum >=0 )
        {
            super.setBalance(sum); 
            getCustumerAccountsTransaktionsList().add(new Transaktions(dateFormat.format(date), super.getAccountID(), -Math.round(withdrawAmount * 100.0) / 100.0, super.getBalance(), "Ut"));

            
        }
        
      else
        {
           
             System.out.println("Kreditgräns -5000");
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
         
        //double division = sum/1.07 = 4672;
        if(super.getBalance() >=  0)
        {
            setBalance(super.getBalance() + (CREDITACCOUNTPOSITIVERATE * super.getBalance()/100));//rate is 0.5% for the balance 
        }
        else if(super.getBalance() <  0)
        {
            setBalance(super.getBalance() + (CREDITACCOUNTLOANRATE * super.getBalance()/100));//rate is 7% for the balance
        }
        return super.getBalance();
    }
    @Override
     public String toStringClose()
     {
      if(super.getBalance() >=  0)
                {
                    return "Saldo: " + closeAccount() + ", Ränta: " + CREDITACCOUNTPOSITIVERATE + "%, Kontotyp:  " + super.getAccountType() + ", KontoID " + super.getAccountID() + "\n";
                }
             else if(super.getBalance() <  0)
             {
                 return "Saldo: " + closeAccount() + ", Ränta: " + CREDITACCOUNTLOANRATE + "%, KontoTyp  " + super.getAccountType() + ", KontoID " + super.getAccountID() + "\n";
             }
      return "";
     }
    
}