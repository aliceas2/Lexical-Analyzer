package lexeranalysis;

import java.io.IOException;

/**
 *
 * @author Alice Stanford
 * @date 12/1/2017
 */
public class main {
    /**
	 * @param args
	 * @throws IOException
	 * @throws SyntaxError
	 */
	public static void main(String[] args) throws SyntaxError, IOException {
		// TODO Auto-generated method stub
		String fileName = "input.txt";
		Lexer lexer = new Lexer(fileName);
		Parser parser = new Parser(lexer);
		parser.run();
	}

}
