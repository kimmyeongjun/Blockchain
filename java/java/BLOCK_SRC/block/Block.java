package block;

import util.SAHashUtil;
import util.SAHexUtil;
import util.SALogUtil;

public class Block {
	public int index;
	public String timestamp;
	public String transaction; //	json
	public String previousHash;
	public String currentHash;
	public int nonce;

	public Block() {
		super();
	}

	public Block(int index, String timestamp, String transaction) {
		super();
		this.index = index;
		this.timestamp = timestamp;
		this.transaction = transaction;
		this.nonce = 1;
		this.previousHash = "";
		this.currentHash = createHash(index, timestamp, transaction, previousHash, nonce);
	}
	
	public Block(int index, String timestamp, String transaction, String previousHash) {
		super();
		this.index = index;
		this.timestamp = timestamp;
		this.previousHash = previousHash;
		this.nonce = 1;
		this.currentHash = createHash(index, timestamp, transaction, previousHash, nonce);
	}

	public String createHash(int index, String timestamp, String transaction, String previousHash,	int nonce) {
		String info = new String((index + timestamp + transaction + previousHash + nonce).toString());
		
		String result = "";
		try {
			result = SAHexUtil.byteArrToHexString(SAHashUtil.sha256(info.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void mineBlock(int difficulty) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < difficulty; i++) {
			sb.append("0");
		}
		
		while (!this.currentHash.substring(0, difficulty).equals(sb.toString())) {
					this.nonce++;
			this.currentHash = createHash(difficulty, this.timestamp, this.transaction, this.previousHash,this.nonce);
		}
		SALogUtil.fine("block mined :: " + this.currentHash);
	}

}
