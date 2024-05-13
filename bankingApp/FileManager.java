//Will save current information or load already saved information
package bankingApp;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;


public class FileManager{
	public static void serializeDataOut(ArrayList<IndividualAccount> usersToWrite)throws IOException{
		String fileName= "./Test.txt";
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(usersToWrite);
			oos.close();
		}
		catch(IOException eio)
		{
			//do something
			System.out.println("Did not write to file");
			return ;
		}
	}

	public static ArrayList<IndividualAccount> serializeDataIn() throws IOException,ClassNotFoundException{
		String fileName= "Test.txt";
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fin);
		ArrayList<IndividualAccount> inUsers= (ArrayList<IndividualAccount>) ois.readObject();
		ois.close();
		System.out.println(inUsers);
		return inUsers;
	}

}