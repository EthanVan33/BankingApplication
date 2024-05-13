package bankingApp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;

public class AccountManager{

    public ArrayList<IndividualAccount> iAccountList;
    Account currentAccount;
    int currentAccountIndex;
    private List<Integer> accountNumberList = new ArrayList<Integer>();
    
    //Returns account numbers associated with individual account
    public AccountManager(){

        iAccountList = readFile();
    }

    //prints account info for each account in list
    public void getAccountInfo(){
        int i;
        for(i = 0; i < iAccountList.size(); i++){
            iAccountList.get(i).print();
        }

    }
    
    //returns index of account with matching accountName from iAccountList
    public int getAccountIndex(String accountName){
        int accountNum = -1;
        for(int i = 0; i < iAccountList.size(); i++){
            if(iAccountList.get(i).getAccountName().equals(accountName)){
            accountNum = i;
            }
        }
        return accountNum;

    }

    //Finds and returns index of IndividualAccount in iAccountList given a checkings or savings account number
    public int getAccountIndex(int accountNumber){
        int accountNum = accountNumber / 100;
        int accountIndex = -1;
        for(int i = 0; i < iAccountList.size(); i++){
            if(iAccountList.get(i).getAccountNumber() == accountNum){
                if(iAccountList.get(i).checkAccountNumber(accountNumber) == true){
                    accountIndex = i;
                }
            }
        }
        return accountIndex;

    }




    //calls account constructor and adds the created account to master list
    public void createIAccount(String accountName, String accountPassword){
        IndividualAccount newAccount = new IndividualAccount(accountName, accountPassword);
        iAccountList.add(newAccount);
    }

    //removes account from iAccountList, currently unused
    public static void closeAccount(){

        System.out.println("closeAccount in individualAccount is empty");

    }

    //searches  accountNumberList to check if account number already exists
    public boolean checkExistingAccountNumber(int accountNum){
        boolean isRepeat = false;
        if(accountNumberList.size() > 0){
        for(int i = 0; i < accountNumberList.size(); i++ ){
            if(accountNum == accountNumberList.get(i)){
                isRepeat = true;
            }

        }
    }
        return isRepeat;

    }

    //searches iAccountList to check if accountName is already in use
    public boolean checkAccountName(String accountName){
        boolean isRepeat = false;
        for(int i = 0; i < iAccountList.size(); i++ ){
            if(accountName.equals(iAccountList.get(i).getAccountName())){
                isRepeat = true;
            }
        }
        return isRepeat;
    }

    //adds account number to accountNumberList
    public void appendAccountNum(int accountNum){
        accountNumberList.add(accountNum);
    }

    //checks to see if inputted password matches stored password
    public boolean checkPassword(String passInput, int psIndex){
        boolean passwordCorrect = false;
        if(passInput.equals(iAccountList.get(psIndex).getAccountPassword())){
            passwordCorrect = true;
        }
        return passwordCorrect;
    }

    //returns account from provided index
    public IndividualAccount getCurrentAccount(int acIndex){
        return iAccountList.get(acIndex);
    }

    //calls getCheckingAccount method in IndividualAccount for indicated iAccount
    public String displayCheckingAccounts(int index){
        return iAccountList.get(index).getCheckingAccounts();
    }

    //Calls getSavingsAccounts method in Individual account for indicated iAccount
    public String displaySavingsAccounts(int index){
        return iAccountList.get(index).getSavingsAccounts();
    }

    //Calls createCheckings method with provided accountName in Individual account for indicated iAccount
    public void createCheckingsHelper(String accountName, int index){
        iAccountList.get(index).createCheckings(accountName);
    }

    //Calls createSavings method with provided accountName in Individual account for indicated iAccount
    public void createSavingsHelper(String accountName, int index){
        iAccountList.get(index).createSavings(accountName);
    }

    //gets destinationIndex for inputted accountNumber, checks to make sure the destination exists and calls iADepsitHelper in IndividualAccount
    public boolean depositHelper(int index,int accountNumber, double depositAmount){
        boolean depositSuccessful = false;
        int[] destinationIndex;
        destinationIndex = iAccountList.get(index).findAccount(accountNumber);
        if(destinationIndex[0] != -1){
            depositSuccessful = iAccountList.get(index).iADepositHelper(destinationIndex, depositAmount);
        }

        return depositSuccessful;
    }

    //gets destinationIndex for inputted accountNumber, checks to make sure the destination exists and calls iAWithdrawHelper in IndividualAccount
    public boolean withdrawHelper(int index,int accountNumber, double withdrawAmount){
        boolean withdrawSuccessful = false;
        int[] destinationIndex;
        destinationIndex = iAccountList.get(index).findAccount(accountNumber);
        if(destinationIndex[0] != -1){
            withdrawSuccessful = iAccountList.get(index).iAWithdrawtHelper(destinationIndex, withdrawAmount);
        }

        return withdrawSuccessful;
    }

    //currently unused
    public boolean transferHelper(int index, int senderNumber, int recieverNumber, int revieverAccountIndex, double transferAmount){
        boolean transferSuccess = false;
        int recieverAccountIndex;


        return transferSuccess;
    }

    //takes information from gui and passes to transferAmountValid in IndividualAccount returns true if the amount is valid
    public boolean validTransferAmount(int index, int accountNumber, double transferAmount){
        boolean validTransfer = false;
        if(iAccountList.get(index).transferAmountValid(accountNumber, transferAmount) == true){
            validTransfer = true;
        }
        return validTransfer;
    }
		
    
    //reads file
	public ArrayList<IndividualAccount> readFile(){
		ArrayList<IndividualAccount> usersRead = new ArrayList<IndividualAccount>();
		try{
			usersRead = FileManager.serializeDataIn();
			return usersRead;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException b){
			b.printStackTrace();
			}
		return usersRead;
	}


//writes iAccountList to file
	public void writeFile(){
		try{
			FileManager.serializeDataOut(iAccountList);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    //initializes new, empty, iAccountList and ovverides current list
    public void resetAccounts(){
        iAccountList = new ArrayList<IndividualAccount>();
    }
}