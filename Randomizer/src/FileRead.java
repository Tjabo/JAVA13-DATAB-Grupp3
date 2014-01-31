import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

	BufferedReader input;
	FileReader readStream;

	public FileRead(String filename) throws FileNotFoundException {
		readStream = new FileReader(filename);
		input = new BufferedReader(readStream);
	}

	public String readFile() {
		String currentLine = "";
		currentLine = readFromFile();
		
		while (currentLine != null) {
			currentLine += currentLine + System.lineSeparator();
			currentLine = readFromFile();
		}		
		return currentLine;
	}

	public String readFromFile() {
		try {			
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	public void close() {
		try {
			input.close();
			readStream.close();
		} catch (IOException e) {
			System.out.println("Shit happens! " + e.getMessage());
			e.printStackTrace();
		}
	}

}
