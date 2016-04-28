package org.eclipse.php;

import java.io.File;

import org.eclipse.php.internal.core.PHPVersion;
import org.eclipse.php.internal.core.ast.nodes.ASTParser;
import org.eclipse.php.internal.core.ast.nodes.Program;

public class PhpParserTest {

	public static void main(String[] args) throws Exception {
		ASTParser parser = ASTParser.newParser(PHPVersion.PHP4);
		parser.setSource(new File("src/test/resources/language/php4/basic.php"));

		Program ast = parser.createAST();
	}

}
