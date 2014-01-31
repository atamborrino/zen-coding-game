import org.scalatest._

class TestSpec extends FlatSpec with Matchers {

  it should "return 2" in {
    MyObject.doit() should be (2)
  }

}

object Main extends App {
  (new TestSpec).execute(stats = true)
}


