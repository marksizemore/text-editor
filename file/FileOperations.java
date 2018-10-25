package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class containing methods for performing file operations, including 
 * opening a new document, and saving the current one.
 * 
 * @author Mark Sizemore
 */
public class FileOperations 
{
	/**
	 * Opens the selected file for editing in the GUI window.
	 * 
	 * @param file The file selected by the user to be opened
	 * @return text String representation of the file contents
	 */
	public static String open(File file)
	{
		String text = "";
		StringBuilder sb = new StringBuilder(text);
		try 
		{
			BufferedReader reader =  new BufferedReader(new FileReader(file.getAbsolutePath()));
			
			int input;
			while((input = reader.read()) != -1)
			{
				char c = (char)input;
				sb.append(c);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		text = sb.toString();
		return text;
	}
	
	/**
	 * Saves the document to the location designated by the user.
	 * 
	 * @param document The text to be saved as a new file
	 * @param file the selected file to save
	 */
	public static void save(String document, File file)
	{
		try 
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			bw.write(document);
			bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
