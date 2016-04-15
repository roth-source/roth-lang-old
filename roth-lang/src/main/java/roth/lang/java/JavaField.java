package roth.lang.java;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class JavaField extends JavaVariable
{
	protected JavaAccess access;
	protected boolean _static;
	protected boolean _volatile;
	protected boolean _transient;
	
	public JavaField()
	{
		
	}
	
	public JavaField(Field field)
	{
		access = JavaAccess.fromModifiers(field.getModifiers());
		_static = Modifier.isStatic(field.getModifiers());
		_final = Modifier.isFinal(field.getModifiers());
		_volatile = Modifier.isVolatile(field.getModifiers());
		_transient = Modifier.isTransient(field.getModifiers());
		declaration = JavaDeclaration.get(field.getGenericType());
		name = field.getName();
	}
	
	public boolean isStatic()
	{
		return _static;
	}
	
	public boolean isFinal()
	{
		return _final;
	}
	
	@Override
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		access.toJava(builder);
		if(_static)
		{
			builder.append(STATIC);
			builder.append(SPACE);
		}
		if(_final)
		{
			builder.append(FINAL);
			builder.append(SPACE);
		}
		if(_volatile)
		{
			builder.append(VOLATILE);
			builder.append(SPACE);
		}
		if(_transient)
		{
			builder.append(TRANSIENT);
			builder.append(SPACE);
		}
		declaration.toJava(workspace, builder);
		builder.append(SPACE);
		builder.append(name);
		builder.append(SEMI_COLON);
	}
	
}
