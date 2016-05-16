package roth.lang.java.type;

import java.lang.reflect.Method;

public class JavaAttribute extends JavaVariable
{
	
	public JavaAttribute()
	{
		
	}
	
	public JavaAttribute(Method method, Object value)
	{
		declaration = JavaDeclaration.get(method.getReturnType());
		name = method.getName();
	}
	
}
