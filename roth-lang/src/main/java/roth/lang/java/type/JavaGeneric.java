package roth.lang.java.type;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import roth.lang.java.JavaCode;

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
