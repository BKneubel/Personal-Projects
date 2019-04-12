package earlyBuilds;

import java.net.InetAddress;

public class RegexTest{

	public static void main(String[] args){
		try{
			System.out.println(InetAddress.getLocalHost().getHostAddress() + ":" + 46290);
		} catch(Exception e){
		}
	}

}
