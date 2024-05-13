package bankingApp;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.io.Serializable;


public class IndividualAccount implements Serializable{ 

    //Declares class variables
    private String individualName;
    private int individualNumber;
    private int individualBalance;
    private String password;
    private ArrayList<Account> savingsAccountList = new ArrayList<Account>();
    private ArrayList<Account> checkingsAccountList = new ArrayList<Account>();
    private NumberFormat dollarFormatter = NumberFormat.getCurrencyInstance();


    //Constructor taking account holder name and chosen password as inputs, sets balance to 0 and calls setAccoutnNumber() to assign an account number
    public IndividualAccount(String individualName, String password){

        this.individualName = individualName;
        this.password = password;
        individualBalance = 0;
        this.individualNumber = setAccountNumber();

    }

    //Default constructor
    public IndividualAccount(){
        individualName = "Name Required";
        password = "Please Set Password";
        individualNumber = -1;
        individualBalance = 0;
    }

    //Called by constuctors, assigns random 6-digit number
    public int setAccountNumber(){

        boolean newNumber = false;
        int randomNum;
        randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return randomNum;
    }

    

    //Prints account information
    public void print(){

        System.out.println("Account Holder Name: " + individualName + "\nAccount Number: " + individualNumber + "\nCombined Account Balance: $" + individualBalance/100+ "." + individualBalance%100 + "\nAccount Password: " + password);


    }

    //takes a string as an input and sets account password to match
    public void resetPassword(String newPassword){

        this.password = newPassword;

    }

    //call savings account constructor with provided info and adds it to savings account list
    public void createSavings(String accountName){
        SavingsAccount  newAccount = new SavingsAccount(accountName, this.individualNumber);
        savingsAccountList.add(newAccount);
        
    }

    //call checking account constructor with provided info and adds it to checking account list
    public void createCheckings(String accountName){
        CheckingsAccount newAccount = new CheckingsAccount(accountName, this.individualNumber);
        checkingsAccountList.add(newAccount);

    }

    //prints information of all accounts connected to Iaccount
    public void getCreatedAccounts(){
        System.out.println("Checkings Accounts:");
        for(int i = 0; i < checkingsAccountList.size(); i++){
            
            checkingsAccountList.get(i).printAccount();

        }
        System.out.println("Savings Accounts:");
        for(int i = 0; i < savingsAccountList.size(); i++){
            savingsAccountList.get(i).printAccount();
        }
    }

    //returns string of account info for all accounts in checkings account list or string for none yet present
    public String getCheckingAccounts(){
        String checkingAccounts = "";
        if(checkingsAccountList.size() > 0){
            for(int i = 0; i < checkingsAccountList.size(); i++){
                checkingAccounts = checkingAccounts + "Account Name: " + checkingsAccountList.get(i).getAccountName() + "\nAccount Number: " + checkingsAccountList.get(i).getAccountNumber() + "\nAccount Balance: " + dollarFormatter.format((double)checkingsAccountList.get(i).getAccountBalance() / 100) + "\n\n";
            }
            return checkingAccounts;
        }
        else{
            checkingAccounts = "No Checking Accounts Found";
            return checkingAccounts;
        }

    }

    //returns string of account info for all accounts in savings account list or string for none yet present
    public String getSavingsAccounts(){
        String savingsAccounts = "";
        if(savingsAccountList.size() > 0){
            for(int i = 0; i < savingsAccountList.size(); i++){
                savingsAccounts = savingsAccounts + "Account Name: " + savingsAccountList.get(i).getAccountName() + "\nAccount Number: " + savingsAccountList.get(i).getAccountNumber() + "\nAccount Balance: " + dollarFormatter.format((double)savingsAccountList.get(i).getAccountBalance() / 100) + "\n\n";
            }
            return savingsAccounts;
        }
        else{
            savingsAccounts = "No Savings Accounts Found";
            return savingsAccounts;
        }

    }

    //returns account number
    public int getAccountNumber(){
        int num = this.individualNumber;
        return num;
    }

    //returns account name
    public String getAccountName(){
        return this.individualName;
    }

    //returns account password
    public String getAccountPassword(){
        return this.password;
    }

    //returns total balance for all accounts under this IAccount
    public double getIndividualBalance(){
        return (double)this.individualBalance / 100;
    }

    //searches savings/checkings account lists and return index list that specifies which list the account is in and what index it is at
    public int[] findAccount(int accountNumber){
        int[] accountIndex = new int[2];
        accountIndex[0] = -1;
        for(int i = 0; i < checkingsAccountList.size(); i++){
            if(checkingsAccountList.get(i).getAccountNumber() == accountNumber){
                accountIndex[0] = i;
                accountIndex[1] = 1;
            }
        }
        for(int i = 0; i < savingsAccountList.size(); i++){
            if(savingsAccountList.get(i).getAccountNumber() == accountNumber){
                accountIndex[0] = i;
                accountIndex[1] = 2;
            }
        }
        return accountIndex;
    }

    //checks to see if inputed accountNumber is connected to an account
    public boolean checkAccountNumber(int accountNumber){
        boolean accountExists = false;
        for(int i = 0; i < checkingsAccountList.size(); i++){
            if(checkingsAccountList.get(i).getAccountNumber() == accountNumber){
                accountExists = true;
            }
        }
        for(int i = 0; i < savingsAccountList.size(); i++){
            if(savingsAccountList.get(i).getAccountNumber() == accountNumber){
                accountExists = true;
            }
        }
        return accountExists;
    }

    //Calls deposit in checking/savings accounts from list
    public boolean iADepositHelper(int[] accountIndex, double depositAmount){
        boolean depositSuccessful = false;
        if(accountIndex[1] == 1){
            checkingsAccountList.get(accountIndex[0]).deposit(depositAmount);
            depositSuccessful = true;
            individualBalance += depositAmount * 100;
        }
        else if(accountIndex[1] == 2){
            savingsAccountList.get(accountIndex[0]).deposit(depositAmount);
            depositSuccessful = true;
            individualBalance += depositAmount * 100;
        }

        return depositSuccessful;
    }

    //Calls withdraw method in checkings/savings accounts from list
    public boolean iAWithdrawtHelper(int[] accountIndex, double withdrawAmount){
        boolean withdrawSuccessful = false;
        if(accountIndex[1] == 1){
            checkingsAccountList.get(accountIndex[0]).withdraw(withdrawAmount);
            withdrawSuccessful = true;
            individualBalance -= withdrawAmount * 100;
        }
        else if(accountIndex[1] == 2){
            savingsAccountList.get(accountIndex[0]).withdraw(withdrawAmount);
            withdrawSuccessful = true;
            individualBalance -= withdrawAmount * 100;
        }

        return withdrawSuccessful;
    }

    //checks to see if inputed transfer amount is valid for the account the money is being transferred from
    public boolean transferAmountValid(int accountNumber, double transferAmount){
        boolean transferValid = false;
        int intAmount = (int) transferAmount * 100;
        for(int i = 0; i < checkingsAccountList.size(); i++){
            if(accountNumber == checkingsAccountList.get(i).getAccountNumber()){
                if(checkingsAccountList.get(i).getAccountBalance() > intAmount){
                    transferValid = true;
                }
                else{
                    break;
                }
            }
        }
        for(int i = 0; i < savingsAccountList.size(); i++){
            if(accountNumber == savingsAccountList.get(i).getAccountNumber()){
                if(savingsAccountList.get(i).getAccountBalance() > intAmount){
                    transferValid = true;
                }
                else{
                    break;
                }
            }
        }

        return transferValid;
    }


    

}