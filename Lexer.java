package lexeranalysis;
// CMSC 330
// Project 1
// Alice Stanford
// March 25, 2017

import java.io.*;

// This class provides the lexical analyzer

class Lexer
{
    private static final int KEYWORDS = 11;
    private StreamTokenizer tokenizer;
    private String punctuation = ",:;.()";
    private Token[] punctuationTokens =
    {
        Token.COMMA, Token.COLON, Token.SEMICOLON, Token.PERIOD, Token.LEFT_PAREN, Token.RIGHT_PAREN
    };


    /**
     * 
     * @param fileName
     * @throws FileNotFoundException
     */
    public Lexer(String fileName) throws FileNotFoundException
    {
        tokenizer = new StreamTokenizer(new FileReader(fileName));
        tokenizer.ordinaryChar('.');
        tokenizer.quoteChar('"');
    }

    
    /***
     * 
     * @return
     * @throws SyntaxError
     * @throws IOException
     */
    public Token getNextToken() throws SyntaxError, IOException
    {
        int token = tokenizer.nextToken();
        switch (token)
        {
            case StreamTokenizer.TT_NUMBER:
                return Token.NUMBER;
            case StreamTokenizer.TT_WORD:
                for (Token aToken : Token.values())
                {
                    if (aToken.ordinal() == KEYWORDS)
                        break;
                    if (aToken.name().equals(tokenizer.sval.toUpperCase()))
                        return aToken;
                }
                throw new SyntaxError(lineNo(), "Invalid token " + getLexeme());
            case StreamTokenizer.TT_EOF:
                return Token.EOF;
            case '"':
                return Token.STRING;
            default:
                for (int i = 0; i < punctuation.length(); i++)
                    if (token == punctuation.charAt(i))
                        return punctuationTokens[i];
        }
        return Token.EOF;
    }

    /**
     * 
     * @return
     */
    public String getLexeme()
    {
        return tokenizer.sval;
    }

    
    /**
     * 
     * @return
     */
    public int getValue()
    {
        return (int) tokenizer.nval;
    }

    
    /**
     * 
     * @return
     */
    public int lineNo()
    {
        return tokenizer.lineno();
    }

}
