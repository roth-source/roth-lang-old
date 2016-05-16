package roth.lang.java.statement;

import roth.lang.List;
import roth.lang.java.expression.JavaExpression;

public class JavaSwitch extends JavaStatement
{
	protected JavaExpression selector;
	protected List<JavaCase> cases = new List<>();
	
}
