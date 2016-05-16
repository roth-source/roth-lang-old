package roth.lang.java.type;

import java.lang.reflect.Method;

import roth.lang.Set;
import roth.lang.java.JavaWorkspace;

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
	public void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	public void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
}
