package roth.lang.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

import roth.lang.Set;

public class JavaParameter extends JavaVariable
{
	protected Set<JavaAnnotationDeclaration> annotationDeclarations = new Set<>();
	
	protected JavaParameter()
	{
		
	}
	
	public JavaParameter(Parameter parameter)
	{
		for(Annotation declaredAnnotation : parameter.getDeclaredAnnotations())
		{
			annotationDeclarations.add(new JavaAnnotationDeclaration(declaredAnnotation));
		}
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
