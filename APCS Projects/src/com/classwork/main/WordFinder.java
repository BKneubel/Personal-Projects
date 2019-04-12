package com.classwork.main;

import java.util.Random;

public class WordFinder {
	public static String str = "ben";
	public static String temp = "   ";
	public static String getTemp(char c, int i){
		if(i == 1)temp = c + temp.substring(1);
		if(i == 2)temp = temp.substring(0, 1) + c + temp.substring(2);
		if(i == 3)temp = temp.substring(0,2) + c;
		return temp;
	} 
	
	public static void main(String[] args) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		int i = 0;
		boolean looping = true;
		Random r = new Random();
		while(looping){
			i++;
			int index = r.nextInt(26);
			char c = alpha.charAt(index);
			System.out.print(c);
			if(getTemp(c,i).equals("ben")) break;
			
			if(i==3) i= 0;
			
			
		}
		
		
		
	}

}
