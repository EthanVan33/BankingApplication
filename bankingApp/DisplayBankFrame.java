package bankingApp;
import java.util.ArrayList;
import java.lang.Number.*;
import java.text.NumberFormat;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.text.NumberFormat;
import java.awt.GridBagLayoutInfo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.color.*;


public class DisplayBankFrame extends JPanel{

    //Global Variables
    String currentUser;
    public int currentAccount;
    public AccountManager bankFrameTest = new AccountManager();

    //loginFrame variables
    private JDialog loginFrame;
    private JFrame mFrame;
    private JLabel welcomeMessage;
    private JLabel login;
    private JLabel userNameLabelL;
    private JLabel passLabelLog;
    private JTextField loginRegStatus;
    private JTextField userInputLog;
    private JTextField psInputLog;
    private JTextField userInputSign;
    private JButton welcomeScreenConfirm;
    private JRadioButton loginButton;
    private JRadioButton registerButton;
    private ButtonGroup homeSelection;
    private NumberFormat dollarFormatter = NumberFormat.getCurrencyInstance();

    //mFrame variables
    private JLabel mPageWelcome;
    private JLabel mPageName;
    private JLabel accountInfoLabel;
    private JLabel overallBalanceLabel;
    private JLabel yourAccounts;
    private JLabel yourCheckingAccounts;
    private JLabel yourSavingsAccounts;
    private JLabel depositWithdrawLabel;
    private JLabel accountNumberDepositWithDrawLabel;
    private JLabel amountDepositWithdrawLabel;
    private JLabel transferLabel;
    private JLabel transferAccountLabelA;
    private JLabel transferAccountLabelB;
    private JLabel transferAmountLabel;
    private JTextField depositWithdrawConfirmation;
    private JTextField transferConfirmMessage;

    private JTextArea accountInfo = new JTextArea();
    private JTextArea accountListChecking;
    private JTextArea accountListSavings;

    private JTextField accountNameEntry;
    private JTextField amountDepositWithdraw;
    private JTextField accountNumberDepositWithdraw;
    private JTextField transferAccountA;
    private JTextField transferAccountB;
    private JTextField transferAmount;

    private JRadioButton depositButton;
    private JRadioButton withdrawButton;
    private ButtonGroup depositWithdraw;
    private JButton depositWithdrawConfirm;
    private JButton transferConfirm;
    private JButton applyInterest;
    private JButton createCheckingsAccount;
    private JButton createSavingsAccount;
    private JButton logoutButton;

    private JPanel depositWithdrawPanel;
    private JPanel transferPanel;
    private JPanel accountInfoPanel;
    private JPanel accountsDisplayPanel;
    Color blck = new Color(1);
    Font titleFont = new Font("Serif", Font.BOLD, 24);
    private String name;


    //DisplayBank Constructor
    public DisplayBankFrame(){
        mFrame = new JFrame("Group3 Bank Program Main Page");
        GridBagConstraints gbc = new GridBagConstraints();
        mFrame.setLayout(new GridBagLayout());
        mFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel accountInfoPanel = new JPanel(new GridBagLayout());
        accountInfoPanel.setBorder(BorderFactory.createLineBorder(blck));

        
        gbc.insets.set(10, 10, 10, 10);
        gbc.gridx = 3;
        gbc.gridy = 0;
        mPageWelcome = new JLabel("Welcome ");
        mPageWelcome.setFont(titleFont);
        mPageWelcome.setSize(150, 150);
        mFrame.add(mPageWelcome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel accountInfoLabel = new JLabel("Account Information");
        accountInfoPanel.add(accountInfoLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        accountInfo.setEditable(false);
        accountInfoPanel.add(accountInfo, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
        mFrame.add(accountInfoPanel,gbc);

        JPanel depositWithdrawPanel = new JPanel(new GridBagLayout());
        
        depositWithdrawPanel.setBorder(BorderFactory.createLineBorder(blck));

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel depositWithdrawLabel = new JLabel("Deposit or Withdraw");
        gbc.gridwidth = 2;
        depositWithdrawPanel.add(depositWithdrawLabel,gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
  
        JLabel accountNumberDepositWithdrawLabel = new JLabel("Account Number:");
        depositWithdrawPanel.add(accountNumberDepositWithdrawLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        accountNumberDepositWithdraw = new JTextField(10);
        accountNumberDepositWithdraw.setEditable(true);
        depositWithdrawPanel.add(accountNumberDepositWithdraw,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BASELINE;
        JLabel amountDepositWithdrawLabel = new JLabel("Amount: ");
        depositWithdrawPanel.add(amountDepositWithdrawLabel,gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        amountDepositWithdraw = new JTextField(10);
        amountDepositWithdraw.setEditable(true);
        amountDepositWithdraw.setText("0");
        depositWithdrawPanel.add(amountDepositWithdraw,gbc);


        depositButton = new JRadioButton("Deposit");
        withdrawButton = new JRadioButton("Withdraw");
        depositButton.setSelected(true);
        depositWithdraw = new ButtonGroup();
        depositWithdraw.add(depositButton);
        depositWithdraw.add(withdrawButton);

        gbc.fill = GridBagConstraints.BASELINE;
        gbc.gridx = 0;
        gbc.gridy = 3;
        depositWithdrawPanel.add(depositButton,gbc);
        
        gbc.gridx = 1;

        depositWithdrawPanel.add(withdrawButton,gbc);

        //checks validity of account number and deposit amount, then passes to depositHelper in AccountManager
        depositWithdrawConfirm = new JButton("Confirm");
        depositWithdrawConfirm.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                double depositWithdrawAmount;
                int acntNum;
                try {depositWithdrawAmount = Double.parseDouble(amountDepositWithdraw.getText());
                }
                catch (NumberFormatException ex){
                    depositWithdrawConfirmation.setText("Invalid Deposit Ammount");
                    depositWithdrawAmount = 0.0;
                }

                try {acntNum = Integer.parseInt(accountNumberDepositWithdraw.getText());
                }
                catch (NumberFormatException ex){
                    depositWithdrawConfirmation.setText("Invalid Account Number");
                    acntNum = -1;
                }
                if(depositWithdrawAmount != -1.0 && acntNum !=-1 && depositButton.isSelected() == true && depositWithdrawAmount > 0.0){
                    if(bankFrameTest.depositHelper(currentAccount, acntNum, depositWithdrawAmount) == true){
                        //System.out.println("Deposit Successful");
                        depositWithdrawConfirmation.setText("Deposit Successful");
                        accountListChecking.setText(bankFrameTest.displayCheckingAccounts(currentAccount));
                        accountListSavings.setText(bankFrameTest.displaySavingsAccounts(currentAccount));
                        amountDepositWithdraw.setText("0");
                        accountNumberDepositWithdraw.setText("");
                    }
                    else{
                        //System.out.println("Deposit Failed");
                        depositWithdrawConfirmation.setText("Deposit Failed");
                    }
                }
                else if(depositWithdrawAmount != -1.0 && acntNum !=-1 && withdrawButton.isSelected() == true && depositWithdrawAmount > 0.0){
                    if(bankFrameTest.withdrawHelper(currentAccount, acntNum, depositWithdrawAmount) == true){
                       // System.out.println("Withdraw Successful");
                        depositWithdrawConfirmation.setText("Withdraw Successful");
                        accountListChecking.setText(bankFrameTest.displayCheckingAccounts(currentAccount));
                        accountListSavings.setText(bankFrameTest.displaySavingsAccounts(currentAccount));
                        amountDepositWithdraw.setText("0");
                        accountNumberDepositWithdraw.setText("");
                    }
                    else{
                        //System.out.println("Withdraw Failed");
                        depositWithdrawConfirmation.setText("Withdraw Failed");
                    }
                }
                else if(depositWithdrawAmount < 0.01){
                    depositWithdrawConfirmation.setText("Please Enter A Positive Number");
                }
                populateMFrame(bankFrameTest.getCurrentAccount(currentAccount));

            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        depositWithdrawPanel.add(depositWithdrawConfirm,gbc);

        depositWithdrawConfirmation = new JTextField("", 20);
        depositWithdrawConfirmation.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        depositWithdrawPanel.add(depositWithdrawConfirmation,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mFrame.add(depositWithdrawPanel,gbc);

        JPanel transferPanel = new JPanel(new GridBagLayout());
        transferPanel.setBorder(BorderFactory.createLineBorder(blck));

        transferLabel = new JLabel("Transfer");
        gbc.gridx = 1;
        gbc.gridy = 0;
        transferPanel.add(transferLabel,gbc);
        gbc.gridwidth = 1;

        transferAccountLabelA = new JLabel("Transfer From Account Number:");  
        gbc.gridx = 0;
        gbc.gridy = 1;
        transferPanel.add(transferAccountLabelA,gbc);

        transferAccountLabelB = new JLabel("Transfer To Account Number:");
        gbc.gridy = 2;
        transferPanel.add(transferAccountLabelB,gbc);

        transferAccountA = new JTextField();
        transferAccountA.setColumns(10);
        transferAccountA.setEditable(true);
        gbc.gridx = 1;
        gbc.gridy = 1;
        transferPanel.add(transferAccountA,gbc);

        transferAccountB = new JTextField();
        transferAccountB.setColumns(10);
        transferAccountB.setEditable(true);
        gbc.gridy = 2;
        transferPanel.add(transferAccountB,gbc);

        transferAmount = new JTextField();
        transferAmount.setText("0");
        transferAmount.setColumns(10);
        transferAmount.setEditable(true);
        gbc.gridy = 3;
        transferPanel.add(transferAmount,gbc);

        transferAmountLabel = new JLabel("Amount To Transfer:");
        gbc.gridx = 0;
        transferPanel.add(transferAmountLabel,gbc);

        transferConfirmMessage = new JTextField(20);
        transferConfirmMessage.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        transferPanel.add(transferConfirmMessage,gbc);

        //Checks validity of account numbers and transfer amount then calls the transferHelper in AccountManager
        transferConfirm = new JButton("Confirm");
        transferConfirm.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                double amountTransfer;
                int acntNumA;
                int acntNumB;
                int indexA;
                int indexB;
                System.out.println("Transfer Initiated");
                try {amountTransfer = Double.parseDouble(transferAmount.getText());
                }
                catch (NumberFormatException ex){
                    //System.out.println("Invalid Deposit Amount");
                    transferConfirmMessage.setText("Invalid Transfer Ammount");
                    amountTransfer = 0.0;
                }

                try {acntNumA = Integer.parseInt(transferAccountA.getText());
                }
                catch (NumberFormatException ex){
                    //System.out.println("Invalid Account Number");
                    transferConfirmMessage.setText("Invalid Account Number");
                    acntNumA = -1;
                }
                
                try {acntNumB = Integer.parseInt(transferAccountB.getText());
                }
                catch (NumberFormatException ex){
                    //System.out.println("Invalid Account Number");
                    transferConfirmMessage.setText("Invalid Account Number");
                    acntNumB = -1;
                }
                //System.out.println("Numbers Collected");
                indexA = bankFrameTest.getAccountIndex(acntNumA);
                indexB = bankFrameTest.getAccountIndex(acntNumB);
                //System.out.println("indices collected");
                
                if(indexA == -1 || indexB == -1){
                    transferConfirmMessage.setText("Invalid Account Number");
                }
                else if(indexA != currentAccount){
                    transferConfirmMessage.setText("You Can Only Transfer From Your Accounts");
                }
                else if(bankFrameTest.validTransferAmount(indexA, acntNumA, amountTransfer) != true){
                    transferConfirmMessage.setText("Insufficient Funds");
                }
                else if(amountTransfer < 0.01){
                    transferConfirmMessage.setText("Please Enter A Positive Amount");
                }
                else{
                    bankFrameTest.withdrawHelper(indexA, acntNumA, amountTransfer);
                    bankFrameTest.depositHelper(indexB, acntNumB, amountTransfer);
                    accountListChecking.setText(bankFrameTest.displayCheckingAccounts(currentAccount));
                    accountListSavings.setText(bankFrameTest.displaySavingsAccounts(currentAccount));
                    transferConfirmMessage.setText("Transfer Successful");
                    transferAmount.setText("");

                    populateMFrame(bankFrameTest.getCurrentAccount(currentAccount));
                }
                //System.out.println("End of Transfer");
            }
        });

        gbc.gridx = 0;
        transferPanel.add(transferConfirm,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mFrame.add(transferPanel, gbc);

        JPanel accountsDisplayPanel = new JPanel(new GridBagLayout());
        
        accountsDisplayPanel.setBorder(BorderFactory.createLineBorder(blck));

        yourAccounts = new JLabel("Your Accounts:");
        yourAccounts.setFont(titleFont);
        gbc.gridx = 1; 
        gbc.gridy = 0;
        accountsDisplayPanel.add(yourAccounts,gbc);

        yourCheckingAccounts = new JLabel("Checking Accounts:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        accountsDisplayPanel.add(yourCheckingAccounts,gbc);

        yourSavingsAccounts = new JLabel("Savings Accounts:");
    
        gbc.gridy = 4;
        accountsDisplayPanel.add(yourSavingsAccounts,gbc);

        accountListChecking = new JTextArea("");
        accountListChecking.setEditable(false);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        accountsDisplayPanel.add(accountListChecking,gbc);


        accountListSavings = new JTextArea("");
        accountListSavings.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        accountsDisplayPanel.add(accountListSavings,gbc);
        gbc.gridwidth = 1;

        

        accountNameEntry = new JTextField("Enter Account Name");
        accountNameEntry.setColumns(15);
        accountNameEntry.setEditable(true);
        gbc.gridx = 0; 
        gbc.gridy = 5;
        accountsDisplayPanel.add(accountNameEntry,gbc);

        //calls Checking account after checking for input validity
        createCheckingsAccount = new JButton("Create Checkings Account");
        createCheckingsAccount.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
               String accountName = accountNameEntry.getText();
                if(accountName != null && !accountName.trim().isEmpty()){
                    bankFrameTest.createCheckingsHelper(accountName, currentAccount);
                    System.out.println("Checkings Account Successfully Created");
                    accountListChecking.setText(bankFrameTest.displayCheckingAccounts(currentAccount));
                }
                else{
                    System.out.println("Please Enter an Account Name");
                }
            }
        });
        //gbc.gridx = 6;
        //gbc.gridy = 7;
        //mFrame.add(createCheckingsAccount,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        accountsDisplayPanel.add(createCheckingsAccount,gbc);

        //call createSavingsHelper in AccountManager after checking for validity
        createSavingsAccount = new JButton("Create Savings Account");
        createSavingsAccount.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
               String accountName = accountNameEntry.getText();
                if(accountName != null && !accountName.trim().isEmpty()){
                    bankFrameTest.createSavingsHelper(accountName, currentAccount);
                    System.out.println("Savings Account Successfully Created");
                    accountListSavings.setText(bankFrameTest.displaySavingsAccounts(currentAccount));
                }
                else{
                    System.out.println("Please Enter an Account Name");
                }
            }
        });
        //gbc.gridx = 7;
        //gbc.gridy = 7;
        //mFrame.add(createSavingsAccount,gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        accountsDisplayPanel.add(createSavingsAccount,gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight =2;
        gbc.fill = GridBagConstraints.VERTICAL;
        mFrame.add(accountsDisplayPanel,gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        //Hides and depopulates main frame, reinitializes login dialogue
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
               mFrame.setVisible(false);
               accountNameEntry.setText("Enter Account Name");
               amountDepositWithdraw.setText("0");
               accountNumberDepositWithdraw.setText("");
                transferAccountA.setText("");
                transferAccountB.setText("");
                transferAmount.setText("0");
                depositWithdrawConfirmation.setText("");
                showLogin();
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 4;
        
        mFrame.add(logoutButton,gbc);


        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //calls writeFile on window closing
        mFrame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e){
                bankFrameTest.writeFile();
            }
            
        });
        
        showLogin();

    }

    //creates login form and passes it to showLogin()
    private JPanel createLoginForm() {

        JPanel loginForm = new JPanel(new GridBagLayout());
        GridBagConstraints layoutConst = new GridBagConstraints();

        welcomeMessage = new JLabel("Welcome to the Group 3 Bank!");
        login = new JLabel("Login/Register");
        userNameLabelL = new JLabel("Account Name");
        passLabelLog = new JLabel("Password");
        
        userInputLog = new JTextField(15);
        userInputLog.setEditable(true);
        userInputLog.setText("");

        psInputLog = new JTextField(15);
        psInputLog.setEditable(true);

        userInputSign = new JTextField(15);
        userInputSign.setEditable(true);

        userInputSign.setEditable(true);

        //checks validity of inputs and either checks for repeated profile name and creates an account or checks inputted strings against stored username/password
        JButton welcomeScreenConfirm = new JButton("Confirm");
        welcomeScreenConfirm.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
            String userInput = userInputLog.getText();
            String psInput = psInputLog.getText();
            int checkIndex;
            if(loginButton.isSelected() == true){
                checkIndex = bankFrameTest.getAccountIndex(userInput);
                if(checkIndex != -1){
                    if(bankFrameTest.checkPassword(psInput, checkIndex)){
                        currentAccount = checkIndex;
                        populateMFrame(bankFrameTest.getCurrentAccount(checkIndex));
                        loginFrame.setVisible(false);
                        userInputLog.setText("");
                        userInputSign.setText("");
                        mFrame.setVisible(true);
                    }
                    else{
                        System.out.println("Incorrect Password");
                        loginRegStatus.setText("Incorrect Password");
                       }
                }
                else{
                    loginRegStatus.setText("Username Not Found");
                    System.out.println("Username not found");
                }
                }
            else{
                if(bankFrameTest.checkAccountName(userInput) == false){
                    bankFrameTest.createIAccount(userInput,psInput);
                    System.out.println("Account Created");
                    loginRegStatus.setText("Account Successfully Created");
                    }
                else{
                    loginRegStatus.setText("Account Name Already in Use");
                    System.out.println("Username Already in Use");
                }
                    bankFrameTest.getAccountInfo();
            }
                
            }
        });

        //initializes new iAccount list in AccountManager
        JButton resetAccountsButton = new JButton("Reset");
        resetAccountsButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
               bankFrameTest.resetAccounts();
            }
        });

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 2;
        layoutConst.gridy = 5;
        loginForm.add(resetAccountsButton, layoutConst);

        setLayout(new GridBagLayout());
        layoutConst = new GridBagConstraints();

        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        loginForm.add(welcomeMessage, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        loginForm.add(login, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        loginForm.add(userNameLabelL, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        loginForm.add(passLabelLog, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        loginForm.add(userInputLog, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        loginForm.add(psInputLog, layoutConst);


        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 2;
        layoutConst.gridy = 2;
        loginForm.add(welcomeScreenConfirm, layoutConst);

        //allows user to swap between login and register actions
        loginButton = new JRadioButton("Login");
        loginButton.setSelected(true);
        registerButton = new JRadioButton("Register");
        homeSelection = new ButtonGroup();
        homeSelection.add(loginButton);
        homeSelection.add(registerButton);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        loginForm.add(loginButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        loginForm.add(registerButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 1;
        layoutConst.gridy = 5;
        loginRegStatus = new JTextField("");
        loginRegStatus.setColumns(15);
        loginRegStatus.setEditable(false);
        loginForm.add(loginRegStatus, layoutConst);

        //Adds functionality to save and exit program on login frame closing
        loginFrame.addWindowListener(new WindowAdapter() { 
    @Override public void windowClosed(WindowEvent e) { 
        bankFrameTest.writeFile();
        System.exit(0);
    }
  });
        return loginForm;
    }

    //creates JDialog and adds loginFrame to it to display
    private void showLogin() {

        loginFrame = new JDialog(mFrame, "Group 3 Bank Program");
        loginFrame.add(createLoginForm(), BorderLayout.CENTER);
        loginFrame.pack();
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    //Uses user information to populate relevant fields in mFrame
    public void populateMFrame(IndividualAccount userIndex){
        mPageWelcome.setText("Welcome " + userIndex.getAccountName() + "!");
        accountInfo.setText("Account Name: " + userIndex.getAccountName() + "\n\nAccount Number: " + userIndex.getAccountNumber() + "\n\nTotal Balance: " + dollarFormatter.format(userIndex.getIndividualBalance()) + "\n");
        //accountInfo.setText("testing testing");
        System.out.println(userIndex.getIndividualBalance());
        accountListChecking.setText(bankFrameTest.displayCheckingAccounts(currentAccount));
        accountListSavings.setText(bankFrameTest.displaySavingsAccounts(currentAccount));
    }
    //JTextArea accountInfo = new JTextArea("Account Name: Test\n\nTotal Balance: $0.00\n\nNumber of Accounts:\nChecking: 0\nSavings: 0");

    //Main - calls DisplayBankFrame Constructor
    public static void main(String[] args) {
    

        DisplayBankFrame testFrame = new DisplayBankFrame();
        //testFrame.bankFrameTest.writeFile();


    }



}