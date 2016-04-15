package test;

import roth.lang.java.JavaType;

public class Test1
{
	public static final String TEST = "1234";
	static String test2 = "1234";
	
	protected static <T> String test(String test1, T test2)
	{
		return TEST;
	}
	
	public static void main(String[] args)
	{
		JavaType type = JavaType.get(Test1.class);
		System.out.println(type.toJava(null));
	}
	
	protected String test3 = "1234";
	
	public Test1(String test)
	{
		
	}
	
	public String test5()
	{
		return null;
	}
	
}
