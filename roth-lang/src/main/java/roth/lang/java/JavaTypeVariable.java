package roth.lang.java;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import roth.lang.List;

public class JavaTypeVariable extends JavaCode
{
	protected String name;
	protected List<JavaDeclaration> extendsDeclarations = new List<>();
	
	protected JavaTypeVariable()
	{
		
	}
	
	public JavaTypeVariable(TypeVariable<?> typeVariable)
	{
		this.name = typeVariable.getName();
		for(Type type : typeVariable.getBounds())
		{
			if(!Object.class.equals(type))
			{
				extendsDeclarations.add(JavaDeclaration.get(type));
			}
		}
	}
	
	@Override
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		builder.append(name);
		if(!extendsDeclarations.isEmpty())
		{
			builder.append(SPACE);
			builder.append(EXTENDS);
			builder.append(SPACE);
			String seperator = BLANK;
			for(JavaDeclaration extendsDeclaration : extendsDeclarations)
			{
				builder.append(seperator);
				extendsDeclaration.toJava(workspace, builder);
				seperator = SPACE + String.valueOf(AMPERSAND) + SPACE;
			}
		}
	}
	
}
