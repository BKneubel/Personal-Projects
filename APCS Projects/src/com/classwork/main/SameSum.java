package com.classwork.main;

public class SameSum {

	public static void main(String[] args) {
		int[] valA = {13,-22,82,17};
		int[] valB = {25-valA[0],25-valA[1],25-valA[2],25-valA[3]};
		
		System.out.println( "valA: " + valA[0] + " " + valA[1] + " " + valA[2] + " " + valA[3] );
		System.out.println( "valB: " + valB[0] + " " + valB[1] + " " + valB[2] + " " + valB[3] );
		System.out.println( "sum:  " + (valA[0]+valB[0]) + " " + (valA[1]+valB[1]) + " " + (valA[2]+valB[2]) + " " + (valA[3]+valB[3]) );
		
	}

}
