package roth.lang.java.statement;

import roth.lang.List;
import roth.lang.java.expression.JavaExpression;

public class JavaIf extends JavaStatement
{
	protected JavaExpression condition;
	protected JavaBlock ifBlock;
	protected List<JavaElseIf> elseIfs = new List<>();
	protected JavaBlock elseBlock;
	
}
