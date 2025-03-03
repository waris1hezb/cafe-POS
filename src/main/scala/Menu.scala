case class Menu()











sealed trait MenuItem {

  def name: Name

  def price: BigDecimal

  def nutritionalInfo: NutritionalInfo

  def stock: Int

  def isPremiumSpecial: Boolean

  def hotOrCold: HotOrCold

  def category: Category
}


//since nutritional info is a pure data structure
case class NutritionalInfo(
                            calories: Option[Int],
                            carbs: Option[Int],
                            sugar: Option[Int],
                            fat: Option[Int],
                            protein: Option[Int] = None,
                            fiber: Option[Int] = None
                          )

sealed trait Category
case object Food extends Category
case object Drink extends Category


sealed trait Name

sealed trait DrinkName extends Name
//Cold Drinks
case object SpanishLatte extends DrinkName
case object MochaFrappe extends DrinkName
case object IcedAmericano extends DrinkName

//Hot Drinks
case object Espresso extends DrinkName
case object FlatWhite extends DrinkName
case object Cappuccino extends DrinkName

//Teas
case object Turkish extends DrinkName
case object English extends DrinkName
case object Green extends DrinkName

//Juices
case object Orange extends DrinkName
case object Apple extends DrinkName
case object Mango extends DrinkName



sealed trait FoodName extends Name
//Cakes
case object Tiramisu extends FoodName
case object Chocolate extends FoodName
case object RedVelvet extends FoodName

//Burgers
case object Chicken extends FoodName
case object Smash extends FoodName
case object Veggie extends FoodName

//Pizza
case object Margherita extends FoodName
case object Pepperoni extends FoodName








sealed trait HotOrCold
case object Hot extends HotOrCold
case object Cold extends HotOrCold





case class Coffee(name: Name, price: BigDecimal, nutritionalInfo: NutritionalInfo, stock: Int, isPremiumSpecial: Boolean, hotOrCold: HotOrCold, category: Category) extends MenuItem {
}

case class Tea(name: Name, price: BigDecimal, nutritionalInfo: NutritionalInfo, stock: Int, isPremiumSpecial: Boolean, hotOrCold: HotOrCold)

case class Juice(name: Name, price: BigDecimal, nutritionalInfo: NutritionalInfo, stock: Int, isPremiumSpecial: Boolean, hotOrCold: HotOrCold)

case class Cake(name: Name, price: BigDecimal, nutritionalInfo: NutritionalInfo, stock: Int, isPremiumSpecial: Boolean, hotOrCold: HotOrCold)

case class Sandwich(name: Name, price: BigDecimal, nutritionalInfo: NutritionalInfo, stock: Int, isPremiumSpecial: Boolean, hotOrCold: HotOrCold)

case class Burger(name: Name, price: BigDecimal, nutritionalInfo: NutritionalInfo, stock: Int, isPremiumSpecial: Boolean, hotOrCold: HotOrCold)

case class Pizza(name: Name, price: BigDecimal, nutritionalInfo: NutritionalInfo, stock: Int, isPremiumSpecial: Boolean, hotOrCold: HotOrCold)






