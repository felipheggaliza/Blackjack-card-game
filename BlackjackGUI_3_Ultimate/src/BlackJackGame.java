import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class BlackJackGame extends MousePosition{

	
public static void main(String[] args) {
	
	
	
		Thread t1 =new Thread(new Runnable() {
			public void run() {
				try {
					window = new GUI();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
	
	}


}
