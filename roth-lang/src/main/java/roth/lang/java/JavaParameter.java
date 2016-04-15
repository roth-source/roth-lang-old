package roth.lang.java;

import java.lang.reflect.Parameter;

public class JavaParameter extends JavaVariable
{
	
	
	protected JavaParameter()
	{
		
	}
	
	public JavaParameter(Parameter parameter)
	{
		declaration = JavaDeclaration.get(parameter.getParameterizedType());
		name = parameter.getName();
	}
	
	@Override
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		declaration.toJava(workspace, builder);
		builder.append(SPACE);
		builder.append(name);
	}
	
}
