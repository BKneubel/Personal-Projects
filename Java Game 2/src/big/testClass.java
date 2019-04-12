package big;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class testClass {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter("C:\\Users\\bkneu\\Desktop\\textFolder\\out.txt", "UTF-8");
		writer.println("TESTE");
		writer.close();
		

	}

}
