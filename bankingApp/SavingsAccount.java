package bankingApp;
import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable{

    //variable declarations
    private int minimumBalance;
    private double interestRate;


    //Constructor sets accountName equal to inputed accountName, sets blance to 0.0, sets account number equal to returned number from assignAccountNumber
    //Sets minimumBalance and interestRate by calling minimumBalance() and setInterestRate()
    public SavingsAccount(String accountName, int iAccountNum){
        this.accountName = accountName;
        this.accountBalance = 0;
        this.accountNumber = assignAccountNumber(iAccountNum);
        minimumBalance();
        setInterestRate();
    }

    //sets interestRate for constructor, currently uses default value, can be removed if only one interest rate value will be used
    public void setInterestRate(){

        interestRate = 1.0;


    }

    //Sets minimumBalance for constructor, currently uses default value, can be removed if only one minimum balance value will be used
    public void minimumBalance(){

        minimumBalance = 100;

    }

    //Overrides Account class print statement, prints SavingsAccount info
    @Override
    public void printAccount(){
        System.out.println("Account Name: " + accountName + "\nAccount Number: " + accountNumber + "\nAccount Balance: $" + accountBalance);
        System.out.println("Interest Rate: " + interestRate + "%\nMinimum Account Balance: $" + accountBalance);
    }


}