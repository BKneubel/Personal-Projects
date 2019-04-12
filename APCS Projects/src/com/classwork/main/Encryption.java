package com.classwork.main;
import java.util.Scanner;

public class Encryption {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Encryption or Decryption? (Type E or D) ");
		String s = reader.nextLine();
		final String alphabet = "abcdefghijklmnopqrstuvwxyz ";
		final String encryption = "!6@7#8<9%0^1&2*3(4)5-_=+][$";
		if (s.equals("E") || s.equals("e")){
			System.out.print("Please enter your string: ");
			String str = reader.nextLine();
			String encrypt = "";
			char output = ' ';
			for (int i = 1; i <= str.length(); i++){
				String input = (str.substring(i-1, i)).toLowerCase();
				int a = alphabet.indexOf(input.charAt(0));
				output = encryption.charAt(a);
				encrypt = encrypt + output;
			}
			System.out.println("Your Encrypted String: " + encrypt);
		}
		else if (s.equals("D") || s.equals("d")){
			System.out.print("Please enter your string: ");
			String str = reader.nextLine();
			String decrypt = "";
			char output = ' ';
			for (int i = 1; i <= str.length(); i++){
				String input = str.substring(i-1,i);
				int a = encryption.indexOf(input.charAt(0));
				output = alphabet.charAt(a);
				decrypt = decrypt + output;
			}
			System.out.println("Your Decrypted String: " + decrypt);
			
		}
		else
			System.out.println("Enter E or D");
	}
}
