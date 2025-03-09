import CafeModels._


// Represents a customer order with items and quantities
case class Order(items: Map[MenuItem, Int], customServiceCharge: Option[Double] = None)