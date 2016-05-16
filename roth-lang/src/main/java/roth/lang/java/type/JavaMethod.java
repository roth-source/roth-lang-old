package roth.lang.java.type;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

import roth.lang.Set;
import roth.lang.java.JavaCode;
import roth.lang.java.JavaWorkspace;
import roth.lang.java._enum.JavaAccess;

public class JavaMethod extends JavaCode
{
	protected Set<JavaAnnotationDeclaration> annotationDeclarations = new Set<>();
	protected JavaAccess access;
	protected boolean _static;
	protected boolean _final;
	protected boolean _abstract;
	protected boolean _synchronized;
	protected boolean _native;
	protected Set<JavaTypeVariable> typeVariables = new Set<>();
	protected JavaDeclaration returnDeclaration;
	protected String name;
	protected Set<JavaParameter> parameters = new Set<>(); 
	protected Set<String> throwsTypes = new Set<>();
	protected Object defaultValue;
	
	protected JavaMethod()
	{
		
	}
	
	public JavaMethod(Method method)
	{
		for(Annotation declaredAnnotation : method.getDeclaredAnnotations())
		{
			annotationDeclarations.add(new JavaAnnotationDeclaration(declaredAnnotation));
		}
		access = JavaAccess.fromModifiers(method.getModifiers());
		_static = Modifier.isStatic(method.getModifiers());
		_final = Modifier.isFinal(method.getModifiers());
		_abstract = Modifier.isAbstract(method.getModifiers());
		_synchronized = Modifier.isSynchronized(method.getModifiers());
		_native = Modifier.isNative(method.getModifiers());
		for(TypeVariable<?> typeVariable : method.getTypeParameters())
		{
			typeVariables.add(new JavaTypeVariable(typeVariable));
		}
		returnDeclaration = JavaDeclaration.get(method.getGenericReturnType());
		name = method.getName();
		for(Parameter parameter : method.getParameters())
		{
			parameters.add(new JavaParameter(parameter));
		}
		defaultValue = method.getDefaultValue();
	}
	
	public boolean isStatic()
	{
		return _static;
	}
	
	public boolean isAbstract()
	{
		return _abstract;
	}
	
	@Override
	public void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	public void toJava(JavaWorkspace workspace, StringBuilder builder)
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
		if(_abstract)
		{
			builder.append(ABSTRACT);
			builder.append(SPACE);
		}
		if(_synchronized)
		{
			builder.append(SYNCHRONIZED);
			builder.append(SPACE);
		}
		if(_native)
		{
			builder.append(NATIVE);
			builder.append(SPACE);
		}
		if(!typeVariables.isEmpty())
		{
			builder.append(LEFT_ANGLE_BRACKET);
			String seperator = BLANK;
			for(JavaTypeVariable typeVariable : typeVariables)
			{
				builder.append(seperator);
				typeVariable.toJava(workspace, builder);
				seperator = COMMA_SEPERATOR;
			}
			builder.append(RIGHT_ANGLE_BRACKET);
			builder.append(SPACE);
		}
		returnDeclaration.toJava(workspace, builder);
		builder.append(SPACE);
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
