import java.util.Scanner;
public class CheckingAccount extends BankAccount {
	private MonetaryValue overdraftLimit;

    public CheckingAccount() {
    	this(0, new Name(), new MonetaryValue(), new MonetaryValue());       
    }

    public CheckingAccount(int accountNumber, Name name, MonetaryValue balance, MonetaryValue overdraftLimit) {
        super(accountNumber,name,balance);
        this.overdraftLimit = overdraftLimit;
    }

    public CheckingAccount(CheckingAccount old) {
    	super(old);
        this.overdraftLimit = old.overdraftLimit;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CheckingAccount) {
        	CheckingAccount other = (CheckingAccount)obj;
        	return super.equals(other) && this.overdraftLimit.equals(other.overdraftLimit);
        }else {
        	return false;
        }
    }

    @Override 
    public String toString() {
        return "Chekcing account:\n"+super.toString();
    }

    public MonetaryValue availableAmount() {
        return new MonetaryValue(balance.doubleValue()+overdraftLimit.doubleValue());
    }
    public MonetaryValue overdraftLimitAmmount() {
    	return new MonetaryValue(overdraftLimit.doubleValue());
    }

    /*public boolean withdraw(MonetaryValue amount) {
    	if(amount.isGreaterThan(availableAmount())||amount.isNegative()) {
    		return false;    	
    	}
    	getBalance().subtract(amount);
    	return true;        
    }*/

    public static CheckingAccount read(Scanner sc) {
    	if(!sc.hasNext()) {
        	return null;
        }
        return new CheckingAccount(sc.nextInt(), new Name(sc.next(),sc.next()),
        		new MonetaryValue(sc.nextDouble()), new MonetaryValue(sc.nextDouble()));
    }
    
}
