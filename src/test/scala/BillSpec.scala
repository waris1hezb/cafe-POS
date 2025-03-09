import CafeModels._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillSpec extends AnyFlatSpec with Matchers {

  // Sample menu items for testing
  val espresso = Coffee(
    name = Espresso,
    price = BigDecimal(2.50),
    nutritionalInfo = NutritionalInfo(Some(100), Some(10), Some(5), Some(3)),
    stock = 10,
    isPremiumSpecial = false,
    hotOrCold = Hot,
    category = Drink,
    strength = "Strong"
  )

  val orangeJuice = Juice(
    name = Orange,
    price = BigDecimal(3.00),
    nutritionalInfo = NutritionalInfo(Some(120), Some(30), Some(25), Some(0)),
    stock = 15,
    isPremiumSpecial = false,
    hotOrCold = Cold,
    category = Drink,
    isFreshlySqueezed = true
  )

  val tiramisu = Cake(
    name = Tiramisu,
    price = BigDecimal(4.00),
    nutritionalInfo = NutritionalInfo(Some(300), Some(40), Some(20), Some(10)),
    stock = 5,
    isPremiumSpecial = false,
    hotOrCold = Cold,
    category = Food,
    isGlutenFree = false
  )

  val redVelvetCake = Cake(
    name = RedVelvet,
    price = BigDecimal(5.00),
    nutritionalInfo = NutritionalInfo(Some(350), Some(45), Some(25), Some(15)),
    stock = 5,
    isPremiumSpecial = true, // Premium item
    hotOrCold = Cold,
    category = Food,
    isGlutenFree = false
  )

  val pepperoniPizza = Pizza(
    name = Pepperoni,
    price = BigDecimal(12.00),
    nutritionalInfo = NutritionalInfo(Some(800), Some(60), Some(20), Some(40)),
    stock = 5,
    isPremiumSpecial = false,
    hotOrCold = Hot,
    category = Food,
    toppings = List("Sweetcorn", "Onions")
  )

  val bill = new Bill()

  // Test 1: Valid order with drinks only (no service charge)
  "generateBill" should "calculate the correct total for an order with drinks only" in {
    val order = Order(Map(espresso -> 2, orangeJuice -> 1), None)
    val expectedBill =
      """
        |Itemised Bill:
        |Espresso x 2: £5.00
        |Orange x 1: £3.00
        |Subtotal: £8.00
        |Service Charge: £0.00
        |Total: £8.00
        |""".stripMargin.trim

    bill.generateBill(order) shouldBe Right(expectedBill)
  }

  // Test 2: Valid order with cold food (10% service charge)
  it should "calculate the correct total for an order with cold food" in {
    val order = Order(Map(tiramisu -> 1, orangeJuice -> 2), None)
    val expectedBill =
      """
        |Itemised Bill:
        |Tiramisu x 1: £4.00
        |Orange x 2: £6.00
        |Subtotal: £10.00
        |Service Charge: £1.00
        |Total: £11.00
        |""".stripMargin.trim

    bill.generateBill(order) shouldBe Right(expectedBill)
  }

  // Test 3: Valid order with hot food (20% service charge)
  it should "calculate the correct total for an order with hot food" in {
    val order = Order(Map(pepperoniPizza -> 1, espresso -> 1), None)
    val expectedBill =
      """
        |Itemised Bill:
        |Pepperoni x 1: £12.00
        |Espresso x 1: £2.50
        |Subtotal: £14.50
        |Service Charge: £2.90
        |Total: £17.40
        |""".stripMargin.trim

    bill.generateBill(order) shouldBe Right(expectedBill)
  }

  // Test 4: Valid order with premium items (25% service charge)
  it should "calculate the correct total for an order with premium items" in {
    val order = Order(Map(redVelvetCake -> 1, orangeJuice -> 1), None)
    val expectedBill =
      """
        |Itemised Bill:
        |RedVelvet x 1: £5.00
        |Orange x 1: £3.00
        |Subtotal: £8.00
        |Service Charge: £2.00
        |Total: £10.00
        |""".stripMargin.trim

    bill.generateBill(order) shouldBe Right(expectedBill)
  }

  // Test 5: Valid order with custom service charge
  it should "calculate the correct total for an order with a custom service charge" in {
    val order = Order(Map(espresso -> 1, orangeJuice -> 1), Some(0.15))
    val expectedBill =
      """
        |Itemised Bill:
        |Espresso x 1: £2.50
        |Orange x 1: £3.00
        |Subtotal: £5.50
        |Service Charge: £0.83
        |Total: £6.33
        |""".stripMargin.trim

    bill.generateBill(order) shouldBe Right(expectedBill)
  }

  // Test 6: Invalid order with zero quantity
  it should "return an error for an order with zero quantity" in {
    val order = Order(Map(espresso -> 0, orangeJuice -> 1), None)
    bill.generateBill(order) shouldBe Left(OrderInvalidQuantityError("Espresso"))
  }

  // Test 7: Invalid order with negative quantity
  it should "return an error for an order with negative quantity" in {
    val order = Order(Map(espresso -> -1, orangeJuice -> 1), None)
    bill.generateBill(order) shouldBe Left(OrderInvalidQuantityError("Espresso"))
  }

  // Test 8: Invalid custom service charge (negative value)
  it should "return an error for a negative custom service charge" in {
    val order = Order(Map(espresso -> 1, orangeJuice -> 1), Some(-0.10))
    bill.generateBill(order) shouldBe Left(OrderInvalidServiceChargeError("Custom service charge must be non-negative."))
  }

  // Test 9: Valid order with no items (empty order)
  it should "return an error for an empty order" in {
    val order = Order(Map.empty, None)
    bill.generateBill(order) shouldBe Left(OrderInvalidItemListError("Order cannot be empty."))
  }
}