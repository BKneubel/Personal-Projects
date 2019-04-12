package com.U8.main;
public class Automobile {
	static double mpg = 0;
	static double tank = 0;
	public Automobile(double mpg){this.mpg = mpg;}
	public void fillUp(double gallons){tank += gallons;}
	public void takeTrip(int miles){tank = tank - (miles/mpg);}
	public double reportFuel(){return tank;}
}
