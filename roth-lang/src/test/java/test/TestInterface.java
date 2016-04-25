package test;

import roth.lang.java.JavaType;

public interface TestInterface
{
	String TEST = "1234";
	String test2 = "1234";
	
	static <T> String test(String test1, T test2)
	{
		return TEST;
	}
	
	public static void main(String[] args)
	{
		JavaType type = JavaType.get(TestInterface.class);
		System.out.println(type.toJava(null));
	}
	
	String test4();
	
	default String test5()
	{
		return null;
	}
	
}
