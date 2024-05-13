package bankingApp;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.Serializable;


public class Account implements Serializable{

    //Declares class variables
    protected int accountNumber;
    protected String accountName;
    protected int accountBalance;
    protected int indvAccountNumber;

    //Constructor assigns inputed accountName to accountName, sets balance to 0 and sets accountNumber equal to returned value from assignAccountNumber()
    public Account(String accountName, int indvAccountNumber){
        this.accountName = accountName;
        this.accountBalance = 0;
        this.accountNumber = assignAccountNumber(indvAccountNumber);
        this.indvAccountNumber = indvAccountNumber;


    }

    //default constructor
    public Account(){
        accountName = "Missing Account Name";
        accountBalance = 0;
        //accountNumber = assignAccountNumber();
    }

    //Sets accountname equal to inputted string
    public void setAccountname(String newName){
        this.accountName = newName;
    }

    //Takes an accountName sting as an argument and calls assignAccountNumber then passes both to the CheckingsAccount constructor
    public void createCheckings(String accountName){
        int savingsAccountNumber = assignAccountNumber(this.accountNumber);
        System.out.println("createCheckings in Account is empty");

    }

    //returns generated account number
    protected int assignAccountNumber(int indvAccountNum){
        boolean newNumber = false;
        int randomNum;
        int iAccountNum = indvAccountNum;
        randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        iAccountNum = iAccountNum * 100 + randomNum;

        return iAccountNum;
    }

    //prints account info for testing purposes
    public void printAccount(){

        System.out.println("Account Name: " + accountName + "\nAccount Number: " + accountNumber + "\nAccount Balance: $" + accountBalance);


    }



    //Takes a double as input and subtracts it from the current account balance, halts process if withdraw amount is negative or makes the account balance negative, otherwise updates and prints new account balance
    public void withdraw(double withdrawAmount){
        System.out.println("Current Balance: $" + this.accountBalance + "\nAmount to withdrawl: $" + withdrawAmount);
        int convertedAmount = (int)(withdrawAmount * 100);
        int newBalance = this.accountBalance - convertedAmount;
        if (newBalance < 0.0){
            System.out.println("You do not have enough money in your account to withdraw that amount");
        }

            else{

                if( withdrawAmount < 0){
                    System.out.println("Invalid withdraw amount, please try again");

                }
                else{
                    System.out.println("New Balance: $" + newBalance);
                    this.accountBalance = newBalance;
                }
            }

    }

    //takes double depositAmount, prints the current balance and amount to be deposited, prints new balance
    public void deposit(double depositAmount){

        System.out.println("Current Balance: $" + this.accountBalance + "\nAmount to deposit: $" + depositAmount);
        int convertedAmount = (int) (depositAmount * 100);
        int newBalance = this.accountBalance + convertedAmount;
        this.accountBalance = newBalance;
        double convertedNewBalance = (double)newBalance; 
        convertedNewBalance = convertedNewBalance / 100;
        System.out.println("New Balance: $" + (double) newBalance / 100);

    }

    //Transfers balance between two accounts
    public static void transferBalance(){

        System.out.println("transferBalance in Account is empty");

    }

    //returns account name
    public String getAccountName(){
        return this.accountName;
    }

    //returns account number
    public int getAccountNumber(){
        return this.accountNumber;
    }

    //returns account balance
    public int getAccountBalance(){
        return this.accountBalance;
    }

}