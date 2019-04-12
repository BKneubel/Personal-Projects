package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReaderWriter{

	public ReaderWriter(){
	}

	public <T> String write(T obj, File file){//write method
		try{
			FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(obj);//writes object to file
			o.close();
			f.close();
			return file.getAbsolutePath();//returns the file path

		} catch(FileNotFoundException e){
			return "File not found";

		} catch(IOException e){
			return "Error initializing stream";
		}
	}

	public Object read(File file) throws FileNotFoundException, IOException{
		Object toReturn = null;
		try{
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fi);
			Object obj = (oi.readObject());
			toReturn = obj;
			fi.close();
			oi.close(); //catch potential errors
		} catch(ClassNotFoundException e){
			System.out.println("Class error");
		}
		return toReturn;
	}

}