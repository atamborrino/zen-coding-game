import collection.mutable.Stack
import org.scalatest._

class PalindromeSpec extends FlatSpec {

  "isPalindrome" should "return true for the empty list" in {
    assert(isPalindrome(List()))
  }

  it should "return true for Lists with one element" in {
    assert(isPalindrome(List(0)))
    assert(isPalindrome(List(1)))
    assert(isPalindrome(List(4)))
  }

  it should "handle palindromes with even length" in {
    assert(isPalindrome(List(1, 2, 2, 1)))
    assert(isPalindrome(List(3, 4, 7, 7, 4, 3)))
  }

  it should "handle palindromes with odd length" in {
    assert(isPalindrome(List(1, 2, 3, 2, 1)))
    assert(isPalindrome(List(3, 4, 7, 1, 7, 4, 3)))
  }

  it should "return false for invalid palindromes" in {
    assert(isPalindrome(List(1, 3)) === false)
    assert(isPalindrome(List(0, -1)) === false)
    assert(isPalindrome(List(1, 4, 0)) === false)
    assert(isPalindrome(List(1, 2, 5, 3, 2, 1)) === false)
    assert(isPalindrome(List(1, 4, 7, 9, 10, 7, 4, 1)) === false)
  }
}
