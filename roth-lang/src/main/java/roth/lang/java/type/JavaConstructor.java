package roth.lang.java.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import roth.lang.Set;
import roth.lang.java.JavaCode;
import roth.lang.java.JavaWorkspace;
import roth.lang.java._enum.JavaAccess;

public class JavaConstructor extends JavaCode
{
	protected JavaAccess access;
	protected Set<JavaParameter> parameters = new Set<>();
	
	protected JavaConstructor()
	{
		
	}
	
	public JavaConstructor(Constructor<?> constructor)
	{
		access = JavaAccess.fromModifiers(constructor.getModifiers());
		for(Parameter parameter : constructor.getParameters())
		{
			parameters.add(new JavaParameter(parameter));
		}
	}
	
	@Override
	public void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	public void toJava(JavaWorkspace workspace, StringBuilder builder, String name)
	{
		access.toJava(builder);
		builder.append(name);
		builder.append(LEFT_PAREN);
		String seperator = BLANK;
		for(JavaParameter parameter : parameters)
		{
			builder.append(seperator);
			parameter.toJava(workspace, builder);
			seperator = COMMA_SEPERATOR;
		}
		builder.append(RIGHT_PAREN);
		builder.append(NEW_LINE);
		builder.append(TAB);
		builder.append(LEFT_BRACE);
		builder.append(NEW_LINE);
		builder.append(TAB);
		builder.append(NEW_LINE);
		builder.append(TAB);
		builder.append(RIGHT_BRACE);
	}
	
}
