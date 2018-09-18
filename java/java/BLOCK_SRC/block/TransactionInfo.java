package block;

public class TransactionInfo {
	public String sender;
	public String receiver;
	public int amount;

	public TransactionInfo() {
		super();
	}

	public TransactionInfo(String sender, String receiver, int amount) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

}
