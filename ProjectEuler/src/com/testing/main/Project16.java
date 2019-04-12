package com.testing.main;

public class Project16 {

	public static void main(String[] args) {
		String num = Double.toString(Math.pow(2,15));
		System.out.println(num);
		int sum = 0;
		for(int i = 0; i < num.length(); i++){
			sum += Integer.parseInt(num.substring(i,i+1));
		}
		System.out.println(sum);
	}

}
