package com.U8_2.main;

public class LinearFunction implements LinearFunctionMethods{
	static double slope;
	static double yInt;
	public LinearFunction(double slope,double yInt){
		this.slope = slope;
		this.yInt = yInt;
	}
	public double getSlope(){
		return slope;
	}
	public double getYintercept(){
		return yInt;
	}
	public double getRoot(){
		return ((-1.0) * yInt)/slope;
	}
	public double getYvalue(double x){
		return slope*x + yInt;
		
	}
	public double getXvalue(double y){
		return (y - yInt)/slope;
	}
	
}
