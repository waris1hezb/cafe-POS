package CafeModels



// Base trait for all menu items
sealed trait MenuItem {
  def name: Name
  def price: BigDecimal
  def nutritionalInfo: NutritionalInfo
  def stock: Int
  def isPremiumSpecial: Boolean
  def hotOrCold: HotOrCold
  def category: Category
  def description: String
}

// Specific item case classes with type-specific features
case class Coffee(
                   name: Name,
                   price: BigDecimal,
                   nutritionalInfo: NutritionalInfo,
                   stock: Int,
                   isPremiumSpecial: Boolean,
                   hotOrCold: HotOrCold,
                   category: Category,
                   strength: String // Type-specific field
                 ) extends MenuItem {
  override def description: String = s"${name.description} Strength: $strength."
}

case class Tea(
                name: Name,
                price: BigDecimal,
                nutritionalInfo: NutritionalInfo,
                stock: Int,
                isPremiumSpecial: Boolean,
                hotOrCold: HotOrCold,
                category: Category,
                addSugar: Int // Type-specific field
              ) extends MenuItem {
  override def description: String = s"${name.description} Sugar: $addSugar cubes."
  def addSugar(amount: Int): Tea = this.copy(addSugar = this.addSugar + amount)
}

case class Juice(
                  name: Name,
                  price: BigDecimal,
                  nutritionalInfo: NutritionalInfo,
                  stock: Int,
                  isPremiumSpecial: Boolean,
                  hotOrCold: HotOrCold,
                  category: Category,
                  isFreshlySqueezed: Boolean // Type-specific field
                ) extends MenuItem {
  override def description: String = s"${name.description} Freshly squeezed: $isFreshlySqueezed."
}

case class Cake(
                 name: Name,
                 price: BigDecimal,
                 nutritionalInfo: NutritionalInfo,
                 stock: Int,
                 isPremiumSpecial: Boolean,
                 hotOrCold: HotOrCold,
                 category: Category,
                 isGlutenFree: Boolean // Type-specific field
               ) extends MenuItem {
  override def description: String = s"${name.description} Gluten-free: $isGlutenFree."
}

case class Burger(
                   name: Name,
                   price: BigDecimal,
                   nutritionalInfo: NutritionalInfo,
                   stock: Int,
                   isPremiumSpecial: Boolean,
                   hotOrCold: HotOrCold,
                   category: Category,
                   hasCheese: Boolean // Type-specific field
                 ) extends MenuItem {
  override def description: String = s"${name.description} Cheese: $hasCheese."
}

case class Pizza(
                  name: Name,
                  price: BigDecimal,
                  nutritionalInfo: NutritionalInfo,
                  stock: Int,
                  isPremiumSpecial: Boolean,
                  hotOrCold: HotOrCold,
                  category: Category,
                  toppings: List[String] // Type-specific field
                ) extends MenuItem {
  override def description: String = s"${name.description} Toppings: ${toppings.mkString(", ")}."
  def addTopping(topping: String): Pizza = this.copy(toppings = topping :: this.toppings)
}