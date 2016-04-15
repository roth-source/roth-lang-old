package roth.lang.java;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import roth.lang.List;

public class JavaWildcard extends JavaGeneric
{
	protected List<JavaDeclaration> extendsDeclarations = new List<>();
	protected List<JavaDeclaration> superDeclarations = new List<>();
	
	protected JavaWildcard()
	{
		
	}
	
	public JavaWildcard(WildcardType wildcardType)
	{
		for(Type type : wildcardType.getUpperBounds())
		{
			if(!Object.class.equals(type))
			{
				extendsDeclarations.add(JavaDeclaration.get(type));
			}
		}
		for(Type type : wildcardType.getLowerBounds())
		{
			if(!Object.class.equals(type))
			{
				superDeclarations.add(JavaDeclaration.get(type));
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
		builder.append(QUESTION);
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
		else if(!superDeclarations.isEmpty())
		{
			builder.append(SPACE);
			builder.append(SUPER);
			builder.append(SPACE);
			String seperator = BLANK;
			for(JavaDeclaration superDeclaration : superDeclarations)
			{
				builder.append(seperator);
				superDeclaration.toJava(workspace, builder);
				seperator = SPACE + String.valueOf(AMPERSAND) + SPACE;
			}
		}
	}
	
}
