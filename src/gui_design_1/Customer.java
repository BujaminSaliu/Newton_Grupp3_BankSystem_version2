/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;



import java.util.ArrayList;

public class Customer
{

    private String customerName;
    private long personalNumber;
    private ArrayList<Account> custumerAccountsList;

    /**
     * Default constructor
     */
    public Customer() {  }
    
  
    public Customer(String customerName, long personalNumber)
    {
        this.customerName = customerName;
        this.personalNumber = personalNumber;
        this.custumerAccountsList = new ArrayList<>();
    }



    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public long getPersonalNumber()
    {
        return personalNumber;
    }

    public void setPersonalNumber(long personalNumber)
    {
        this.personalNumber = personalNumber;
    }

    public ArrayList<Account> getCustumerAccountsList()
    {
        return custumerAccountsList;
    }
    

    public void setCustumerAccountsList(ArrayList<Account> custumerAccountsList)
    {
        this.custumerAccountsList = custumerAccountsList;
    }
    
   @Override
    public String toString()
    {
        return "Kundens namn " + customerName + " Personnummer " + personalNumber + "\n";
    }
    
    public String toString1()
    {
        System.out.print("customer Name=" + customerName + ", personal Number=" + personalNumber );
        for(int i = 0; i < custumerAccountsList.size();i++)
        {
            System.out.print(",Account ID ");
            System.out.print(custumerAccountsList.get(i).getAccountID() + ", ");
            System.out.print("Interest rate ");
            System.out.print(custumerAccountsList.get(i).getInterestRate() + ", ");
            System.out.print("Return money(last balance) ");
            System.out.print(custumerAccountsList.get(i).getBalance() + "\n ");
        }
        return "";
    }
    
    public String toString2()
    {
        return customerName + " " + personalNumber + "\n";
    }
 
       
}
