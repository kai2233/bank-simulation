import java.util.Scanner;
public class SavingsAccount extends BankAccount {
    private static double annualInterestRate;

    public SavingsAccount() {
        this(0,new Name(),new MonetaryValue());
    }

    public SavingsAccount(int accountNumber, Name name, MonetaryValue balance) {
        super(accountNumber,name,balance);
    }

    public SavingsAccount(SavingsAccount old) {
        super(old);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SavingsAccount) {
        	SavingsAccount other = (SavingsAccount)obj;
        	return super.equals(other);
        }else {
        	return false;
        }
    }

    @Override
    public String toString() {
        return "Savings account:\n"+super.toString();
    }
    public MonetaryValue availableAmount() {
    	return balance;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        SavingsAccount.annualInterestRate = annualInterestRate;
    }
    public double getAnnualInterestRate() {
    	return SavingsAccount.annualInterestRate;
    }
    
    public MonetaryValue getMonthlyInterest() {
        double monthlyInterestRate = super.balance.doubleValue()*((SavingsAccount.annualInterestRate/12)/100);
    	return new MonetaryValue(monthlyInterestRate);
    }

    public static SavingsAccount read(Scanner sc) throws NegativeBalanceException{
    	if(!sc.hasNext()) {
        	return null;
        }
    	int accountNumber = sc.nextInt();
    	Name name = Name.read(sc);
    	MonetaryValue balance = MonetaryValue.read(sc);
    	return new SavingsAccount(accountNumber,name,balance);
    }
}

