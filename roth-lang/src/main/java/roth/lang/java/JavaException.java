package roth.lang.java;

@SuppressWarnings("serial")
public class JavaException extends RuntimeException
{
	
	public JavaException(String message)
	{
		super(message);
	}
	
	public JavaException(Throwable cause)
	{
		super(cause);
	}
	
	public JavaException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
