package tests;

import java.util.ArrayList;
import java.util.Arrays;

public class Array{

	public static void main(String[] args){
		ArrayList<TestObject> list = new ArrayList<TestObject>();
		for(int i = 0; i < 20; i++){
			list.add(new TestObject(i, "Hello"));
		}
		System.out.println(Arrays.toString(list.toArray()));

		list.get(5).setString("Goodbye");
		System.out.println(Arrays.toString(list.toArray()));
	}

}
