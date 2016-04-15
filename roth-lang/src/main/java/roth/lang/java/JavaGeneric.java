package roth.lang.java;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public abstract class JavaGeneric extends JavaCode
{
	
	protected JavaGeneric()
	{
		
	}
	
	public static JavaGeneric get(Type type)
	{
		JavaGeneric generic = null;
		if(type instanceof WildcardType)
		{
			generic = new JavaWildcard((WildcardType) type);
		}
		else
		{
			generic = JavaDeclaration.get(type);
		}
		return generic;
	}
	
}
