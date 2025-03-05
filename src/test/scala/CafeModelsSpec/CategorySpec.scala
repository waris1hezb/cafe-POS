package CafeModelsSpec

import CafeModels._ // Import all models from the CafeModels package


import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CategorySpec extends AnyFlatSpec with Matchers {
  "Category" should "have correct case objects" in {
    Food.toString shouldBe "Food"
    Drink.toString shouldBe "Drink"
  }
}