import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class CardsGUI {

	private	ArrayList<ImagePanel> imagePanelPlayer =  new ArrayList<ImagePanel>();
	private	ArrayList<ImagePanel> imagePanelDealer =  new ArrayList<ImagePanel>();
	private	ImagePanel upsideDownCard = new ImagePanel(new ImageIcon("UpsideDown.png").getImage());

	private static int indexPlayer;
	private static int indexDealer;

	private static int xPositionp;
	private static int xPositiond;

	public CardsGUI() {
		indexPlayer=0;
		 indexDealer=0;

		 xPositionp=90;
		xPositiond=90;
	}
	
	public void showUpsideDown(JPanel panel){
		upsideDownCard.setBounds(190, 220, 72, 96);
		panel.add(upsideDownCard);
	}
	
	public void removeUpsideDown(JPanel panel){
		panel.remove(upsideDownCard);
	}
	
	public void showPlayerCard(Players player,JPanel panel){
		System.out.println("Adding " +player.cardHand.get(indexPlayer)+".png");
		imagePanelPlayer.add(indexPlayer,new ImagePanel(new ImageIcon(player.cardHand.get(indexPlayer)+".png").getImage()));

			imagePanelPlayer.get(indexPlayer).setBounds(xPositionp, 90, 72, 96);
			xPositionp=xPositionp+100;

		panel.add(imagePanelPlayer.get(indexPlayer));


		indexPlayer++;
	}
	
	public void showDealerCard(Players dealer,JPanel panel){
		System.out.println("Adding " +dealer.cardHand.get(indexDealer)+".png");
		imagePanelDealer.add(indexDealer,new ImagePanel(new ImageIcon(dealer.cardHand.get(indexDealer)+".png").getImage()));

		imagePanelDealer.get(indexDealer).setBounds(xPositiond, 220, 72, 96);
		xPositiond=xPositiond+100;
	
		panel.add(imagePanelDealer.get(indexDealer));


		indexDealer++;
	}
	

	public void eraseCards(JPanel panel){
		indexPlayer=0;
		 indexDealer=0;

		 xPositionp=90;
		xPositiond=90;
		
		for(int i=0; i<imagePanelPlayer.size();i++)
			panel.remove(imagePanelPlayer.get(i));
			
		for(int i=0; i<imagePanelDealer.size();i++)
			panel.remove(imagePanelDealer.get(i));
		
	}


	
	}


