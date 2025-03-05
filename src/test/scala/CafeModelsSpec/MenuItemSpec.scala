package CafeModelsSpec


import CafeModels._ // Import all models from the CafeModels package
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MenuItemSpec extends AnyFlatSpec with Matchers {
  // Test data
  val nutritionalInfo: NutritionalInfo = NutritionalInfo(Some(100), Some(10), Some(5), Some(2))
  val coffee: Coffee = Coffee(Espresso, BigDecimal(2.50), nutritionalInfo, 10, false, Hot, Drink, "Strong")
  val tea: Tea = Tea(Turkish, BigDecimal(2.00), nutritionalInfo, 5, false, Hot, Drink, 0)
  val juice: Juice = Juice(Orange, BigDecimal(3.00), nutritionalInfo, 0, false, Cold, Drink, true)

  // Test Coffee
  "A Coffee" should "have the correct description" in {
    coffee.description shouldBe "A strong and concentrated coffee shot. Strength: Strong."
  }

  // Test Tea
  "A Tea" should "have the correct description" in {
    tea.description shouldBe "A strong black tea brewed with traditional Turkish methods. Sugar: 0 cubes."
  }

  it should "allow adding sugar" in {
    val updatedTea = tea.addSugar(2)
    updatedTea.addSugar shouldBe 2
  }

  // Test Juice
  "A Juice" should "have the correct description" in {
    juice.description shouldBe "Freshly squeezed orange juice, packed with vitamin C. Freshly squeezed: true."
  }
}