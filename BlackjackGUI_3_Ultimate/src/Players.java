import java.util.ArrayList;


public class Players {
	
	private String name = new String();
	public ArrayList<String> cardHand = new ArrayList<String>();
	private final int INITIAL =1000;
	private int amount;
	
	public Players(){
		amount = INITIAL;
	}
	
	public void setAmount(int cash){
		amount = cash;
	}
	
	public int getAmount(){
		return amount;
	}
	
	
	public void setName( String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public void setCardHand(String c){
		cardHand.add(c);
	}
	
	public ArrayList<String> getCardHand(){
		return cardHand;
	}

	public void resetAmount(){
		amount=INITIAL;
	}
	
	public void resetHand(){
			cardHand.clear();
	}
}
