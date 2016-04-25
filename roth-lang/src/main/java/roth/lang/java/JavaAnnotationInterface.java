package roth.lang.java;

import java.lang.reflect.Method;

import roth.lang.Set;

public class JavaAnnotationInterface extends JavaType
{
	protected Set<JavaMethod> methods = new Set<>();
	
	public JavaAnnotationInterface()
	{
		
	}
	
	public JavaAnnotationInterface(Class<?> _class)
	{
		super(_class);
		for(Method declaredMethod : _class.getDeclaredMethods())
		{
			JavaMethod method = new JavaMethod(declaredMethod);
			methods.add(method);
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
