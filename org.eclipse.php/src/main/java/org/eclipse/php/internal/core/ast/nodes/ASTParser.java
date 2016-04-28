/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.internal.core.ast.nodes;

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;
import java_cup.runtime.lr_parser;

import org.eclipse.php.internal.core.PHPVersion;

/**
 * A PHP language parser for creating abstract syntax trees (ASTs).
 * <p>
 * Example: Create basic AST from source string
 * 
 * <pre>
 * String source = ...;
 * Program program = ASTParser.parse(source);
 * </pre>
 */
public class ASTParser {

	// version tags
	private static final Reader EMPTY_STRING_READER = new StringReader(""); //$NON-NLS-1$

	/**
	 * THREAD SAFE AST PARSER STARTS HERE
	 */
	private final AST ast;

	private ASTParser(Reader reader, PHPVersion phpVersion, boolean useASPTags,
			boolean useShortTags) throws IOException {

		this.ast = new AST(reader, phpVersion, useASPTags, useShortTags);
		this.ast.setDefaultNodeFlag(ASTNode.ORIGINAL);

	}

	/**
	 * Factory methods for ASTParser
	 */
	public static ASTParser newParser(PHPVersion version, boolean useShortTags) {
		try {
			return new ASTParser(new StringReader(""), version, false, //$NON-NLS-1$
					useShortTags);
		} catch (IOException e) {
			assert false;
			// Since we use empty reader we cannot have an IOException here
			return null;
		}

	}

	/**
	 * This operation creates an abstract syntax tree for the given AST Factory
	 * 
	 * @param progressMonitor
	 * @return Program that represents the equivalent AST
	 * @throws Exception
	 *             - for exception occurs on the parsing step
	 */
	public Program createAST() throws Exception {

		final Scanner lexer = this.ast.lexer();
		final lr_parser phpParser = this.ast.parser();

		phpParser.setScanner(lexer);

		final Symbol symbol = phpParser.parse();

		if (symbol == null || !(symbol.value instanceof Program)) {
			return null;
		}
		Program p = (Program) symbol.value;
		AST ast = p.getAST();

		// now reset the ast default node flag back to differntate between
		// original nodes
		ast.setDefaultNodeFlag(0);
		// Set the original modification count to the count after the creation
		// of the Program.
		// This is important to allow the AST rewriting.
		ast.setOriginalModificationCount(ast.modificationCount());
		return p;
	}

	/**
	 * Factory methods for ASTParser
	 */
	public static ASTParser newParser(PHPVersion version) {
		return newParser(version, true);
	}

	/**
	 * Set the raw source that will be used on parsing
	 * 
	 * @throws IOException
	 */
	public void setSource(char[] source) throws IOException {
		final CharArrayReader charArrayReader = new CharArrayReader(source);
		setSource(charArrayReader);
	}

	public void setSource(InputStream source) throws IOException {

		InputStreamReader reader = new InputStreamReader(source);
		this.ast.setSource(reader);
	}

	public void setSource(File source) throws IOException {
		FileInputStream input = new FileInputStream(source);
		InputStreamReader reader = new InputStreamReader(input);
		this.ast.setSource(reader);
	}

	/**
	 * Set source of the parser
	 * 
	 * @throws IOException
	 */
	public void setSource(Reader source) throws IOException {
		this.ast.setSource(source);
	}

	/********************************************************************************
	 * NOT THREAD SAFE IMPLEMENTATION STARTS HERE
	 *********************************************************************************/
	// php 5.3 analysis
	private static org.eclipse.php.internal.core.ast.scanner.php53.PhpAstLexer createEmptyLexer_53() {
		return new org.eclipse.php.internal.core.ast.scanner.php53.PhpAstLexer(
				ASTParser.EMPTY_STRING_READER);
	}

	// php 5.4 analysis
	private static org.eclipse.php.internal.core.ast.scanner.php54.PhpAstLexer createEmptyLexer_54() {
		return new org.eclipse.php.internal.core.ast.scanner.php54.PhpAstLexer(
				ASTParser.EMPTY_STRING_READER);
	}

	private static org.eclipse.php.internal.core.ast.scanner.php54.PhpAstParser createEmptyParser_54() {
		return new org.eclipse.php.internal.core.ast.scanner.php54.PhpAstParser(
				createEmptyLexer_54());
	}

	private static org.eclipse.php.internal.core.ast.scanner.php53.PhpAstParser createEmptyParser_53() {
		return new org.eclipse.php.internal.core.ast.scanner.php53.PhpAstParser(
				createEmptyLexer_53());
	}

	// php 5 analysis
	private static org.eclipse.php.internal.core.ast.scanner.php5.PhpAstLexer createEmptyLexer_5() {
		return new org.eclipse.php.internal.core.ast.scanner.php5.PhpAstLexer(
				ASTParser.EMPTY_STRING_READER);
	}

	private static org.eclipse.php.internal.core.ast.scanner.php5.PhpAstParser createEmptyParser_5() {
		return new org.eclipse.php.internal.core.ast.scanner.php5.PhpAstParser(
				createEmptyLexer_5());
	}

	// php 4 analysis
	private static org.eclipse.php.internal.core.ast.scanner.php4.PhpAstLexer createEmptyLexer_4() {
		return new org.eclipse.php.internal.core.ast.scanner.php4.PhpAstLexer(
				ASTParser.EMPTY_STRING_READER);
	}

	private static org.eclipse.php.internal.core.ast.scanner.php4.PhpAstParser createEmptyParser_4() {
		return new org.eclipse.php.internal.core.ast.scanner.php4.PhpAstParser(
				createEmptyLexer_4());
	}

}
