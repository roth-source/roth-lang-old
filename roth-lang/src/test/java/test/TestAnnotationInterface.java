package test;

public @interface TestAnnotationInterface
{
	String test() default "asdf";
	String test2();
	
}
