import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {

	private BufferedWriter output;
	
	public FileWrite(String filename) throws IOException{
		FileWriter fileStream = new FileWriter(filename);
		output = new BufferedWriter(fileStream);
	}
	
	public void writeStrings(String[] strs){
		for (int i=0; i<strs.length; i++){
			writeToFile(strs[i]);
		}
		closeFile();
	}
	
	public void writeToFile(String line){
		try {
			output.write(line);
			output.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeFile(){
		try {
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
