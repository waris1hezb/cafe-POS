import CafeModels._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MenuSpec extends AnyFlatSpec with Matchers {

  // Sample menu items for testing
  val espresso = Coffee(Espresso, BigDecimal(2.50), NutritionalInfo(Some(100), Some(10), Some(5), Some(3)), 10, false, Hot, Drink, "Strong")
  val cappuccino = Coffee(Cappuccino, BigDecimal(3.00), NutritionalInfo(Some(120), Some(15), Some(8), Some(4)), 5, false, Hot, Drink, "Medium")
  val tiramisu = Cake(Tiramisu, BigDecimal(4.50), NutritionalInfo(Some(300), Some(40), Some(20), Some(10)), 3, false, Cold, Food, true)

  "A Menu" should "add an item successfully" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.listItems should contain(espresso)
  }

  it should "return an error when adding a duplicate item" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.addItem(espresso) shouldBe Left(MenuItemAlreadyExistsError("Espresso"))
  }

  it should "remove an item successfully" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.removeItem("Espresso") shouldBe Right(())
    menu.listItems should not contain espresso
  }

  it should "return an error when removing a non-existent item" in {
    val menu = new Menu() // Fresh instance for this test
    menu.removeItem("Espresso") shouldBe Left(MenuItemNotFoundError("Espresso"))
  }

  it should "get an item by name successfully" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.getItem("Espresso") shouldBe Right(espresso)
  }

  it should "return an error when getting a non-existent item" in {
    val menu = new Menu() // Fresh instance for this test
    menu.getItem("Espresso") shouldBe Left(MenuItemNotFoundError("Espresso"))
  }

  it should "update stock successfully" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.updateStock("Espresso", 5) shouldBe Right(espresso.copy(stock = 5))
    menu.getItem("Espresso").map(_.stock) shouldBe Right(5)
  }

  it should "return an error when updating stock for a non-existent item" in {
    val menu = new Menu() // Fresh instance for this test
    menu.updateStock("Espresso", 5) shouldBe Left(MenuItemNotFoundError("Espresso"))
  }

  it should "return an error when updating stock with a negative value" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.updateStock("Espresso", -1) shouldBe Left(MenuInvalidStockError("Espresso"))
  }

  it should "check if an item is in stock for a given quantity" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())

    // Check if there is enough stock for a valid quantity
    menu.isInStock("Espresso", 5) shouldBe true // 5 <= 10 (stock)

    // Check if there is not enough stock for a larger quantity
    menu.isInStock("Espresso", 15) shouldBe false // 15 > 10 (stock)

    // Check if the item is out of stock
    menu.updateStock("Espresso", 0) shouldBe Right(espresso.copy(stock = 0))
    menu.isInStock("Espresso", 1) shouldBe false // 1 > 0 (stock)
  }


  it should "purchase an item successfully" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.purchaseItem("Espresso", 3) shouldBe Right(true)
    menu.getItem("Espresso").map(_.stock) shouldBe Right(7)
  }

  it should "return an error when purchasing a non-existent item" in {
    val menu = new Menu() // Fresh instance for this test
    menu.purchaseItem("Espresso", 3) shouldBe Left(MenuItemNotFoundError("Espresso"))
  }

  it should "return an error when purchasing with insufficient stock" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.purchaseItem("Espresso", 15) shouldBe Left(MenuInsufficientStockError("Espresso"))
  }

  it should "list all items in the menu" in {
    val menu = new Menu() // Fresh instance for this test
    menu.addItem(espresso) shouldBe Right(())
    menu.addItem(cappuccino) shouldBe Right(())
    menu.addItem(tiramisu) shouldBe Right(())
    menu.listItems should contain allOf(espresso, cappuccino, tiramisu)
  }
}