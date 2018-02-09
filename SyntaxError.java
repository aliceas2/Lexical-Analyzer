package lexeranalysis;

class SyntaxError extends Exception
{
    
   public SyntaxError(int line, String description)
    {
        super("Line: " + line + " " + description);
    }
}