package roth.lang.java;

import java.lang.reflect.TypeVariable;

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
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		builder.append(name);
		for(int i = 0; i < dimensions; i++)
		{
			builder.append(LEFT_BRACKET);
			builder.append(RIGHT_BRACKET);
		}
	}
	
}
