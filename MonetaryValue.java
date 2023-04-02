import java.util.Scanner;
public class MonetaryValue {
	private int cents;

    public MonetaryValue() {
        this.cents=0;
    }

    public MonetaryValue(double amount) {
    	this.cents= (int)(amount*100) ;      
    }

    public MonetaryValue(MonetaryValue old) {
        this.cents=old.cents;
    }

    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof MonetaryValue) {
    		MonetaryValue other = (MonetaryValue)obj;
    		return this.cents == other.cents;
    	}else {
    		return false;
    	}
    }

    @Override
    public String toString() {
    	String result ="$";
    	int dollarsPart, centsPart;
    	dollarsPart = cents/100;
    	centsPart = cents- (dollarsPart*100);
    	result += dollarsPart + ".";
    	if(-10<centsPart && centsPart<10) {
    		result+="0";
    	}
    	if(centsPart<0) {
    		centsPart = -centsPart;
    	}
    	result += centsPart;
    	return result;
    }


    public boolean isLessThan(MonetaryValue other) {
        return this.cents < other.cents;
    }

    public boolean isGreaterThan(MonetaryValue other) {
        return this.cents > other.cents;
    }

    public boolean isNegative() {
    	return this.cents<0;
    }

    public double doubleValue() {
        return cents/100.0;
    }

    public boolean add(MonetaryValue amount) {
        if(amount.isNegative()) {
        	return false;
        }else {
        	this.cents += amount.cents;
        	return true;
        }
    }

    public boolean subtract(MonetaryValue amount) {
    	if(amount.isNegative()) {
        	return false;
        }else {
        	this.cents -= amount.cents;
        	return true;
        }
    }

    public static MonetaryValue read(Scanner sc) {
        if(!sc.hasNext()) {
        	return null;
        }
        return new MonetaryValue(sc.nextDouble());
    }
}
