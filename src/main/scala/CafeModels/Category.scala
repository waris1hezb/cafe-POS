package CafeModels

// Category of the item (Food or Drink)
sealed trait Category
case object Food extends Category
case object Drink extends Category