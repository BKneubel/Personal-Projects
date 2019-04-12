/*Loop Project
 * Brennen Kneubel
 * 11/28/2016
 * Prints a reversal of user inputed name
 */
package com.classwork.main;
import java.util.Scanner;//import scanner

public class LoopProject {//class declaration

	public static void main(String[] args) {//main method
		Scanner reader = new Scanner(System.in);//create new scanner
		System.out.print("Please enter your name: ");//prompt for user input
		String name = reader.next();//declare and initialize first name
		String lastname = reader.next();//declare and initialize last name
		String reverse = "", lastreverse = "";//declare and initialize reversal of first and last names
		for (int i = name.length(); i > 0 ; i--){//run loop that subtracts 1 each time from first name length
			String a = name.substring(i-1,i);//declare and initialize a substring the characters in first name, starting from the back
			reverse = reverse + a;//add to the first name reverse string each time loop runs
		}
		for (int i = lastname.length(); i > 0 ; i--){//run loop that subtracts 1 each time from last name length
			String a = lastname.substring(i-1,i);//declare and initialize a substring the characters in last name, starting from the back
			lastreverse = lastreverse + a;//add to the last name reverse string each time loop runs
		}
		System.out.println(lastreverse.toLowerCase() + " " + reverse.toLowerCase());//print the lower-case reversal of first and last names
	}

}
