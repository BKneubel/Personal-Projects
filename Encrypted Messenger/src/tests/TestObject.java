package tests;

public class TestObject{

	private String string;
	private int integer;

	public TestObject(int i, String s){
		integer = i;
		string = s;
	}

	public int getInt(){
		return integer;
	}

	public String getString(){
		return string;
	}

	public void setInt(int i){
		integer = i;
	}

	public void setString(String s){
		string = s;
	}

	@Override
	public String toString(){
		return("Integer: " + integer + ", String: " + string);
	}

}
