package com.classwork.main;

public class ReverseOrder {

	public static void main(String[] args) {
		int[] val = {0, 1, 2, 3}; 
	    
	 
	    System.out.println( "Original Array: " + val[0] + " " + val[1] + " " + val[2] + " " + val[3] );
	 
	    int[] temp = {val[3],val[2],val[1],val[0]};  
	 
	    System.out.println( "Reversed Array: " + temp[0] + " " + temp[1] + " " + temp[2] + " " + temp[3] );

	}

}
