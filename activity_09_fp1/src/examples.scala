/*
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Description: Activity 09 - Functional Programming Examples
 */

object Examples {
  def isPrime(a: Int): Boolean = {
    if (2.until(a).exists(a % _ == 0)) {
      return false
    } else {
      return true
    }
  }
  

  def totientPhi(m: Int): Range = {

    //if (1.until(m).filter((r: Int) => coprime(m)(r)) {

    //}

  }


  def main(args: Array[String]): Unit = {
    //println(isPrime(5))
    val lst = 2.to(100)
    println(lst.filter(isPrime(_)))
  }
}