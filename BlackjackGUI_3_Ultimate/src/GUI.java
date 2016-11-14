import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;


public class GUI extends GameMethods implements MouseMotionListener{

	private int screenwidth;
	private int screenwheight;
	public boolean gameIsrunning =false;
	public static int xMouse=0, yMouse=0;
	
	protected JFrame frame;
	
	public JPanel startPanel, gamePanel;
	
	public JTextField textField_1,textField_2;
	
	private JLabel 	lblMain ,
					lblPlayersPoints,
					lblDealersPoints,
					lblEnterPlayersName,
					lblEnterDealersName,
					labelResult;
	
	public JLabel lblBlackjack,lblPlayersName,lblDealersName,lblPlayersAmount,
	lblDealersAmount;
	
	public JButton btnQuit, btnStartGame,btnHit,btnStay,btnNextRound,btnRestartGame;


	protected Players player = new Players();
	protected Players dealer = new Players();
	private CardDeck cards = new CardDeck();
	private CardsGUI cardsGUI = new CardsGUI();
	
	private boolean hide=false;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					window.frame.setResizable(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}
*/
	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenwidth = screenSize.width;
		screenwheight = screenSize.height;
		
		frame = new JFrame();
		frame.setBounds(100, 100, screenwidth / 2, screenwheight / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0,0));
		
	
		initializeStartPanel();
		frame.add(startPanel);
		
		initializeGamePanel();
		frame.add(gamePanel);
		
		gamePanel.addMouseMotionListener(this);

	
		
	}
	
	private void initializeStartPanel(){		
		
		startPanel= new JPanel();
		startPanel.setBackground(new Color(0, 128, 0));
		startPanel.setLayout(null);
		
		lblMain = new JLabel();
		lblMain .setBounds(340, 59, 274, 24);
		lblMain .setText("Welcome to Blackjack!");
		lblMain .setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblMain .setHorizontalAlignment(SwingConstants.CENTER);
		startPanel.add(lblMain );
		
		btnQuit = new JButton("Quit");
		btnQuit.setBounds(screenwidth/2-100, 0, 76, 25);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		startPanel.add(btnQuit);
		
		lblEnterPlayersName = new JLabel("Enter Player's name:");
		lblEnterPlayersName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterPlayersName.setBounds(329, 223, 135, 16);
		startPanel.add(lblEnterPlayersName);
		
		lblEnterDealersName = new JLabel("Enter Dealer's name:");
		lblEnterDealersName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterDealersName.setBounds(329, 274, 146, 16);
		startPanel.add(lblEnterDealersName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(526, 220, 116, 22);
		textField_1.setColumns(10);
		startPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(255, 255, 255));
		textField_2.setBounds(526, 271, 116, 22);
		textField_2.setColumns(10);
		startPanel.add(textField_2);

		btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(421, 345, 116, 22);
		startPanel.add(btnStartGame);
	
		
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Thread t2 =new Thread(new Runnable() {
					public void run() {
						try {
							MousePosition.generateLogFile();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				});
				t2.start();
				
				player.setName(textField_1.getText());
				dealer.setName(textField_2.getText());
				System.out.println(player.getName() + " Registered");
				System.out.println(dealer.getName() + " Registered");
				
				starGame();
				lblPlayersName.setText("Player: "+player.getName());
				lblDealersName.setText("Dealer: "+dealer.getName());
				lblPlayersPoints.setText("Card's sum:" + getCardsValue(player));
				lblDealersPoints.setText("Card's sum:" + getCardsValue(dealer));
				lblPlayersAmount.setText("U$"+ player.getAmount());
				lblDealersAmount.setText("U$"+ dealer.getAmount());
				startPanel.setVisible(false);
				gamePanel.setVisible(true);
				gamePanel.revalidate();
				gamePanel.repaint();
				gameIsrunning=true;
			}

		});
		
		startPanel.setVisible(true);		
	}
	
	public void starGame(){
		// to reset the game
				
		
		for(int k=0;k<2;k++){
			player.setCardHand(cards.giveCard()); // Give one card for the Player
			dealer.setCardHand(cards.giveCard()); // Give one card for the Dealer
		}

		for(int i=0;i<2;i++)
			cardsGUI.showPlayerCard(player, gamePanel);		
		cardsGUI.showDealerCard(dealer, gamePanel);

			cardsGUI.showUpsideDown(gamePanel);
			
		//check if it's a Blackjack
		if(isBlackjack(player)){
			if(!hide){
				gamePanel.add(lblDealersPoints);
				cardsGUI.removeUpsideDown(gamePanel);
				cardsGUI.showDealerCard(dealer, gamePanel);
				hide=true;
			}
			roundOver();

		}
		
	}
	
	private void initializeGamePanel(){

		
		gamePanel= new JPanel();
		gamePanel.setBackground(new Color(0, 128, 0));
		gamePanel.setLayout(null);
				
		//labels
		lblBlackjack = new JLabel("Player's turn!");
		lblBlackjack.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblBlackjack.setBounds(340, 10, 274, 24);
		gamePanel.add(lblBlackjack);
		
		lblPlayersName = new JLabel("Player: "+player.getName());
		lblPlayersName.setBounds(30,50, 135, 16);
		gamePanel.add(lblPlayersName);

		lblDealersName =new JLabel("Dealer: "+dealer.getName());
		lblDealersName.setBounds(30, 200, 146, 16);
		gamePanel.add(lblDealersName);
		
		lblPlayersPoints = new JLabel("Card's sum:" + getCardsValue(player));
		lblPlayersPoints.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayersPoints.setBounds(200,50, 135, 16);
		gamePanel.add(lblPlayersPoints);
		
		lblDealersPoints = new JLabel("Card's sum:" + getCardsValue(dealer));
		lblDealersPoints.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDealersPoints.setBounds(200, 200, 146, 16);
		
		lblPlayersAmount = new JLabel("U$" + player.getAmount());
		lblPlayersAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayersAmount.setBounds(30,70, 135, 16);
		gamePanel.add(lblPlayersAmount);
		
		lblDealersAmount= new JLabel("U$" + dealer.getAmount());
		lblDealersAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDealersAmount.setBounds(30, 220, 146, 16);
		gamePanel.add(lblDealersAmount);
		
		labelResult = new JLabel();
		labelResult.setHorizontalAlignment(SwingConstants.CENTER);
		labelResult.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		labelResult.setBounds(340, 400, 274, 24);
		

		//buttons
		btnQuit = new JButton("Quit");
		btnQuit.setBounds(screenwidth/2-100, 0, 76, 25);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		gamePanel.add(btnQuit);
	
		btnHit = new JButton("Hit!");
		btnHit.setBounds(300,50, 76, 25);
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				if(lblBlackjack.getText().equals("Player's turn!")){
					player.setCardHand(cards.giveCard());	
					cardsGUI.showPlayerCard(player, gamePanel);
					gamePanel.revalidate();
					gamePanel.repaint();
				}

				if(lblBlackjack.getText().equals("Dealer's turn!")){
					dealer.setCardHand(cards.giveCard());
					cardsGUI.showDealerCard(dealer, gamePanel);
					gamePanel.revalidate();
					gamePanel.repaint();
				}

				
				lblPlayersPoints.setText("Card's sum:" + getCardsValue(player));
				lblDealersPoints.setText("Card's sum:" + getCardsValue(dealer));
				
				lblPlayersAmount.setText("U$"+ player.getAmount());
				lblDealersAmount.setText("U$"+ dealer.getAmount());

				
				if(getCardsValue(player) > 21 || getCardsValue(dealer) > 21){
					if(!hide){
						gamePanel.add(lblDealersPoints);
						cardsGUI.removeUpsideDown(gamePanel);
						cardsGUI.showDealerCard(dealer, gamePanel);
						hide=true;
						
					}
					roundOver();
				}
				gamePanel.revalidate();
				gamePanel.repaint();
			}
		});
		gamePanel.add(btnHit);
		
		btnStay = new JButton("Stay!");
		btnStay.setBounds(400,50, 76, 25);
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lblBlackjack.getText().equals("Player's turn!")){					

					lblBlackjack.setText("Dealer's turn!");
					cardsGUI.showDealerCard(dealer, gamePanel);


					btnHit.setBounds(300,190, 76, 25);
					btnStay.setBounds(400,190, 76, 25);
					
					if(isBlackjack(dealer)){
						if(!hide){
							gamePanel.add(lblDealersPoints);
							cardsGUI.removeUpsideDown(gamePanel);
							cardsGUI.showDealerCard(dealer, gamePanel);
							hide=true;
							
						}
						roundOver();
					}
				}
				
				else{

					roundOver();

				}
				if(!hide){
					gamePanel.add(lblDealersPoints);
					hide=true;
					cardsGUI.removeUpsideDown(gamePanel);

				}
				gamePanel.revalidate();
				gamePanel.repaint();
			
			}
		});
		gamePanel.add(btnStay);
		
		btnNextRound = new JButton("Next Round");
		btnNextRound.setBounds(421, 345, 116, 22);
		btnNextRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					restartRound();
			}
		});

	
	btnRestartGame = new JButton("Restart Game");
	btnRestartGame.setBounds(421, 345, 116, 22);
	btnRestartGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("Restarting game...");
			player.resetHand();
			dealer.resetHand();
			
			player.resetAmount();
			dealer.resetAmount();
			
			switchPlayers();
			cards.shuffle();
			
			
			gamePanel.remove(labelResult);

			cardsGUI.eraseCards(gamePanel);

			btnHit.setBounds(300,50, 76, 25);
			btnStay.setBounds(400,50, 76, 25);

			gamePanel.add(btnHit);
			gamePanel.add(btnStay);
			gamePanel.remove(btnRestartGame);
			lblBlackjack.setText("Player's turn!");

			gamePanel.revalidate();
			gamePanel.repaint();
			
			
			gamePanel.setVisible(false);
			startPanel.setVisible(true);
		}
	});

}
	
	public void roundOver(){
		
		gamePanel.remove(btnHit);
		gamePanel.remove(btnStay);
		
		String []temp = getResult(player,dealer).split("!", 2);

		lblBlackjack.setText(temp[0]);
		System.out.println(temp[0] + temp[1] );
	
		
		if(temp[0].equals(player.getName() + " is the winner")){
			reduceAmount(dealer);
			increaseAmount(player);
		}
		else{
			reduceAmount(player);
			increaseAmount(dealer);
		}
		
		lblPlayersAmount.setText("U$"+player.getAmount());
		lblDealersAmount.setText("U$"+dealer.getAmount());
		
	
		gamePanel.add(labelResult);
		
		gamePanel.revalidate();
		gamePanel.repaint();
				
		if(player.getAmount() == 0 | dealer.getAmount() == 0){
			labelResult.setText("Congratulations!");
			gameIsrunning=false;
			gamePanel.add(btnRestartGame);
		
		}
		else{
			labelResult.setText(temp[1]);
			gamePanel.add(btnNextRound);
		
		}
		
	}
	
	
	
	public void switchPlayers(){
		// Receives two objects of type Player and Dealer, and switch their names
		Players n  = player;
		player=dealer;
		dealer=n;
	}
	
	public void restartRound(){
		// to reset the game
		System.out.println("Restarting game...");
		player.resetHand();
		dealer.resetHand();
		switchPlayers();
		cards.shuffle();
		
		gamePanel.remove(labelResult);

		cardsGUI.eraseCards(gamePanel);

		btnHit.setBounds(300,50, 76, 25);
		btnStay.setBounds(400,50, 76, 25);

		gamePanel.add(btnHit);
		gamePanel.add(btnStay);
		gamePanel.remove(btnNextRound);
		lblBlackjack.setText("Player's turn!");

		gamePanel.revalidate();
		gamePanel.repaint();

		for(int k=0;k<2;k++){
			player.setCardHand(cards.giveCard()); // Give one card for the Player
			dealer.setCardHand(cards.giveCard()); // Give one card for the Dealer

		}
		for(int i=0;i<2;i++)
			cardsGUI.showPlayerCard(player, gamePanel);
		cardsGUI.showDealerCard(dealer, gamePanel);
		cardsGUI.showUpsideDown(gamePanel);

		if(hide){
			gamePanel.remove(lblDealersPoints);
			hide=false;
		}
			

		lblPlayersName.setText("Player: "+player.getName());
		lblDealersName.setText("Dealer: "+dealer.getName());

		lblPlayersPoints.setText("Card's sum:" + getCardsValue(player));
		lblDealersPoints.setText("Card's sum:" + getCardsValue(dealer));

		lblPlayersAmount.setText("U$"+ player.getAmount());
		lblDealersAmount.setText("U$"+ dealer.getAmount());
		

		if(isBlackjack(player)){
			if(!hide){
				gamePanel.add(lblDealersPoints);
				cardsGUI.removeUpsideDown(gamePanel);
				cardsGUI.showDealerCard(dealer, gamePanel);
				hide=true;
				
			}
			roundOver();

		}
		
		gamePanel.revalidate();
		gamePanel.repaint();

	}
	
	
	 public void mouseMoved(MouseEvent e) {
		 xMouse=e.getX();
		 yMouse=e.getY();
	     // System.out.println("Mouse moved"+ e.getX() + " , " + e.getY());
	    }
	    
	    public void mouseDragged(MouseEvent e) {
	      //  eventOutput("Mouse dragged", e);
	    }


}
