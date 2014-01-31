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
      "Palindrome" -> nextPaste( """
// Find out whether a list is a palindrome.
// Example:
// scala> isPalindrome(List(1, 2, 3, 2, 1))
// res0: Boolean = true
def isPalindrome[A](ls: List[A]): Boolean = ???

                                             """)
        ,
      "Greatest common divisor" -> nextPaste( """
// Determine the greatest common divisor of two positive integer numbers.
// Use Euclid's algorithm.
// Example:
// scala> gcd(36, 63)
// res0: Int = 9
def gcd(m: Int, n: Int): Int = ???
                                """)
    )
  }

  val default = templates.head._2

  def nextPaste(x: String): PastesActor.Paste = {
    Paste(pasteIds.next, Some(x), None, None)
  }
}
