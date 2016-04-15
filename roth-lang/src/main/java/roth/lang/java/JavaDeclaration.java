package roth.lang.java;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public abstract class JavaDeclaration extends JavaGeneric
{
	protected int dimensions;
	
	protected JavaDeclaration()
	{
		
	}
	
	public static JavaDeclaration get(Type type)
	{
		return get(type, 0);
	}
	
	protected static JavaDeclaration get(Type type, int dimensions)
	{
		JavaDeclaration declaration = null;
		if(type instanceof Class)
		{
			declaration = new JavaTypeDeclaration((Class<?>) type);
		}
		else if(type instanceof ParameterizedType)
		{
			declaration = new JavaTypeDeclaration((ParameterizedType) type, dimensions);
		}
		else if(type instanceof TypeVariable)
		{
			declaration = new JavaTypeVariableDeclaration((TypeVariable<?>) type, dimensions);
		}
		else if(type instanceof GenericArrayType)
		{
			declaration = get(((GenericArrayType) type).getGenericComponentType(), ++dimensions);
		}
		return declaration;
	}
	
}
