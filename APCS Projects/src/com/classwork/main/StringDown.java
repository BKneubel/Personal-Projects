/*String Down Project
 * Brennen Kneubel
 * 11/28/2016
 * Accepts user inputed string and prints the string with one character per line
 */
package com.classwork.main;
import java.util.Scanner;//import scanner

public class StringDown {//class declaration
	public static void main(String[] args) {//main method
		Scanner reader = new Scanner(System.in);//create new scanner
		System.out.print("Please enter your string: ");//prompt for user input
		String str = reader.next();//declare and initialize user inputed string
		for (int i = 1; i <= str.length(); i++){//run loop incrementing by 1 until reaching length of user inputed string
			System.out.println(str.substring(i-1,i));//print characters from string starting from first character going to the last
		}
	}	
}
