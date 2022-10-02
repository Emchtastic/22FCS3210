/*
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Description: Prg 01 - LexicalAnalyzer (an iterable lexical analyzer)
 * Student(s) Name(s): Alex Emch
 */

import LexicalAnalyzer.{BLANKS, DIGITS, LETTERS, NEW_LINE, PUNCTUATIONS, SPECIALS}

import scala.io.Source
import scala.util.control.Breaks.break

class LexicalAnalyzer(private var source: String) extends Iterable[Lexeme]{

  var input = ""
  for (line <- Source.fromFile(source).getLines)
    input += line + LexicalAnalyzer.NEW_LINE
  input = input.trim

  // checks if reached eof
  private def eof: Boolean = input.length == 0

  var currentChar: Char = 0

  // returns the current char
  private def getChar = {
    if (!eof)
      currentChar = input(0)
    currentChar
  }

  // advances the input one character
  private def nextChar: Unit = {
    if (!eof)
      input = input.substring(1)
  }

  // checks if input has a blank character ahead
  private def hasBlank: Boolean = {
    LexicalAnalyzer.BLANKS.contains(getChar)
  }

  // reads the input until a non-blank character is found, updating the input
  def readBlanks: Unit = {
    while (!eof && hasBlank)
      nextChar
  }

  // checks if input has a letter ahead
  private def hasLetter: Boolean = {
    LexicalAnalyzer.LETTERS.contains(getChar)
  }

  // checks if input has a digit ahead
  private def hasDigit: Boolean = {
    LexicalAnalyzer.DIGITS.contains(getChar)
  }

  // checks if input has a special character ahead
  private def hasSpecial: Boolean = {
    LexicalAnalyzer.SPECIALS.contains(getChar)
  }

  // checks if input has a punctuation character ahead
  private def hasPunctuation: Boolean = {
    LexicalAnalyzer.PUNCTUATIONS.contains(getChar)
  }

  // returns an iterator for the lexical analyzer
  override def iterator: Iterator[Lexeme] = {

    new Iterator[Lexeme] {

      // returns true/false depending whether there is a lexeme to be read from the input
      override def hasNext: Boolean = {
        readBlanks
        !eof
      }

      // TODO: return the next lexeme (or Token.EOF if there isn't any lexeme left to be read)
      override def next(): Lexeme = {
        if (!hasNext)
          return new Lexeme("eof", Token.EOF)

        else if (getChar == '"') {
          var str = ""
          nextChar
          while((hasBlank||hasLetter||hasDigit||hasSpecial||hasPunctuation) && !eof && getChar != '"') {
            str += getChar
            nextChar
          }
          nextChar
          return new Lexeme(str, Token.STRING)
        }

        else if (hasDigit) {
          val str = getChar + ""
          nextChar
          return new Lexeme(str, Token.LITERAL)
        }

        else if (hasLetter) {
          val str = getChar + ""
          nextChar
          return new Lexeme(str, Token.IDENTIFIER)
        }

        else if (hasPunctuation) {
          val str = getChar + ""
          nextChar
          str match {
            case ";" =>
              var str = ""
              nextChar
              while((hasBlank||hasLetter||hasDigit||hasSpecial||hasPunctuation) && !eof && getChar != NEW_LINE) {
                str += getChar
                nextChar
              }
              return new Lexeme(str, Token.COMMENT)
            case "?" => return new Lexeme(str, Token.INPUT)
            case "!" => return new Lexeme(str, Token.OUTPUT)
            case "." => return new Lexeme(str, Token.DOT)
          }
        }

        else if (hasSpecial) {
          var str = getChar + ""
          nextChar
          str match {
            case "[" => return new Lexeme(str, Token.OPEN_BRACKET)
            case "]" => return new Lexeme(str, Token.CLOSE_BRACKET)
            case "(" => return new Lexeme(str, Token.OPEN_PAR)
            case ")" => return new Lexeme(str, Token.CLOSE_PAR)
            case ">" =>
              if (getChar == '=') {
                str += getChar
                nextChar
                return new Lexeme(str, Token.GREATER_EQUAL)
              }
              else {
                return new Lexeme(str, Token.GREATER)
              }
            case "<" =>
              if (getChar == '=') {
                str += getChar
                nextChar
                return new Lexeme(str, Token.LESS_EQUAL)
              }
              else {
                return new Lexeme(str, Token.LESS)
              }
            case "+" => return new Lexeme(str, Token.ADDITION)
            case "-" => return new Lexeme(str, Token.SUBTRACTION)
            case "*" => return new Lexeme(str, Token.MULTIPLICATION)
            case "/" => return new Lexeme(str, Token.DIVISION)
            case "=" =>
              if (getChar == '=') {
                str += getChar
                nextChar
                return new Lexeme(str, Token.EQUAL)
              }
              else {
                return new Lexeme(str, Token.ASSIGNMENT)
              }
            case "%" => return new Lexeme(str, Token.MODULUS)
            case "^" => return new Lexeme(str, Token.BREAK)
            case "$" =>
              str += getChar
              nextChar
              return new Lexeme(str, Token.EO_PRG)
          }
        }
/**
        else if (getChar == '$') {
          var str = getChar + ""
          nextChar
          str += getChar
          nextChar
          return new Lexeme(str, Token.EO_PRG)
        }
**/

        // throw an exception if an unrecognizable symbol is found
        throw new Exception("Lexical Analyzer Error: unrecognizable symbol \"" + getChar + "\" found!")
      }
    }
  }
}

object LexicalAnalyzer {
  val BLANKS       = " \n\t"
  val NEW_LINE     = '\n'
  val LETTERS      = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
  val DIGITS       = "0123456789"
  val PUNCTUATIONS = ".,;:?!"
  val SPECIALS     = "><_@#$%^&()-+=*'/\\[]{}|"

  def main(args: Array[String]): Unit = {

    // checks the command-line for source file
    if (args.length != 1) {
      print("Missing source file!")
      System.exit(1)
    }

    // iterates over the lexical analyzer, printing the lexemes found
    val lex = new LexicalAnalyzer(args(0))
    val it = lex.iterator
    while (it.hasNext)
      println(it.next())

  } // end main method
}
