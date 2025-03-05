package CafeModelsSpec

import CafeModels._ // Import all models from the CafeModels package
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NutritionalInfoSpec extends AnyFlatSpec with Matchers {
  // Test data
  val nutritionalInfo: NutritionalInfo = NutritionalInfo(Some(100), Some(10), Some(5), Some(2))

  "NutritionalInfo" should "calculate total calories correctly" in {
    nutritionalInfo.totalCalories shouldBe 158 // 100 + (10 * 4) + (5 * 4) + (2 * 9)
  }

//  it should "handle missing fields gracefully" in {
//    val incompleteInfo = NutritionalInfo(Some(100), None, Some(5), None)
//    incompleteInfo.totalCalories shouldBe 120 // 100 + (5 * 4)
//  }
}