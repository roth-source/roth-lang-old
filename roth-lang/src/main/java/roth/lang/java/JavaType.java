package roth.lang.java;

import java.io.File;
import java.lang.reflect.Modifier;

import roth.lang.List;

public abstract class JavaType extends JavaCode
{
	protected File jarFile;
	protected File sourceFile;
	protected int hashCode;
	protected String _package;
	protected List<String> enclosures = new List<>();
	protected JavaAccess access;
	protected boolean _static;
	protected boolean member;
	protected String name;
	
	protected JavaType()
	{
		
	}
	
	protected JavaType(Class<?> klass)
	{
		hashCode = klass.hashCode();
		_package = klass.getPackage().getName();
		Class<?> enclosingClass = klass;
		while((enclosingClass = enclosingClass.getEnclosingClass()) != null)
		{
			enclosures.addFirst(enclosingClass.getSimpleName());
		}
		access = JavaAccess.fromModifiers(klass.getModifiers());
		_static = Modifier.isStatic(klass.getModifiers());
		member = klass.isMemberClass();
		name = klass.getSimpleName();
	}
	
	public File getJarFile()
	{
		return jarFile;
	}
	
	public File getSourceFile()
	{
		return sourceFile;
	}
	
	public int getHashCode()
	{
		return hashCode;
	}
	
	public String getPackage()
	{
		return _package;
	}
	
	public JavaAccess getAccess()
	{
		return access;
	}
	
	public boolean isStatic()
	{
		return _static;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getFullName()
	{
		StringBuilder builder = new StringBuilder();
		if(_package != null && !_package.isEmpty())
		{
			builder.append(_package);
			builder.append(".");
		}
		for(String enclosure : enclosures)
		{
			builder.append(enclosure);
			builder.append(".");
		}
		builder.append(name);
		return builder.toString();
	}
	
	public JavaType setJarFile(File jarFile)
	{
		this.jarFile = jarFile;
		return this;
	}
	
	public JavaType setSourceFile(File sourceFile)
	{
		this.sourceFile = sourceFile;
		return this;
	}
	
	public JavaType setHashCode(int hashCode)
	{
		this.hashCode = hashCode;
		return this;
	}
	
	public JavaType setPackage(String _package)
	{
		this._package = _package;
		return this;
	}
	
	public JavaType setAccess(JavaAccess access)
	{
		this.access = access;
		return this;
	}
	
	public JavaType setStatic(boolean _static)
	{
		this._static = _static;
		return this;
	}
	
	public JavaType setName(String name)
	{
		this.name = name;
		return this;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_package == null) ? 0 : _package.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(getClass() != obj.getClass())
		{
			return false;
		}
		JavaType other = (JavaType) obj;
		if(_package == null)
		{
			if(other._package != null)
			{
				return false;
			}
		}
		else if(!_package.equals(other._package))
		{
			return false;
		}
		if(name == null)
		{
			if(other.name != null)
			{
				return false;
			}
		}
		else if(!name.equals(other.name))
		{
			return false;
		}
		return true;
	}
	
	public static JavaType get(Class<?> klass)
	{
		JavaType type = null;
		try
		{
			if(klass.isAnnotation())
			{
				type = new JavaAnnotationInterface(klass);
			}
			else if(klass.isEnum())
			{
				type = new JavaEnum(klass);
			}
			else if(klass.isInterface())
			{
				type = new JavaInterface(klass);
			}
			else if(!klass.isAnonymousClass() && !klass.isLocalClass())
			{
				type = new JavaClass(klass);
			}
		}
		catch(Throwable e)
		{
			
		}
		return type;
	}
	
	public static String getFullName(Class<?> klass)
	{
		String fullName = null;
		try
		{
			fullName = klass.getCanonicalName();
		}
		catch(Throwable e)
		{
			
		}
		return fullName;
	}
	
	public static boolean isValid(Class<?> klass)
	{
		boolean valid = false;
		try
		{
			valid = klass.isAnnotation() || klass.isEnum() || klass.isInterface() || (!klass.isAnonymousClass() && !klass.isLocalClass());
		}
		catch(Throwable e)
		{
			
		}
		return valid;
	}
	
}
