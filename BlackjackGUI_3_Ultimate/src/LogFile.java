import java.awt.MouseInfo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class LogFile {

	public  void writeFile(String filename, String textline) throws IOException {
		
		BufferedWriter  fw = new BufferedWriter(new FileWriter(filename));
		try{
				fw.write(textline);
				fw.newLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
			fw.close();
		
	}		
}

