package lexeranalysis;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Parser {
	private Lexer lex;
	JFrame frame = null;
	JPanel jPanel;
	JButton button;
	JRadioButton button2;
	JTextField field;
	JPanel panel;
	JLabel jLabel;
	FlowLayout flowLayout;
	GridLayout gridLayout;
	Component component;
	int h, l, w, d;
	private SyntaxError error;
	String title;

	public Parser(Lexer lexer) {
		lex = lexer;
	}

	public void run() throws SyntaxError, IOException {

		lex.getNextToken();
		createGUI();

	}

	/***
	 * 
	 * @throws SyntaxError
	 * @throws IOException
	 */
	private void createGUI() throws SyntaxError, IOException {
		while (lex.getNextToken() != Token.EOF) {
			expression();
		}
	}

	/**
	 * @throws IOException
	 * @throws SyntaxError
	 *             *
	 * 
	 */
	public void expression() throws SyntaxError, IOException {
		char choice = lex.getLexeme().charAt(0);
		switch (choice) {

		case 'B':
			title = lex.getLexeme();
			button = new JButton(title);
			break;

		case 'W':
			title = lex.getLexeme();
			frame = new JFrame(title);
			lex.getNextToken();
			h = lex.getValue();
			lex.getNextToken();

			w = lex.getValue();
			frame.setSize(h, w);
			break;

		case 'P':
			panel = new JPanel();
			component = panel;
			break;

		case 'N':
			lex.getNextToken();
			h = lex.getValue();
			lex.getNextToken();

			w = lex.getValue();
			lex.getNextToken();

			l = lex.getValue();
			lex.getNextToken();

			d = lex.getValue();
			break;

		case 'T':
			lex.getNextToken();
			int length = lex.getValue();
			field = new JTextField(length);
			component = field;
			break;

		case 'R':
			title = lex.getLexeme();
			button2 = new JRadioButton(title);

			component = button2;
			break;
		case 'L':
			title = lex.getLexeme();
			jLabel = new JLabel(title);
			component = jLabel;
			break;
		case 'E':
			Token token = lex.getNextToken();
			if (token == Token.PERIOD) {
				frame.setVisible(true);
			}
			if (token == Token.COMMA) {
				frame.add(jPanel);
			}
			break;

		case 'S':
			jPanel.add(component);

			break;
		default:
			new SyntaxError(lex.lineNo(), "Unknown Error");
			break;
		}
	}
}
