import java.util.Scanner;
public class Name {
	private String first;
	private String last;
	
    public Name() {
    	this("first","last");
    }

    public Name(String first, String last) {
        this.first=first;
        this.last=last;
    }

    public Name(Name old) {
    	 this.first=old.first;
    	 this.last=old.last;
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Name) {
    		Name other = (Name)obj;
    		return this.first.equals(other.first) && this.last.equals(other.last);
    	}else {
    		return false;
    	}
    }

    @Override
    public String toString() {
    	return first.toString()+" "+last.toString();
    }

    public static Name read(Scanner sc) {
    	if(!sc.hasNext()) {
    		return null;
    	}
    	return new Name(sc.next(),sc.next());
    }

}
