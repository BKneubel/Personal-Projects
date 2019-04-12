package testing;

import java.util.Scanner;

public class Try3 {

	public static void main(String[] args) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz ";
		char temp = alphabet.charAt((int) (Math.random()*27));
		boolean found = false;
		int newLine = 160;
		int index = 0;
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter String: ");
		String str = reader.nextLine();
		while(found == false){
			System.out.print(temp);
			if (temp == str.charAt(index)){
				if(index == str.length()-1) break;
				index++;
			}
			else index = 0;
			newLine--;
			if (newLine <= 0){
				System.out.print("\n");
				newLine = 160;
			}
			temp = alphabet.charAt((int) (Math.random()*27));
		}
	}
}
