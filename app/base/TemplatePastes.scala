package base

import com.olegych.scastie.PastesActor.Paste
import java.util.concurrent.atomic.AtomicLong
import com.olegych.scastie.PastesActor

/**
  */
object TemplatePastes {
  private val pasteIds = new AtomicLong(1L) {
    def next = incrementAndGet()
  }

  val templates = {
    List(
      (
        "Palindrome"
          ,nextPaste( """
// Find out whether a list is a palindrome.
// Example:
// scala> isPalindrome(List(1, 2, 3, 2, 1))
// res0: Boolean = true
object Palindrome {
  def isPalindrome[A](ls: List[A]): Boolean = ???
}
"""
          , """
import org.scalatest._

class TestSpec extends FlatSpec {
  "isPalindrome" should "return true for the empty list" in {
    assert(Palindrome.isPalindrome(List()))
  }

  it should "return true for Lists with one element" in {
    assert(Palindrome.isPalindrome(List(0)))
    assert(Palindrome.isPalindrome(List(1)))
    assert(Palindrome.isPalindrome(List(4)))
  }

  it should "handle palindromes with even length" in {
    assert(Palindrome.isPalindrome(List(1, 2, 2, 1)))
    assert(Palindrome.isPalindrome(List(3, 4, 7, 7, 4, 3)))
  }

  it should "handle palindromes with odd length" in {
    assert(Palindrome.isPalindrome(List(1, 2, 3, 2, 1)))
    assert(Palindrome.isPalindrome(List(3, 4, 7, 1, 7, 4, 3)))
  }

  it should "return false for invalid palindromes" in {
    assert(Palindrome.isPalindrome(List(1, 3)) === false)
    assert(Palindrome.isPalindrome(List(0, -1)) === false)
    assert(Palindrome.isPalindrome(List(1, 4, 0)) === false)
    assert(Palindrome.isPalindrome(List(1, 2, 5, 3, 2, 1)) === false)
    assert(Palindrome.isPalindrome(List(1, 4, 7, 9, 10, 7, 4, 1)) === false)
  }
}

object Main extends App {
  (new TestSpec).execute(stats = true)
}

                                             """
)),
      ("Greatest common divisor"
       ,nextPaste( """
// Determine the greatest common divisor of two positive integer numbers.
// Use Euclid's algorithm.
// Example:
// scala> gcd(36, 63)
// res0: Int = 9
object GCD {
  def gcd(m: Int, n: Int): Int = ???
}
"""
        , """
import org.scalatest._

class TestSpec extends FlatSpec {

  "gcd" should "return 1 when n and m are coprimes" in {
    assert(GCD.gcd(35, 64) === 1)
    assert(GCD.gcd(100, 17) === 1)
    assert(GCD.gcd(16, 27) === 1)
    assert(GCD.gcd(97, 21) === 1)
  }

  it should "return valid values" in {
    assert(GCD.gcd(36, 63) === 9)
    assert(GCD.gcd(70, 42) === 14)
    assert(GCD.gcd(123, 15) === 3)
    assert(GCD.gcd(70, 21) === 7)
    assert(GCD.gcd(56, 21808) === 8)
  }

  it should "return m if m is a divisor of n" in {
    assert(GCD.gcd(15, 60) === 15)
    assert(GCD.gcd(2, 8) === 2)
    assert(GCD.gcd(1, 10) === 1)
  }

  it should "return n if n is a divisor of m" in {
    assert(GCD.gcd(60, 15) === 15)
    assert(GCD.gcd(8, 2) === 2)
    assert(GCD.gcd(10, 1) === 1)
  }
}

object Main extends App {
  (new TestSpec).execute(stats = true)
}

""" ) )
) }

  val default = templates.head._2

  def nextPaste(x: String, t: String): PastesActor.Paste = {
    Paste(pasteIds.next, Some(x), Some(t), None, None)
  }
}
