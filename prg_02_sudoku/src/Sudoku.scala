/*
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Student: Alex Emch
 * Description: Prg 02 - Sudoku Puzzle
 */

import java.security.Identity
import scala.annotation.tailrec
import scala.io._

object Sudoku {

  // TODO #1: return an 2D array of Int representing a sudoku board given a filename
  def readBoard(fileName: String): Array[Array[Int]] = {
    var in = Source.fromFile(fileName)
    val test = in.getLines().filterNot(_.isEmpty)
    //Check if there is any funny business in the sudoku file (non-digits will not be accepted)
    if (!test.forall(_.matches("[0-9]+"))) {
      println("The sudoku board must only contain digits")
      System.exit(0)
    }
    //Restart file to upload
    in = Source.fromFile(fileName)
    val lines = in.getLines().filterNot(_.isEmpty)
    val board = lines.map(line => line.split("").map(_.toInt)).toArray
    in.close()
    board
  }

  // TODO #2: return a String representation from a given sudoku board
  def boardToString(board: Array[Array[Int]]): String = {
    board.map(_.mkString).mkString("\n")
  }

  // TODO #3: return a specific row from a sudoku board as a sequence of numbers
  def getRow(board: Array[Array[Int]], row: Int): Array[Int] = {
    board(row-1)
  }

  // TODO #4: return a specific column from a sudoku board as a sequence of numbers
  def getCol(board: Array[Array[Int]], col: Int): Array[Int] = {
    //Build new array using elements from column _
    board.map(_(col-1))
  }

  // TODO #5: return a specific box from a sudoku board as a sequence of numbers
  def getBox(board: Array[Array[Int]], x: Int, y: Int): Array[Int] = {
    val rowStart = x
    val colStart = y

    val box = for {
      rowInt <- (rowStart*3) until (rowStart*3+3)
      colInt <- (colStart*3) until (colStart*3+3)
    } yield (board(rowInt)(colInt))
    box.toArray
  }

  // TODO #6: a sequence is valid if it has 9 numbers in [0-9] with possibly repeated zeros
  def isValid(seq: Array[Int]): Boolean = {
    //array of any duplicate 1-9 numbers in array
    val duplicates = seq.diff(seq.distinct).distinct.filterNot(_==0)
    //Check if elements in sequence are 0-9, length is 9, and no duplicates (0's are ok for now)
    val check = seq.forall(Set(0,1,2,3,4,5,6,7,8,9).contains) && seq.length==9 && duplicates.isEmpty
    check
  }

  // TODO #7: return whether all rows of the given board are valid sequences
  def allRowsValid(board: Array[Array[Int]]): Boolean = {
    for {
      el <- 1.to(9)
      if !isValid(getRow(board, el))
    } return false
    true
  }

  // TODO #8: return whether all columns of the given board are valid sequences
  def allColsValid(board: Array[Array[Int]]): Boolean = {
    for {
      el <- 1.to(9)
      if !isValid(getCol(board, el))
    } return false
    true
  }

  // TODO #9: return whether all boxes of the given board are valid sequences
  def allBoxesValid(board: Array[Array[Int]]): Boolean = {
    for {
      i <- 0.to(2)
      j <- 0.to(2)
      if !isValid(getBox(board, i, j))
    } return false
    true
  }

  // TODO #10: a board is valid if all of its rows, columns, and boxes are also valid
  def isValid(board: Array[Array[Int]]): Boolean = {
    allBoxesValid(board) && allColsValid(board) && allRowsValid(board)
  }

  // TODO #11: a board is complete if there is no zero
  def isComplete(board: Array[Array[Int]]): Boolean = {
    for{
      el <- 1.to(9)
      if getRow(board, el).contains(0)
    } return false
    true
  }

  // TODO #12: a board is solved if is complete and valid
  def isSolved(board: Array[Array[Int]]): Boolean = {
    isValid(board) && isComplete(board)
  }

  // TODO #13: return a new board configuration from the given one by setting a digit at a specific (row, col) location
  def getChoice(board: Array[Array[Int]], row: Int, col: Int, d: Int): Array[Array[Int]] = {
    val choice = board.map(_.map(X=>X)) //Using map to throw each element for board into the choice. Cloning the whole board will also clone the address and overwrite the original board
    choice(row)(col) = d
    choice
  }

  // TODO #14: return all possible new board configurations from the given one
  def getChoices(board: Array[Array[Int]]): IndexedSeq[Array[Array[Int]]] = {
    for {
      row <- 0.to(8)
      col <- 0.to(8)
      d <- 1.to(9)
      if (board(row)(col) == 0 && isValid(getChoice(board,row,col,d))) //isValid() check to prune as we go - get rid of boards that are inValid for the solution
    } yield getChoice(board,row,col,d)

  }

  // TODO #15: return a solution to the puzzle (null if there is no solution)
  def solve(board: Array[Array[Int]]): Array[Array[Int]] = {
    //Test if starting board is invalid(doesn't follow sudoku rules). Really meant as a check against bad/invalid starting boards
    if (!isValid(board)) return null
    //Check if passed board is already solved (preventing getChoices null seq error)
    if (isSolved(board)) {
      println("Board already Solved :)")
      board
    } else {
      //Build the choice trees and work through them to find the SOLUTION
      for(el <- getChoices(board)) {
        if(isSolved(el)) return el
        else return solve(el)
      }
    }
  }.asInstanceOf[Array[Array[Int]]] //Kudos to Sean Kruse for this fix. Was returning as a Unit and had to hard press asInstanceOf 'board'

  def main(args: Array[String]): Unit = {
    val board = readBoard("sudoku4.txt")
    println(getBox(board, 0, 0).mkString("Array(", ", ", ")"))
    println(getBox(board, 0, 1).mkString("Array(", ", ", ")"))
    println(getBox(board, 0, 2).mkString("Array(", ", ", ")"))
    println(getBox(board, 1, 0).mkString("Array(", ", ", ")"))
    println(getBox(board, 1, 1).mkString("Array(", ", ", ")"))
    println(getBox(board, 1, 2).mkString("Array(", ", ", ")"))
    println(getBox(board, 2, 0).mkString("Array(", ", ", ")"))
    println(getBox(board, 2, 1).mkString("Array(", ", ", ")"))
    println(getBox(board, 2, 2).mkString("Array(", ", ", ")"))
    val sol = solve(board)
    if (sol != null) println("Board Solution:\n"+boardToString(sol))
    else println("There is no possible solution for this board")
  }
}
