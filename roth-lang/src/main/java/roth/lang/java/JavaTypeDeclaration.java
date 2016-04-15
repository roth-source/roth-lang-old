package roth.lang.java;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import roth.lang.List;

public class JavaTypeDeclaration extends JavaDeclaration
{
	protected String type;
	protected List<JavaGeneric> generics = new List<>();
	
	protected JavaTypeDeclaration()
	{
		
	}
	
	public JavaTypeDeclaration(Class<?> klass)
	{
		while(klass.isArray())
		{
			klass = klass.getComponentType();
			dimensions++;
		}
		type = klass.getCanonicalName();
	}
	
	public JavaTypeDeclaration(ParameterizedType parameterizedType, int dimensions)
	{
		Type rawType = parameterizedType.getRawType();
		if(rawType instanceof Class)
		{
			this.type = ((Class<?>) rawType).getCanonicalName();
		}
		else
		{
			// expection
		}
		this.dimensions = dimensions;
		for(Type type : parameterizedType.getActualTypeArguments())
		{
			generics.add(JavaGeneric.get(type));
		}
	}
	
	@Override
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		builder.append(type);
		if(!generics.isEmpty())
		{
			builder.append(LEFT_ANGLE_BRACKET);
			String seperator = BLANK;
			for(JavaGeneric generic : generics)
			{
				builder.append(seperator);
				generic.toJava(workspace, builder);
				seperator = COMMA_SEPERATOR;
			}
			builder.append(RIGHT_ANGLE_BRACKET);
		}
		for(int i = 0; i < dimensions; i++)
		{
			builder.append(LEFT_BRACKET);
			builder.append(RIGHT_BRACKET);
		}
	}
	
	public static JavaTypeDeclaration get(Type type)
	{
		JavaTypeDeclaration typeDeclaration = null;
		if(type instanceof Class && !Object.class.equals(type))
		{
			typeDeclaration = new JavaTypeDeclaration((Class<?>) type);
		}
		else if(type instanceof ParameterizedType)
		{
			typeDeclaration = new JavaTypeDeclaration((ParameterizedType) type, 0);
		}
		return typeDeclaration;
	}
	
}
