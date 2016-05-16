package roth.lang.java.type;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

import roth.lang.Set;

public class JavaAnnotationDeclaration
{
	protected String type;
	protected Set<JavaAttribute> attributes = new Set<>();
	
	public JavaAnnotationDeclaration()
	{
		
	}
	
	public JavaAnnotationDeclaration(Annotation annotation)
	{
		Class<?> _class = annotation.annotationType();
		type = _class.getName();
		for(Method declaredMethod : _class.getDeclaredMethods())
		{
			try
			{
				Object value = declaredMethod.invoke(annotation);
				if(!Objects.equals(value, declaredMethod.getDefaultValue()))
				{
					attributes.add(new JavaAttribute(declaredMethod, value));
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
}
