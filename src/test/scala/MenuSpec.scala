//
////tests not fully working
//
//import CafeModels._
//import org.scalatest.flatspec.AnyFlatSpec
//import org.scalatest.matchers.should.Matchers
//
//
//class MenuSpec extends AnyFlatSpec with Matchers {
//  // Test data
//  val nutritionalInfo: NutritionalInfo = NutritionalInfo(Some(100), Some(10), Some(5), Some(2))
//  val coffee: Coffee = Coffee(Espresso, BigDecimal(2.50), nutritionalInfo, 10, isPremiumSpecial = false, Hot, Drink, "Strong")
//  val tea: Tea = Tea(Turkish, BigDecimal(2.00), nutritionalInfo, 5, isPremiumSpecial = false, Hot, Drink, 0)
//  val juice: Juice = Juice(Orange, BigDecimal(3.00), nutritionalInfo, 0, isPremiumSpecial = false, Cold, Drink, isFreshlySqueezed = true)
//
//  // Initialize a new Menu instance for each test
//  val menu = new Menu()
//
//  // Test addItem
//  "addItem" should "add a new item to the menu" in {
//    menu.addItem(coffee) shouldBe Right(())
//    menu.listItems should contain(coffee)
//  }
//
//  it should "return an error if the item already exists" in {
//    menu.addItem(coffee) shouldBe Right(()) // Add the item first
//    menu.addItem(coffee) shouldBe Left("Item already exists: Espresso") // Try to add it again
//  }
//
//  // Test removeItem
//  "removeItem" should "remove an item from the menu" in {
//    menu.addItem(tea) shouldBe Right(()) // Add the item first
//    menu.removeItem("Turkish") shouldBe Right(()) // Remove the item
//    menu.listItems should not contain tea
//  }
//
//  it should "return an error if the item does not exist" in {
//    menu.removeItem("Unknown") shouldBe Left("Item not found: Unknown")
//  }
//
//  // Test getItem
//  "getItem" should "return the item if it exists" in {
//    menu.addItem(juice) shouldBe Right(()) // Add the item first
//    menu.getItem("Orange") shouldBe Some(juice)
//  }
//
//  it should "return None if the item does not exist" in {
//    menu.getItem("Unknown") shouldBe None
//  }
//
//  // Test updateStock
//  "updateStock" should "update the stock of an item" in {
//    menu.addItem(coffee) shouldBe Right(()) // Add the item first
//    menu.updateStock("Espresso", 5) shouldBe Right(coffee.copy(stock = 5)) // Update stock
//    menu.getItem("Espresso").get.stock shouldBe 5 // Verify the stock is updated
//  }
//
//  it should "return an error if the stock value is negative" in {
//    menu.updateStock("Espresso", -1) shouldBe Left("Invalid stock value: Stock cannot be negative.")
//  }
//
//  it should "return an error if the item does not exist" in {
//    menu.updateStock("Unknown", 10) shouldBe Left("Item not found: Unknown")
//  }
//
//  // Test listItems
//  "listItems" should "return all items in the menu" in {
//    menu.addItem(coffee) shouldBe Right(()) // Add items
//    menu.addItem(tea) shouldBe Right(())
//    menu.listItems should contain allOf(coffee, tea)
//  }
//
//  // Test isInStock
//  "isInStock" should "return true if the item is in stock" in {
//    menu.addItem(coffee) shouldBe Right(()) // Add the item first
//    menu.isInStock("Espresso") shouldBe true
//  }
//
//  it should "return false if the item is out of stock" in {
//    menu.addItem(juice) shouldBe Right(()) // Add the item first (stock = 0)
//    menu.isInStock("Orange") shouldBe false
//  }
//
//  it should "return false if the item does not exist" in {
//    menu.isInStock("Unknown") shouldBe false
//  }
//
//  // Test purchaseItem
//  "purchaseItem" should "reduce the stock if the item is in stock" in {
//    menu.addItem(coffee) shouldBe Right(()) // Add the item first (stock = 10)
//    menu.purchaseItem("Espresso", 2) shouldBe true // Purchase 2 items
//    menu.getItem("Espresso").get.stock shouldBe 8 // Verify the stock is reduced
//  }
//
//  it should "return false if the item is out of stock" in {
//    menu.addItem(juice) shouldBe Right(()) // Add the item first (stock = 0)
//    menu.purchaseItem("Orange", 1) shouldBe false // Try to purchase 1 item
//  }
//
//  it should "return false if the item does not exist" in {
//    menu.purchaseItem("Unknown", 1) shouldBe false
//  }
//}
