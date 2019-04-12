package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest{

	public static void main(String[] args){
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));

	}

}
