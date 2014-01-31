import collection.mutable.Stack
import org.scalatest._

class GcdSpec extends FlatSpec {

  "gcd" should "return 1 when n and m are coprimes" in {
    assert(gcd(35, 64) === 1)
    assert(gcd(100, 17) === 1)
    assert(gcd(16, 27) === 1)
    assert(gcd(97, 21) === 1)
  }

  it should "return valid values" in {
    assert(gcd(36, 63) === 9)
    assert(gcd(70, 42) === 14)
    assert(gcd(123, 15) === 3)
    assert(gcd(70, 21) === 7)
    assert(gcd(56, 21808) === 8)
  }

  it should "return m if m is a divisor of n" in {
    assert(gcd(15, 60) === 15)
    assert(gcd(2, 8) === 2)
    assert(gcd(1, 10) === 1)
  }

  it should "return n if n is a divisor of m" in {
    assert(gcd(60, 15) === 15)
    assert(gcd(8, 2) === 2)
    assert(gcd(10, 1) === 1)
  }
}
