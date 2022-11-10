/*
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Student: Alex Emch
 * Description: Prg 02 - Sudoku Puzzle
 */

import scala.annotation.tailrec
import scala.io._

object Sudoku {

  // TODO #1: return an 2D array of Int representing a sudoku board given a filename
  def readBoard(fileName: String): Array[Array[Int]] = {
    val in = Source.fromFile(fileName)
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
    val newBoard = board.clone()
    newBoard(row)(col) = d
    if (isValid(newBoard)) {
      //println(boardToString(newBoard)+"\n\n")
      return newBoard
    } else
      newBoard(row)(col) = 0
      getChoice(newBoard, row, col, d+1)
    /**
    val newBoard = board
    if (row >= 9) return newBoard
    else if (col >= 9) getChoice(board, row+1, col, 0)
    else if (newBoard(row)(col)>0) getChoice(board, row, col+1, 0)
    else
      newBoard(row)(col)=d+1
      if (isValid(newBoard)) {
        return newBoard
      } else
      newBoard(row)(col)=0
      getChoice(newBoard,row,col,d+1)
    **/
  }

  // TODO #14: return all possible new board configurations from the given one
  def getChoices(board: Array[Array[Int]]): IndexedSeq[Array[Array[Int]]] = {
    val newBoard = board.clone()

    for {
      row <- 0.to(8)
      col <- 0.to(8)
      d <- 1.to(9)

      if (newBoard(row)(col) == 0)
    } yield getChoice(newBoard,row,col,d)//, println(boardToString(getChoice(newBoard,row,col,1)) + "\n"))
  }

  // TODO #15: return a solution to the puzzle (null if there is no solution)
  def solve(board: Array[Array[Int]]): Array[Array[Int]] = {
    val t = getChoices(board)
    if (t.isEmpty) return null
    if (isSolved(t(0)))
      t(0)
    else solve(t(1))
  }

  def main(args: Array[String]): Unit = {
    //val board = readBoard("sudoku3.txt")
    val sol = solve(readBoard("sudoku2.txt"))
    println(isSolved(sol))
    println(boardToString(sol))
  }
}
