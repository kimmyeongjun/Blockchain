package block;

import java.util.ArrayList;
import java.util.Date;

public class Blockchain {
	private static Blockchain instance;
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public Blockchain() {
		blockchain.add(new Block(0, new Date().toString(), "I'am Genesis Block"));
	}

	public static synchronized Blockchain getInstance() {
		if (instance == null) {
			instance = new Blockchain();
		}
		return instance;
	}
	
	public ArrayList<Block> getBlockchain() {
		return blockchain;
	}

	public Block getLastBlock() {
		return Blockchain.blockchain.get(Blockchain.blockchain.size() - 1);
	}

	public void addBlock(Block newBlock) {
		newBlock.previousHash = getLastBlock().currentHash;
		newBlock.mineBlock(5); //difficulty¼³Á¤
		getInstance().getBlockchain().add(newBlock);
		System.out.println("");
	}


	public Boolean isChainValid() {
		for (int i = 1; i < getInstance().getBlockchain().size(); i++) {
			
			Block preBlock = getInstance().getBlockchain().get(i - 1);
			Block curBlock = getInstance().getBlockchain().get(i);
			
			String previousHash = preBlock.currentHash;
			String currentHash = curBlock.previousHash;
			if(!previousHash.equals(currentHash)) {
				return false;
			}
			return true;
		}
		return true;
	}

}
