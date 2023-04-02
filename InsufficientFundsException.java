public class InsufficientFundsException extends Exception {
	public InsufficientFundsException() {
		super("Insufficient Funds");
	}
	public InsufficientFundsException(MonetaryValue amountToWithdraw, MonetaryValue avaliable) {
		super("Insufficient Funds: Tried to Widthraw " + amountToWithdraw +
				" and amount avaliable is " + avaliable);
	}

}
