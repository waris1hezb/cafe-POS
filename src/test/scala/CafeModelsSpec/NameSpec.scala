package CafeModelsSpec

import CafeModels._ // Import all models from the CafeModels package
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NameSpec extends AnyFlatSpec with Matchers {
  "Name" should "have correct descriptions" in {
    Espresso.description shouldBe "A strong and concentrated coffee shot."
    Turkish.description shouldBe "A strong black tea brewed with traditional Turkish methods."
    Orange.description shouldBe "Freshly squeezed orange juice, packed with vitamin C."
    Tiramisu.description shouldBe "A classic Italian dessert with coffee-soaked ladyfingers and mascarpone cream."
  }
}