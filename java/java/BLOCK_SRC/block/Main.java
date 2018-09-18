package block;

import java.util.Date;

import util.SALogUtil;
import util.SAMessageUtil;

public class Main {

	public static void main(String[] args) {

		Blockchain chain = Blockchain.getInstance();

		try {

			// transaction : sender , receiver, money
			SALogUtil.fine("mining block1..............................");
			Block block1 = new Block(1, new Date().toString(),
					SAMessageUtil.toJSON(new TransactionInfo("�����", "������", 100000)));
			chain.addBlock(block1);

			SALogUtil.fine("mining block2..............................");
			Block block2 = new Block(2, new Date().toString(),
					SAMessageUtil.toJSON(new TransactionInfo("������", "�����", 200000)));
			chain.addBlock(block2);

			SALogUtil.fine("mining block3..............................");
			Block block3 = new Block(3, new Date().toString(),
					SAMessageUtil.toJSON(new TransactionInfo("�̻���", "�ڿ���", 300000)));
			chain.addBlock(block3);

			SALogUtil.fine("mining block4..............................");
			Block block4 = new Block(4, new Date().toString(),
					SAMessageUtil.toJSON(new TransactionInfo("�ڵ���", "�����", 500000)));
			chain.addBlock(block4);

			SALogUtil.fine("mining block5..............................");
			Block block5 = new Block(5, new Date().toString(),
					SAMessageUtil.toJSON(new TransactionInfo("�����", "�̰���", 800000)));
			chain.addBlock(block5);

			for (Block block : chain.getBlockchain()) {
				SALogUtil.fine(SAMessageUtil.toJSON(block));
			}

			if (chain.isChainValid()) {
				SALogUtil.fine("����ü��");
			} else {
				SALogUtil.fine("������ü��");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
