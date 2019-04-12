package com.classwork.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MortgageIO {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mortgage Payment Program: Student Edition");
		double principal, annualRate,numYears,midStep,monthPayment,totalPayment,numMonth,totalInterest,monthRate,roundPayment,roundedPayment,roundTotalPayment,roundedTotalPayment,
			roundTotalInterest,roundedTotalInterest;
		principal = Double.parseDouble(JOptionPane.showInputDialog(frame,"Enter your Principal Amount","0"));
		annualRate = Double.parseDouble(JOptionPane.showInputDialog(frame,"Enter your Annual Rate","0"));
		numYears = Double.parseDouble(JOptionPane.showInputDialog(frame,"Enter the Number of Years","0"));
		numMonth = numYears * 12;
		monthRate = annualRate/1200;
		midStep = Math.pow((1+monthRate), numMonth);
		monthPayment = ((monthRate * midStep) / (midStep - 1)) * principal;
		totalPayment = monthPayment * numMonth;
		totalInterest = totalPayment - principal;
		roundPayment = (int)(monthPayment * 100);
		roundedPayment = roundPayment / 100;
		roundTotalPayment = (int)(totalPayment * 100);
		roundedTotalPayment = roundTotalPayment / 100;
		roundTotalInterest = (int)(totalInterest * 100);
		roundedTotalInterest = roundTotalInterest / 100;
		JOptionPane.showMessageDialog(frame, "Monthly Payment: $" + roundedPayment);
		JOptionPane.showMessageDialog(frame, "Total Payments: $" + roundedTotalPayment);
		JOptionPane.showMessageDialog(frame, "Total Interest: $" + roundedTotalInterest);
		
	}
}
