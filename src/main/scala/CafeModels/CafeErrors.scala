package CafeModels

// Base class for all custom errors
abstract class CafeError(message: String) extends Exception(message)

// Errors related to the Menu
case class MenuItemAlreadyExistsError(itemName: String) extends CafeError(s"Item already exists: $itemName")
case class MenuItemNotFoundError(itemName: String) extends CafeError(s"Item not found: $itemName")
case class MenuInvalidStockError(itemName: String) extends CafeError(s"Invalid stock value for item: $itemName. Stock cannot be negative.")
case class MenuInsufficientStockError(itemName: String) extends CafeError(s"Insufficient stock for item: $itemName")

// Errors related to Orders
case class OrderInvalidServiceChargeError(message: String) extends CafeError(message)
case class OrderInvalidItemListError(message: String) extends CafeError(message)
case class OrderInvalidQuantityError(itemName: String) extends CafeError(s"Invalid quantity for item: $itemName. Quantity must be greater than 0.")