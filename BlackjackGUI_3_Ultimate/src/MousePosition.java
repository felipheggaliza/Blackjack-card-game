
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MousePosition {
	
//	public static Long second=new Long(0);
	public static BufferedWriter  fw;
	public static GUI window;
	
	public static String getCurrentTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat currentSystemTime = new SimpleDateFormat("HH:mm:ss");
		return currentSystemTime.format(cal.getTime());
	}
	
	public static String getInitialTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat currentSystemTime = new SimpleDateFormat("HH.mm.ss");
		return currentSystemTime.format(cal.getTime());
	}
	
	public static synchronized void generateLogFile() throws InterruptedException, IOException{

		
		
		try{

			while(!window.gameIsrunning)
				Thread.sleep(1000);
			fw = new BufferedWriter(new FileWriter( window.player.getName() +"_"+ window.dealer.getName() + "_"+ getInitialTime()+".txt"));

			while(window.gameIsrunning){
			
				String [] turn;
				String name,balance; 
				turn= window.lblBlackjack.getText().split("'", 2);
				
				if(turn[0].equals("Player"))
					name=window.player.getName();
				else
					name=window.dealer.getName();
				
				balance=window.lblPlayersAmount.getText();
				
				fw.write("[" + turn[0] + "]" + 
						"[" + name + "]" + 
						" turn, " +
						"[" + getCurrentTime() + "]"+
						" the mouse was hovering over "+
						getGuiElement()+
						" with "+
						"["+ name +"]'s"+
						"[" +balance + "]"
						);
				fw.newLine();

				System.out.println("[" + turn[0] + "]" + 
							"[" + name + "]" + 
							" turn, " +
							"[" + getCurrentTime() + "]"+
							" the mouse was hovering over "+
							getGuiElement()+
							" with "+
							"["+ name +"]'s"+
							"[" +balance + "]"
							);
				
				
				Thread.sleep(1000);
			}
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			fw.close();
		
	}

	private static String getGuiElement(){
		
		int precision=5;
		
		if( (GUI.xMouse <= (int)window.btnHit.getBounds().getMaxX() +precision &   
				GUI.xMouse >= (int)window.btnHit.getBounds().getMinX() -precision )  &
				(GUI.yMouse <= (int)window.btnHit.getBounds().getMaxY() +precision &
						GUI.yMouse >= (int)window.btnHit.getBounds().getMinY() -precision)
		)
					return "Hit Button";
		
		if( (GUI.xMouse <= (int)window.btnStay.getBounds().getMaxX() +precision &   
				GUI.xMouse >= (int)window.btnStay.getBounds().getMinX() -precision )  &
				(GUI.yMouse <= (int)window.btnStay.getBounds().getMaxY() +precision &
						GUI.yMouse >= (int)window.btnStay.getBounds().getMinY() -precision)
		)
					return "Stay Button";
		
		if( (GUI.xMouse <= (int)window.btnQuit.getBounds().getMaxX() +precision &   
				GUI.xMouse >= (int)window.btnQuit.getBounds().getMinX() -precision )  &
				(GUI.yMouse <= (int)window.btnQuit.getBounds().getMaxY() +precision &
						GUI.yMouse >= (int)window.btnQuit.getBounds().getMinY() -precision)
		)
					return "Quit Button";
		
		return "("+String.valueOf(GUI.xMouse) + "," + String.valueOf(GUI.yMouse)+")";
	}

	
	
}
	




