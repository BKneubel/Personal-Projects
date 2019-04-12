package com.classwork.main;

import java.util.Scanner;

public class TextLab06 {

	public static void main(String[] args) {
		System.out.println("TextLab06\n");
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the primes upper bound ===>>  ");
		final int MAX = reader.nextInt();
		System.out.println("");
		boolean primes[] = new boolean[MAX];
		for(int i = 2; i < MAX; i++){
			primes[i] = true;
		}
		computePrimes(primes, MAX);
		displayPrimes(primes, MAX);
	}

	public static void computePrimes(boolean[] primes, int MAX) {
		for(int i = 2; i < MAX; i++){
			for (int n = 2; n < i; n++){
				if(i % n == 0){
					primes[i] = false;
				}
			}
		}
	}
	
	public static void displayPrimes(boolean[] primes, int MAX) {
		System.out.println("COMPUTING PRIME NUMBERS\n");
		System.out.println("PRIMES BETWEEN 1 AND " + MAX + "\n");
		for(int i = 2; i < MAX; i++){
			if (primes[i] == true){
				String integer = Integer.toString(i);
				String maximum = Integer.toString(MAX);
				if (integer.length() < (maximum.length()-1)){
					for(int n = 1; n <= (maximum.length()-1) - integer.length(); n++){
						System.out.print("0");
					}
				}
				System.out.print(i + " ");
			}
		}
	}
}
