package com.testing.main;

public class Project12 {

	public static void main(String[] args) {
		int sum = 0;
		int numDivisors;
		for(int i = 1; i < 10000; i++){
			sum += i;
			numDivisors = 0;
			for(int n = 1; n <= sum; n++){
				if(sum % n == 0){
					numDivisors++;
					if(numDivisors > 500){
						System.out.println(i);
						break;
					}
				}
			}
		}
		

	}

}
