package CafeModels



// Name trait with descriptions
sealed trait Name {
  def description: String
}



// Cold Drinks
case object SpanishLatte extends Name {
  override def description: String = "A creamy and sweet iced coffee with condensed milk."
}
case object MochaFrappe extends Name {
  override def description: String = "A rich and chocolatey blended coffee drink."
}
case object IcedAmericano extends Name {
  override def description: String = "A refreshing cold coffee with a strong espresso base."
}

// Hot Drinks
case object Espresso extends Name {
  override def description: String = "A strong and concentrated coffee shot."
}
case object FlatWhite extends Name {
  override def description: String = "A smooth coffee with steamed milk and a double shot of espresso."
}
case object Cappuccino extends Name {
  override def description: String = "A classic coffee with equal parts espresso, steamed milk, and foam."
}

// Teas
case object Turkish extends Name {
  override def description: String = "A strong black tea brewed with traditional Turkish methods."
}
case object English extends Name {
  override def description: String = "A classic black tea blend, perfect with milk."
}
case object Green extends Name {
  override def description: String = "A light and refreshing tea with natural antioxidants."
}

// Juices
case object Orange extends Name {
  override def description: String = "Freshly squeezed orange juice, packed with vitamin C."
}
case object Apple extends Name {
  override def description: String = "Crisp and sweet apple juice, made from fresh apples."
}
case object Mango extends Name {
  override def description: String = "Sweet and tropical mango juice, bursting with flavor."
}


// Cakes
case object Tiramisu extends Name {
  override def description: String = "A classic Italian dessert with coffee-soaked ladyfingers and mascarpone cream."
}
case object Chocolate extends Name {
  override def description: String = "A rich and decadent chocolate cake, perfect for chocolate lovers."
}
case object RedVelvet extends Name {
  override def description: String = "A moist and velvety red cake with cream cheese frosting."
}

// Burgers
case object Chicken extends Name {
  override def description: String = "A juicy chicken burger with fresh lettuce and mayo."
}
case object Smash extends Name {
  override def description: String = "A smashed beef patty burger with melted cheese and pickles."
}
case object Veggie extends Name {
  override def description: String = "A plant-based burger with a flavorful veggie patty."
}

// Pizza
case object Margherita extends Name {
  override def description: String = "A classic pizza with tomato, mozzarella, and fresh basil."
}
case object Pepperoni extends Name {
  override def description: String = "A savory pizza topped with spicy pepperoni and melted cheese."
}