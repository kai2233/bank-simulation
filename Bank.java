import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class Bank {
	private HashMap<Integer, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Bank(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        accounts = new HashMap<>();
        while(sc.hasNext()) {
        	try {
            	BankAccount bankAccount;
            	char type =sc.next().toUpperCase().charAt(0);
            	if(type=='C') {
            		bankAccount = CheckingAccount.read(sc);
            	}else {
            		bankAccount = SavingsAccount.read(sc);
            	}
            	if(accounts.containsKey(bankAccount.getAccountNumber())) {
            		throw new DuplicateAccountNumberException(bankAccount.getAccountNumber());
            	}
            	accounts.put(bankAccount.getAccountNumber(),bankAccount);
            }catch (Exception ex) {
            	System.out.println(ex.getMessage());
        		sc.nextLine();
        	}
        }
        sc.close();
    }

    @Override
    public String toString() {
        String result="All accounts in bannk:\n";
        Set<Map.Entry<Integer,BankAccount>> entries = accounts.entrySet();
        for(Map.Entry<Integer,BankAccount>entry : entries) {
        	result+=entry.toString().toString()+"\n";
        }
        return result;
    }

    public boolean contains(int accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public MonetaryValue getBalance(int accountNumber) {
        if(!contains(accountNumber)) {
        	return null;
        }else {
        	return accounts.get(accountNumber).getBalance();
        }
    }

    public String getAccountInfo(int accountNumber) {
    	if(!contains(accountNumber)) {
        	return null;
        }else {
        	String result ="";
        	BankAccount bankAccount = accounts.get(accountNumber);
        	result += bankAccount.toString().toString();
    		result += "\nMonthly interest: ";
        	if(bankAccount instanceof SavingsAccount) {
        		SavingsAccount Saveaccount =(SavingsAccount)bankAccount;
        		result += Saveaccount.getMonthlyInterest();
        	}else {
        		result += "$0.00";
        	}
        	return result;
        }
    }

    public boolean deposit(int accountNumber, MonetaryValue amount) {
    	if(!contains(accountNumber)) {
    		return false;
    	}else {
    		return accounts.get(accountNumber).deposit(amount);   		
    	}
    }

    public boolean withdraw(int accountNumber, MonetaryValue amount) throws InsufficientFundsException {
    	if(!contains(accountNumber)) {
    		return false;
    	}else {   		
    	    return accounts.get(accountNumber).withdraw(amount);
    	}
    }

    public boolean add(BankAccount account) throws DuplicateAccountNumberException {
    	if(contains(account.getAccountNumber())) {
    		throw new DuplicateAccountNumberException(account.getAccountNumber());
    	}else {
    		accounts.put(account.getAccountNumber(), account);
    		return true;
    	}
        
    }

    public boolean remove(int accountNumber) {
    	if(!contains(accountNumber)) {
    		return false;
    	}
    	else {
    		accounts.remove(accountNumber);
    		return true;
    	}
    }
    
    public void printTransactions(int accountNumber) {
    	if(!contains(accountNumber)) {
    		System.out.println("Account doesn't exist");
    	}else {
    		ArrayList<Transaction> transactions = accounts.get(accountNumber).getTransactions();
    		System.out.println("All transactions for account number "+accountNumber);
    		for(Transaction t : transactions) {
    			System.out.println(t.toString());
    		}
    		//for(int i=0; i<transactions.size(); i++){
    		//   Sysetm.out.println(transaction.get(i).toString());
    		//}
    	}
    }
}
