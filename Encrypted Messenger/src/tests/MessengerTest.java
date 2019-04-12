package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import main.Message;
import main.ReaderWriter;

public class MessengerTest{

	private static ReaderWriter IO = new ReaderWriter();
	private static File f = new File("res/TestFile.stored");
	private static Scanner kbReader = new Scanner(System.in);
	private static ArrayList<Message> workingList;

	public static void main(String[] args){
		initialize();
		System.out.println("Initial:");
		System.out.println(Arrays.toString(workingList.toArray()));
		addItems();
		System.out.println("Final:");
		System.out.println(Arrays.toString(workingList.toArray()));
		save();
	}

	public static void initialize(){
		try{
			workingList = (ArrayList<Message>) IO.read(f);
		} catch(Exception e){
			System.out.println("ran");
			workingList = new ArrayList<Message>();
		}
	}

	public static void addItems(){
		adding: for(;;){
			System.out.print("Message Text: ");
			String text = kbReader.nextLine();
			kbReader.nextLine();
			if(text.equals("STOP"))
				break adding;
			workingList.add(new Message(text, null));
		}
	}

	public static void save(){
		f.delete();
		IO.write(workingList, f);
	}
}
