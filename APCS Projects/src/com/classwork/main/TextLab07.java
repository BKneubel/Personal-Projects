package com.classwork.main;

import java.util.Arrays;
import java.util.Scanner;

public class TextLab07 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the odd # size of the Magic Square -->   ");
		int num = reader.nextInt();
		int[][] test = new int[num][num];
		
		int edgeY = num - 1;
		int edgeX = num - 1;
		
		
		
		int y = 0;
		int x = num/2;
		
		test[y][x] =  1;
		for(int i = 1; i < 4; i++){
			if (test[y][x] == 0){
				
				if(y-1 >= 0)
				y = y - 1;
				else
				y = edgeY;
				if(x + 1 <= edgeX)
				x = x + 1;
				else
				x = 0;
				test[y][x] = i + 1;
				System.out.println("Iteration: "+i+"X: "+x+"Y: "+y);
				if(i == 2) System.out.println(test[y][x]);
			}
			else{
				y= y+1;
				x=x-1;
				
				System.out.println(i +" "+ x +" " + y);
				
				if(y+1 <= edgeY)
					y = y + 1;
				else
					y = 0;
				System.out.println(i +" "+ x +" " + y);
				test[y][x] = i + 1;
				
			}
			
			
			
			
			
		}
		
		
		
		System.out.println(Arrays.deepToString(test));
	}

}


