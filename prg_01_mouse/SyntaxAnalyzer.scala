/*
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Description: Prg 01 - SyntaxAnalyzer (an iterable syntax analyzer)
 * Student(s) Name(s): Alex Emch
 */

class SyntaxAnalyzer(private var source: String) {

  private val it = new LexicalAnalyzer(source).iterator
  private var current: Lexeme = null

  // returns the current lexeme
  private def getLexeme: Lexeme = {
    if (current == null)
      nextLexeme
    current
  }

  // advances the input one lexeme
  private def nextLexeme = {
    current = it.next
  }

  // returns true if the given token identifies a statement (or the beginning of a statement)
  private def isStatement(token: Token.Value): Boolean = {
    token == Token.IDENTIFIER     ||
    token == Token.LITERAL        ||
    token == Token.STRING         ||
    token == Token.INPUT          ||
    token == Token.OUTPUT         ||
    token == Token.ASSIGNMENT     ||
    token == Token.ADDITION       ||
    token == Token.SUBTRACTION    ||
    token == Token.MULTIPLICATION ||
    token == Token.DIVISION       ||
    token == Token.MODULUS        ||
    token == Token.LESS           ||
    token == Token.LESS_EQUAL     ||
    token == Token.GREATER        ||
    token == Token.GREATER_EQUAL  ||
    token == Token.EQUAL          ||
    token == Token.DIFFERENT      ||
    token == Token.BREAK          ||
    token == Token.DOT            ||
    token == Token.OPEN_BRACKET   ||
    token == Token.OPEN_PAR
  }

  // returns true if the given token identifies a line (or the beginning of a line)
  // a line can be a statement or a comma
  private def isLine(token: Token.Value): Boolean = {
    isStatement(token) || token == Token.COMMENT
  }

  // parses the program, returning its corresponding parse tree
  def parse: Node = {
    parseMouse
  }

  // TODO: mouse = { line } ´$$´
  private def parseMouse: Node = {
    val node = new Node(new Lexeme("mouse"))
    while (getLexeme != null && isLine(current.token)) {
      node.add(parseLine)
    }
    //Get EO_PRG
    node.add(new Node(getLexeme))
    node
  }

  // TODO: line = statement | comment
  private def parseLine: Node = {
    val node = new Node(new Lexeme("line"))
    if (getLexeme.token == Token.COMMENT){
      node.add(new Node(getLexeme))
      nextLexeme
    } else if (isStatement(getLexeme.token)){
      node.add(parseStatement)
    } else if (getLexeme.token == Token.CLOSE_BRACKET) {
      parseIf
    }
    node
  }

  // TODO: statement = ´?´ | ´!´ | string | identifier | ´=´ | literal | ´+´ | ´-´ | ´*´ | ´/´ | ´%´ | ´<´ | ´<=´ | ´>´ | ´>=´ | ´==´ | ´!=´ | ´^´ | ´.´ |  | if | while
  private def parseStatement: Node = {
    val node = new Node(new Lexeme("statement"))
    if (getLexeme.token == Token.OPEN_BRACKET) {
      node.add(parseIf)
    } else if(getLexeme.token == Token.OPEN_PAR) {
      node.add(parseWhile)
    }
    else
    {
      node.add(new Node(getLexeme))
      nextLexeme
    }
    node
  }

  // TODO: if = ´[´ { line } ´]´
  def parseIf: Node = {
    val node = new Node(new Lexeme("if"))
    // Add OPEN_BRACKET to if lexeme
    node.add(new Node(getLexeme))
    nextLexeme
    while (getLexeme.token != Token.CLOSE_BRACKET) {
      node.add(parseLine)
    }
    // Add CLOSED_BRACKET to if lexeme (close out if statement)
    node.add(new Node(getLexeme))
    nextLexeme
    node
  }

  // TODO: while = ´(´ { line } ´)´
  def parseWhile: Node = {
    val node = new Node(new Lexeme("while"))
    // Add OPEN_PAR to if lexeme
    node.add(new Node(getLexeme))
    nextLexeme
    while (getLexeme.token != Token.CLOSE_PAR) {
      node.add(parseLine)
    }
    // Add CLOSED_PAR to if lexeme (close out while statement)
    node.add(new Node(getLexeme))
    nextLexeme
    node
  }
}

object SyntaxAnalyzer {
  def main(args: Array[String]): Unit = {

    // check if source file was passed through the command-line
    if (args.length != 1) {
      print("Missing source file!")
      System.exit(1)
    }

    val syntaxAnalyzer = new SyntaxAnalyzer(args(0))
    val parseTree = syntaxAnalyzer.parse
    print(parseTree)
  }
}
