package test;

import roth.lang.java.JavaType;

public enum TestEnum
{
	ONE,
	TWO,
	THREE,
	;
	
	public static final String ASDF = "1234";
	
	public static void main(String[] args)
	{
		JavaType type = JavaType.get(TestAnnotationInterface.class);
		//JavaType type = JavaType.get(TestEnum.class);
		System.out.println(type.toJava(null));
	}
	
}
