package com.classwork.main;

public class Student {
	private String name;
	private int age;

	public Student(String n, int a)
	{
		name = n;
		age = a;
	}

	public void showData()
	{
		System.out.println("Name: " + name);
		System.out.println("Age:  " + age);
		System.out.println();
	}
}
