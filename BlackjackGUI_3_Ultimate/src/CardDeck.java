import java.util.Random;


public class CardDeck {
	
	private static String [][] gameCards = new String[13][4]; // Game cards 13x4 = 52 cards
	private static int cardIndex_1, cardIndex_2;
	
	public CardDeck(){ //Constructor initializes all cards of the game
		for(int i=0; i<13;i++)
			for(int j=0; j<4;j++){
				switch(i){
					case 0: gameCards[i][j]="Ace";
					break;
					case 1: gameCards[i][j]="2";
					break;
					case 2: gameCards[i][j]="3";
					break;
					case 3: gameCards[i][j]="4";
					break;
					case 4: gameCards[i][j]="5";
					break;
					case 5: gameCards[i][j]="6";
					break;
					case 6: gameCards[i][j]="7";
					break;
					case 7: gameCards[i][j]="8";
					break;
					case 8: gameCards[i][j]="9";
					break;
					case 9: gameCards[i][j]="10";
					break;
					case 10: gameCards[i][j]="Queen";
					break;
					case 11: gameCards[i][j]="Jack";
					break;
					case 12: gameCards[i][j]="King";
					break;
				}
				
				switch(j){
					case 0: gameCards[i][j]=gameCards[i][j].concat(" of Clubs");
					break;
					case 1: gameCards[i][j]=gameCards[i][j].concat(" of Hearts");
					break;
					case 2: gameCards[i][j]=gameCards[i][j].concat(" of Spades");
					break;
					case 3: gameCards[i][j]=gameCards[i][j].concat(" of Diamonds");
					break;
				}
				
		}
		
		shuffle();
	}
	
	public void shuffle(){
		// Reset the indexes and shuffle the cards in the Card Deck
		cardIndex_1 =0; // reset the first index 
		cardIndex_2=0; // reset the first index
		int [] temp= new int[4];
		Random rand = new Random();
		
		
		if(gameCards.length*gameCards[0].length == 52){ // if the Card Deck is full
			for(int k=0;k<100;k++){ //  k expresses the number of times the cards will be shuffled
	
					temp[0]=rand.nextInt(gameCards.length); // generate a random number between 0 and 13
					temp[2]=rand.nextInt(gameCards[0].length); // generate a random number between 0 and 13
					temp[1]=rand.nextInt(gameCards[0].length); // generate a random number between 0 and 4
					temp[3]=rand.nextInt(gameCards[0].length); // generate a random number between 0 and 4

				String box=gameCards[temp[0]][temp[1]];
				gameCards[temp[0]][temp[1]]=gameCards[temp[2]][temp[3]];
				gameCards[temp[2]][temp[3]]=box; // shuffle the cards
				
			}
		}

	}
	

	public String giveCard(){
		//gives one card from the Card Deck
		if(cardIndex_1 == 13){
			cardIndex_1=0;
			cardIndex_2++;	
		}
		return gameCards[cardIndex_1++][cardIndex_2];
	}
	
}
