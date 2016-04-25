package roth.lang.java;

import java.lang.reflect.Method;

import roth.lang.Set;

public class JavaAnnotation extends JavaType
{
	protected Set<JavaMethod> attributeMethods = new Set<>();
	
	public JavaAnnotation()
	{
		
	}
	
	public JavaAnnotation(Class<?> _class)
	{
		super(_class);
		for(Method declaredMethod : _class.getDeclaredMethods())
		{
			JavaMethod method = new JavaMethod(declaredMethod);
			attributeMethods.add(method);
		}
	}
	
	@Override
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
}
