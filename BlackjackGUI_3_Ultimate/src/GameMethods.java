
public class GameMethods {
	public String getResult(Players p, Players d){

		if((isBlackjack(p) == true) && (isBlackjack(d) == false))
			return p.getName() + " is the winner! Blackjack!";
		if((isBlackjack(d) == true) && (isBlackjack(p) == false))
			return d.getName() + " is the winner! Blackjack!";
		if((getCardsValue(p) > getCardsValue(d)) && (getCardsValue(p) <= 21))
			return p.getName() + " is the winner! By Points!";
		if((getCardsValue(d) > getCardsValue(p)) && (getCardsValue(d) <= 21))
			return d.getName() + " is the winner! By Points!";
		if(getCardsValue(d) > 21)
			return p.getName() + " is the winner! The Dealer Burst!";
		if(getCardsValue(p) > 21)
			return d.getName() + " is the winner! The Player Burst!";
		if((getCardsValue(p) == 21) && (getCardsValue(d) ==21))
			return "It's a Push! Both have Blackjack!";  
		if((getCardsValue(p) == getCardsValue(d)))
			return "It's a Push! Both have the same Points!";  
		return null; // just an default return call
	}

	public boolean isBlackjack(Players p){
		//Receives and object of type Players and if the sum of values is 21 and the player has exactly 2 cards, it's a Blackjack.
		if((getCardsValue(p) == 21) && (p.getCardHand().size() == 2))
			return true;
		else
			return false;
	}


	public int getCardsValue(Players p){
		// Receives a Players object and returns a numerical value representing the sum of the cards 
		int sum=0;
		for(int i=0; i< p.getCardHand().size();i++){
			switch(p.getCardHand().get(i).charAt(0)){
			case 'A': sum=sum+valueofAce(p, sum); // calls a method to compute the value of Ace
			break;
			case '2': sum=sum+2;
			break;
			case '3': sum=sum+3;
			break;
			case '4': sum=sum+4;
			break;
			case '5': sum=sum+5;
			break;
			case '6': sum=sum+6;
			break;
			case '7': sum=sum+7;
			break;
			case '8': sum=sum+8;
			break;
			case '9': sum=sum+9;
			break;
			case '1': sum=sum+10; // 1 == 10, because we are looking just at the first character
			break;
			case 'Q': case 'J': case 'K': sum=sum+10; 
			break;
			}
		}
		return sum;	
	}

	public int valueofAce(Players p, int sum){
		
		//case 1 - There is already one ace in the hand, returns 1
		int numberOfAces=0;
		boolean detectLetter=false;
		
		for(int i=0; i< p.getCardHand().size();i++){ // verify if there is an Ace in the Hand
			if(p.getCardHand().get(i).charAt(0) == 'A')
				numberOfAces++;
			if(p.getCardHand().get(i).charAt(0) == 'Q' | p.getCardHand().get(i).charAt(0) == 'J' | p.getCardHand().get(i).charAt(0) == 'K')
				detectLetter=true;
		}
		
		
		
		if(((sum+11) <= 21) & ((detectLetter == true) && (p.getCardHand().size() > 2) ) == false ) // if I can put and 11 without bust and there is no letter to be summed and I have another card, put it.
			return 11;
		

		if( (numberOfAces > 1) && (sum+11 > 21) && (sum+1 >21)) // if I have two aces and I burst either way. return -10 +1 = -9, which is both Aces are 1.
			return -9;
		
		
		
		return 1;
	}
	
	
	
	public void reduceAmount(Players p){
		p.setAmount(p.getAmount()-100);
	}
	
	public void increaseAmount(Players p){
		p.setAmount(p.getAmount()+100);

	}
	
	public void restartAmount(Players p, Players d){
		p.setAmount(1000);
		d.setAmount(1000);
	}
	

}
