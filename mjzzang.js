// npm install --save crypto-js
const SHA256 = require('crypto-js/sha256')
//created by 김 명 준 


// 코드원칙 : 직관적이고 코딩을 조금만 할 수 있어도 나만의 블록체인 생성 및 구조파악에 도움이 되는 소스를 원칙으로 함!(다만, 이 소스를 이해 한다고 블록체인 다 아는건 절대 아님. 60%는 이해한다고 봐도 무방하다고 봄.!

// 블록의 구조 : 최초의 블록부터 시작해서 바로 앞의 블록에 대한 링크를 가지고 있는 형태 ,여러 노드들이 같은 정보, 장부를 갖는지 확인함

// 블록체인 특징
//    - 블록체인의 구조 : 1. Transaction  2. 전 블록에 대한 hash  3. time
//    - 합의과정 :  1. 분산화된 환경에서 자료 동기화   << 추후 업데이트 할 예정



class Transaction{  // 비트코인 거래 데이타 클래스
  constructor(sender, receiver, amount){
    this.sender = sender;       //송신자
    this.receiver = receiver;   //수신자
    this.amount = amount;       //코인
  };
}


class Block {
  constructor(timestamp, transaction, previousHash = '') {
    this.timestamp = timestamp;                       // 언제 블록이 생성되었는지 
    this.transaction = transaction;                   // transaction , 거래 목록 
    this.previousHash = previousHash;                 // 이전 블록의 hash 블록의 무결성 검증   
    this.hash = this.calculateHash();                 // 해당 블록 hash
    this.nonce = 100;                                
  }

  calculateHash(){ // 블록hash 생성
    return SHA256(this.previousHash + this.timestamp + JSON.stringify(this.transaction) + this.nonce).toString();
  }


  mineBlock(difficulty){ //블록채글연산, difficulty가 높을수록 연산난이도 높아짐 (비트코인 30);
    while(this.hash.substring(0, difficulty) !== Array(difficulty+ 1).join("0")){
      this.nonce++;
      this.hash = this.calculateHash();
    }
    console.log("Block minded " + this.hash);
  }
}


class Blockchain {
  constructor() {
    this.chain = [this.createGenesisBlock()];  // 최초블록 (genesis라 불림)
    this.difficulty = 5;                       // pow 난이도  
    this.pendingTransaction = [];              // 거래가 승인되지 않은 블록 리스트
    this.miningReward = 100;                   // 채굴 보상액(임시 100으로 설정)
  }

  createGenesisBlock(){                        // 최초블록 생성
    return new Block(Date.now(), "GenesisBlock Creadted", 1);
  }

  getLastestBlock(){                            // 블록체인의 마지막 노드
    return this.chain[this.chain.length - 1];
  }

  // addBlock(newBlock){
  //   newBlock.previousHash = this.getLastestBlock().hash;
  //   newBlock.mineBlock(this.difficulty);
  //   this.chain.push(newBlock);
  // }

  minePendingTransaction(miningRewardAddress){   

    let block = new Block(new Date(), this.pendingTransaction);
 
    block.previousHash = this.getLastestBlock().hash;    
    
    block.mineBlock(this.difficulty);                       // 채굴

    console.log("block successffuly mined...(채굴완료)");
    this.chain.push(block);                                 // 기존 블록체인에 새 블록 연결

    this.pendingTransaction = [                             // 초기화 
      new Transaction(null, miningRewardAddress,this.miningReward)
    ];
  }



  createTransaction(transaction){                       // 거래리스트에 거래들을 계속 쌓는 메서드
      this.pendingTransaction.push(transaction);
  }


  getBalanceOfAddress(address){                          // 보상 메서드 
     let balance = 0;
     for(const block of this.chain){
        for(const trans of block.transaction){
          if(trans.sender == address){
            balance-= trans.amount;
          }

          if(trans.receiver==address){
            console.log('receiver ' + trans.receiver);
            balance+=trans.amount;
          }
        }

     }
     return balance;
  }


  isChainValid() {                                          //유효성 검사 메서드 
    for(let i = 1; i < this.chain.length; i++){
      const previousBlock = this.chain[i-1];
      const currentBlock = this.chain[i];

      if(currentBlock.hash !== currentBlock.calculateHash()){
        return false;
      }
      if(currentBlock.previousHash !== previousBlock.hash){
        return false;
      }
    }
    return true;
  }
}



  

let mjcoin = new Blockchain();    
// 거래 리스트
mjcoin.createTransaction(new Transaction('김명준wallet_address','이종훈wallet_address',1000));
mjcoin.createTransaction(new Transaction('이종훈wallet_address','김명준wallet_address',300));
console.log('\n starting the miner111111111111............');
mjcoin.minePendingTransaction('mjminer');  //mining작업
console.log('balance of mj is', mjcoin.getBalanceOfAddress('mjminer')); //해당 miner에게 보상지급 


//거래 리스트 
mjcoin.createTransaction(new Transaction('이상진wallet_address','박영길wallet_address',1000));
mjcoin.createTransaction(new Transaction('이혁기wallet_address','서영석wallet_address',30));
mjcoin.createTransaction(new Transaction('정유상wallet_address','김명준wallet_address',360));
console.log('\n starting the miner22222222222............');
mjcoin.minePendingTransaction('mjminer');
console.log('balance of mj is', mjcoin.getBalanceOfAddress('mjminer'));


//거래 리스트 
mjcoin.createTransaction(new Transaction('김명준wallet_address','이혁기wallet_address',1000));
console.log('\n starting the miner333333333........');
mjcoin.minePendingTransaction('mjminer');
console.log('balance of mj is', mjcoin.getBalanceOfAddress('mjminer'));  // 'miners' 는 보상 받을 주소(채굴자 address);



// 블록체인 전체 정보 출력
console.log(JSON.stringify(mjcoin, null, 4));

const result = mjcoin.isChainValid();     //블록체인 무결성 검증
if(result){
  console.log("정상 체인");
}else{
  console.log("위조된 체인");
}