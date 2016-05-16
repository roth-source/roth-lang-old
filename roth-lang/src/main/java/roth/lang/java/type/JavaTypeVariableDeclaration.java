package roth.lang.java.type;

import java.lang.reflect.TypeVariable;

import roth.lang.java.JavaWorkspace;

public class JavaTypeVariableDeclaration extends JavaDeclaration
{
	protected String name;
	
	protected JavaTypeVariableDeclaration()
	{
		
	}
	
	public JavaTypeVariableDeclaration(TypeVariable<?> typeVariable, int dimensions)
	{
		this.name = typeVariable.getName();
		this.dimensions = dimensions;
	}
	
	@Override
	public void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	public void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		builder.append(name);
		for(int i = 0; i < dimensions; i++)
		{
			builder.append(LEFT_BRACKET);
			builder.append(RIGHT_BRACKET);
		}
	}
	
}
