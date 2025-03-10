package CafeModelsSpec


import CafeModels._ // Import all models from the CafeModels package
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HotOrColdSpec extends AnyFlatSpec with Matchers {
  "HotOrCold" should "have correct case objects" in {
    Hot.toString shouldBe "Hot"
    Cold.toString shouldBe "Cold"
  }
}
