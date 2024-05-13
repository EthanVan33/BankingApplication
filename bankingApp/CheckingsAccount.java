package bankingApp;
import java.io.Serializable;

public class CheckingsAccount extends Account implements Serializable{

    private int minimumBalance;

    //constructor, calls assignAccountNumber in Account
    public CheckingsAccount(String accountName, int iAccountNum){

        this.accountName = accountName;
        setMinimumBalance();
        this.accountNumber = assignAccountNumber(iAccountNum);

    }

    public void setMinimumBalance(){

        this.minimumBalance = -1;

    }

    
    public void withdraw(){

        System.out.println("Checkings Account Class Withdraw Function");

    }


}